package com.kirill.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Gotus on 22.05.2017.
 */

@Controller
public class redirectController {

    @RequestMapping(value = "/url", method = RequestMethod.GET)
    public String redirectToMainPage(HttpServletRequest request) {

        //request.getScheme() - if you don't know where was the request sent: http, https, ftp..
        return "redirect:" + request.getScheme() + "/game";
    }
}
