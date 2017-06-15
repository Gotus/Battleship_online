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

                //Password is correct
                EUserData userData = new EUserData();
                userData = user_dataService.getByLogin(loginData.getLogin());
                userData.setCurrentBattle(null);
                user_dataService.updateUser(userData);
                result.setIsSuccess(true);
                return result;
            } else {

                //Password is wrong
                result.setIsSuccess(false);
                return result;
            }
        }

    }

    /*
    *
    *
    *  tested
    */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody RegisterResult registrateUser(@RequestBody RegisterData registerData, HttpServletRequest request) throws Exception {

        RegisterResult result = new RegisterResult();

        if (user_dataService.getByLogin(registerData.getLogin()) != null)
        {
            System.out.println("Inputed login already exists");
            System.out.println(registerData.getLogin());
            System.out.println(registerData.getMail());
            System.out.println(registerData.getPassword());
            result.setIsSuccess(false);
            System.out.println(result.getIsSuccess());
            return result;

        } else {

            if (user_dataService.getByMail(registerData.getMail()) != null)
            {
                System.out.println("Inputed mail already exists");
                System.out.println(registerData.getLogin());
                System.out.println(registerData.getMail());
                System.out.println(registerData.getPassword());
                result.setIsSuccess(false);
                System.out.println(result.getIsSuccess());
                return result;
            } else {
                EUserData userData = new EUserData();
                userData.setLogin(registerData.getLogin());
                userData.setMail(registerData.getMail());
                userData.setPassword(registerData.getPassword());

                Date curDate = new Date();

                userData.setDate_of_registration(curDate);
                user_dataService.addUser(userData);
                System.out.println(registerData.getLogin());
                System.out.println(registerData.getMail());
                System.out.println(registerData.getPassword());
                result.setIsSuccess(true);
                System.out.println(result.getIsSuccess());
                return result;
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

class RegisterResult {
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

class RegisterData {

    public String login;
    public String password;
    public String email;

    public void setLogin(String login){

        this.login = login;
    }

    public void setPassword(String password){

        this.password = password;
    }

    public void setMail(String email) {

        this.email = email;
    }

    public String getLogin() {

        return this.login;
    }

    public String getPassword() {

        return this.password;
    }

    public String getMail(){

        return this.email;
    }
}

class AchievementData {

    Integer achievementID;
    String name;
    String description;
    String path;

    public Integer getAchievementID() {
        return achievementID;
    }

    public void setAchievementID(Integer achievementID) {
        this.achievementID = achievementID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

class LoginResultContainer {


}