package bs.kirill.controllers;

import bs.kirill.entity.EUserData;
import bs.kirill.service.EUser_DataService;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
     * tested
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody LoginResult login(@RequestBody LoginData loginData, HttpServletRequest request) throws Exception {

        LoginResult result = new LoginResult();
        result.setIsSuccess(false);

        if (user_dataService.getByLogin(loginData.getLogin()) == null)
        {
            //Login not found
            result.setIsSuccess(false);
            return result;
        } else {

            if (user_dataService.getByLogin(loginData.getLogin()).getPassword().equals(loginData.getPassword())) {

                Gson gson = new Gson();
                String userDataJSON = gson.toJson(user_dataService.getByLogin(loginData.getLogin()));
                System.out.println(userDataJSON);
                //Password is correct

                result.setIsSuccess(true);
                return result;
            } else {

                //Password is wrong
                result.setIsSuccess(false);
                return result;
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

class LoginResult {
    private Boolean isSuccess;

    public Boolean getIsSuccess() {

        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {

        this.isSuccess = isSuccess;
    }

}

class LoginData {

    public String login;
    public String password;

    public void setLogin(String login){

        this.login = login;
    }

    public void setPassword(String password){

        this.password = password;
    }

    public String getLogin() {

        return this.login;
    }

    public String getPassword() {

        return this.password;
    }
}