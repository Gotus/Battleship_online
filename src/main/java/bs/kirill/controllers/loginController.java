package bs.kirill.controllers;

import bs.kirill.entity.EUserData;
import bs.kirill.service.EUser_DataService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import javax.annotation.Resource;

/**
 * Created by Администратор on 02.12.2016.
 */
@RestController
@RequestMapping(value = "/gamegate")
public class loginController {

    @Resource(name = "EUser_DataService")
    private EUser_DataService user_dataService;


    //валидировать данные запроса
    /*@RequestMapping("/hello")
    public String helloWorld(@RequestParam(value = "name", required = false, defaultValue = "Kirill")String name)
    {
        try {

            File file = new File("HTML-pages/hello.html");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String currentString = new String(reader.readLine());
            StringBuilder builder = new StringBuilder();

            while (currentString != null) {

                builder.append(currentString);
                currentString = reader.readLine();
            }

            System.out.println(builder.toString());
            return builder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error";
        //return "hello, <a href=\"/user?id="+ 3 +"\">"+ name.replaceAll("<", "&lt").replaceAll(">", "&gt") + "</a> <h1>Hay</h1>";
    }
    */

    /*
    @RequestMapping(value = "/image/{imageName}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] giveImage(@PathVariable(value = "imageName") String imageName) {
        byte[] data = null;
        try {

            String directory = "static/" + imageName;
            Path path = Paths.get(directory);
            data = Files.readAllBytes(path);

        } catch (IOException e) {

            e.printStackTrace();
        }

        return data;
    }
    */

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

        //return "redirect:/";

/*
        try {

            byte[] data = null;
            try {

                String directory = "static/" + imageName;
                Path path = Paths.get(directory);
                data = Files.readAllBytes(path);

            } catch (IOException e) {

                e.printStackTrace();
            }

            return data;*/
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
                EUserData userData = new EUserData();
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
