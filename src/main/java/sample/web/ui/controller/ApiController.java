package sample.web.ui.controller;

import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.web.ui.Service.concrete.UserService;
import sample.web.ui.domain.Configuration;
import sample.web.ui.domain.user.User;
import sample.web.ui.viewModel.LoginViewModel;
import sample.web.ui.viewModel.UserInfo;
import sample.web.ui.viewModel.UserViewModel;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * api controller die alle requests naar /api/** afhandeld
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {
    private final UserService userService;

    @Autowired
    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/config")
    public Configuration getConfiguration(){
        return Configuration.getConfiguration();
    }

    /**
     * methode die login gegevens controleerd
     * @param userName: gebruikers naam
     * @param password: wachtwoord
     * @return true als de inloggevens kloppen anders false.
     */
    @RequestMapping("login")
    public boolean Authenticate(String userName, String password, HttpServletResponse res, @CookieValue(value = "userInfo", defaultValue = "") String userCookie){

        if(userCookie != null && !userCookie.equals("")){
            String user = userCookie.split("~~~")[0];
            String pass = userCookie.split("~~~")[1];
            String valid = userCookie.split("~~~")[2];

            if(Boolean.valueOf(valid) && userService.authenticate(user, pass).isAuthenticated()) return true;
        }

        LoginViewModel viewModel = userService.authenticate(userName, password);
        if(viewModel.isAuthenticated()){
            String[] userInfo = new String[4];
            userInfo[0] = userName;
            userInfo[1] = password;
            userInfo[2] = String.valueOf(viewModel.getUserId());

            String userString = Joiner.on("~~~").skipNulls().join(userInfo);

            Cookie cookie = new Cookie("userInfo", userString);

            res.addCookie(cookie);
            return true;
        }
        return false;
    }

    /**
     * methode die een nieuw account aanmaakt
     * @param firstName voornaam
     * @param lastName achternaam
     * @param address adres
     * @param emailAddress email
     * @param userName gebruikers naam
     * @param password wachtwoord
     * @param age leeftijd
     * @param telephoneNumber tel nr
     */
    @RequestMapping("/signup")
    public  void  signUp(String firstName, String lastName, String address,
                         String emailAddress, String userName,
                         String password, int age, int telephoneNumber){
        User.UserBuilder userBuilder = new User.UserBuilder(firstName, lastName, address, age,
                userName, password,emailAddress);
        if(telephoneNumber > 0) userBuilder.telephoneNumber(telephoneNumber);
        User user = userBuilder.build();
        userService.addUser(user);
    }

    /**
     * methode om alle gebruikers mee op te halen
     * @return alle gebruikers
     */
    @RequestMapping("/getusers")
    public UserViewModel userViewModel (){
        return userService.getAllUsers();
    }

    /**
     * Haalt aan de hand van de cookie gebruikers informatie op
     * @param userCookie cookie met daarin gebruikersnaam en wachtwoord van de gebruiker
     * @return gevuld UserInfo object
     */
    @RequestMapping("/getUserInfo")
    public UserInfo getUserInfo(@CookieValue(value = "userInfo", defaultValue = "") String userCookie){
        User userObj = null;
        if(userCookie != null && !userCookie.equals("")){
            String user = userCookie.split("~~~")[0];
            String pass = userCookie.split("~~~")[1];
            String valid = userCookie.split("~~~")[2];
            if(Boolean.valueOf(valid)){
                userObj = userService.getUser(user, pass);
            }
        }
        return userObj != null ? new UserInfo(userObj.getUserName(), userObj.getFirstName(), userObj.getLastName(),
                userObj.getEmailAddress(), userObj.getAddress(), userObj.getTelephoneNumber()) : null;
    }

    /**
     * logt de gebruiker uit
     * @param res httpresponse
     */
    @RequestMapping("logout")
    public void logout(HttpServletResponse res){
        Cookie cookie = new Cookie("userInfo", null);
        res.addCookie(cookie);
    }
}
