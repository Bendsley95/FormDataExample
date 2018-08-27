package com.example.TestApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

@RestController
@RequestMapping("/math")
public class MathController {

    private MathService mathService;

    @Autowired
    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @GetMapping("/pi")
    public String math() {

        return "3.141592653589793";
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam(value = "operation") String operation, @RequestParam(value = "x") int x, @RequestParam(value = "y") int y){
        return mathService.calculate(operation, x, y);
    }

    @PostMapping("/sum")
    public String sum(@RequestParam(value = "n") List<Integer> n) {
        return mathService.sum(n);
    }

    @GetMapping("/volume/{length}/{width}/{height}")
    public String volume(@PathVariable(value = "length") Integer length, @PathVariable(value = "width") Integer width, @PathVariable(value = "height") Integer height) {
        return mathService.volume(length, width, height);
    }

    }
