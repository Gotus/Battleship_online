package com.kirill.controllers;

import com.kirill.entity.EUser_Data;
import com.kirill.service.EUser_DataService;
import com.kirill.service.impl.EUser_DataServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import javax.annotation.Resource;
import javax.validation.constraints.Null;

/**
 * Created by Администратор on 02.12.2016.
 */
@RestController
@RequestMapping(value = "/gamegate")
public class loginController {

    @Resource(name = "EUser_DataService")
    private EUser_DataService user_dataService;


    @RequestMapping("/hello")
    public String helloWorld(@RequestParam(value = "name", required = false, defaultValue = "Kirill")String name)
    {
        return "hello, <a href=\"/user?id="+ 3 +"\">"+ name.replaceAll("<", "&lt").replaceAll(">", "&gt") + "</a> <h1>Hay</h1>";
    }

    /*
     * Method perform logging in
     * Parameters: login: user's login, password: user's password
     *
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password) throws Exception {

        if (user_dataService.getByLogin(login) == null)
        {
            System.out.println("Inputed login does not exist.");
        } else {

            if (user_dataService.getByLogin(login).getPassword().equals(password)) {

                System.out.println("Login successful");
            } else {

                System.out.println("Wrong password");
            }
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registrateUser(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password, @RequestParam(value = "mail") String mail) throws Exception {

        if (user_dataService.getByLogin(login) != null)
        {
            System.out.println("Inputed login already exists");
            return;

        } else {

            if (user_dataService.getByMail(mail) != null)
            {
                System.out.println("Inputed mail already exists");
            } else {
                EUser_Data userData = new EUser_Data();
                userData.setLogin(login);
                userData.setMail(mail);
                userData.setPassword(password);

                Date curDate = new Date();

                userData.setDate_of_registration(curDate);
                user_dataService.addUser(userData);
            }
        }
    }
}
