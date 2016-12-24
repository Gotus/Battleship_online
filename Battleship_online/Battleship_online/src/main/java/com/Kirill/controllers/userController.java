package com.Kirill.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Администратор on 02.12.2016.
 */
@RestController
public class userController {

    @RequestMapping("/hello")
    public String helloWorld(@RequestParam(value = "name", required = false, defaultValue = "Kirill")String name)
    {
        return "hello, "+name;
    }
}
