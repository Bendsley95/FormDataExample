package com.example.TestApplication;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.List;


@Service
public class FormDataExampleService {

    public String calculate(@RequestParam(value = "operation") String operation, @RequestParam(value = "x") int x, @RequestParam(value = "y") int y) {

        String equation = "";
        if (operation.equals("add")) {
            equation = Integer.toString(x) + " + " + Integer.toString(y) + " = " + Integer.toString(x + y);
        } else if (operation.equals("subtract")) {
            equation = Integer.toString(x) + " - " + Integer.toString(y) + " = " + Integer.toString(x - y);
        } else if (operation.equals("multiply")) {
            equation = Integer.toString(x) + " * " + Integer.toString(y) + " = " + Integer.toString(x * y);
        } else if (operation.equals("divide")) {
            equation = Integer.toString(x) + " / " + Integer.toString(y) + " = " + Integer.toString(x / y);
        }

        return equation;
    }

    public String sum(@RequestParam(value = "n") List<Integer> n) {
        String equation = "";
        equation += n.get(0);
        Integer sum = n.get(0);
        n.remove(0);

        for (Integer i : n) {
            equation += " + " + i;
            sum += i;
        }
        equation += " = " + sum;
        return equation;
    }

    public String volume(@PathVariable(value = "length") Integer length, @PathVariable(value = "width") Integer width, @PathVariable(value = "height") Integer height) {
        return "The volume of a " + length + "x" + width + "x" + height + " rectangle is " + (length*width*height);
    }

}
