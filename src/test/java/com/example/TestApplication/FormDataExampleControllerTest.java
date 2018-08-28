package com.example.TestApplication;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FormDataExampleController.class)
public class FormDataExampleControllerTest {

    @Autowired
    MockMvc mvc;
    @Test
    public void testPi() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/math/pi");
        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }
    @Test
    public void testCalculate() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=add&x=4&y=6");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }

    @Test
    public void Return404_WhenCalculateGetsInvalidRequest() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=add&x=");

        this.mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSum() throws Exception {

        RequestBuilder request = post("/math/sum?n=4&n=5&n=6");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));
    }

    @Test
    public void testSum_Return404_GivenBadRequest() throws Exception {

        RequestBuilder request = post("/math/su");

        this.mvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSum_Return400_WhenBadRequest() throws Exception {

        RequestBuilder request = post("/math/sum");

        this.mvc.perform(request)
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testVolume() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/math/volume/3/4/5");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));

    }

    @Test
    public void testVolume_Return400_GivenBadRequest() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/math/volume/3/4/dumb");

        this.mvc.perform(request)
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testCreateComment() throws Exception {
        String content = String.valueOf(new Random().nextInt());

        MockHttpServletRequestBuilder request1 = post("/comments")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("content", content)
                .param("author", "Dwayne");

        this.mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("Dwayne said %s!", content)));
    }
}
