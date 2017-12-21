package sample.web.ui.Service.concrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.web.ui.Service.interfaces.IUserService;
import sample.web.ui.repository.UserRepository;
import sample.web.ui.domain.user.Authenticated;
import sample.web.ui.domain.user.User;
import sample.web.ui.domain.user.UserStateContext;
import sample.web.ui.viewModel.LoginViewModel;
import sample.web.ui.viewModel.UserViewModel;

import java.util.ArrayList;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * controleerd of de opgegeven inlognaam en wachtwoord overeen komen met een user in de db
     * @param userName inlognaam
     * @param password wachtwoord
     * @return true als user bestaat met deze username wachtwoord combinatie
     */
    public LoginViewModel authenticate(String userName, String password){
        for (User user: userRepository.findAll()){
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                    UserStateContext userStateContext = new UserStateContext();
                    userStateContext.setState(new Authenticated());
                    return new LoginViewModel(true, user.getId());
            }
        }
        return new LoginViewModel(false, -1);
    }

    /**
     * haalt alle gebruikers op uit de db
     * @return lijst met gebruikers
     */
    public UserViewModel getAllUsers(){
        ArrayList<User> allUsers = (ArrayList<User>)userRepository.findAll();
        return new UserViewModel(allUsers);
    }

    /**
     * voegt een nieuwe user toe in de db
     * @param user toe te voegen user
     */
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(String userName, String password){
        for(User user: userRepository.findAll()){
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)) return user;
        }
        return null;
    }

    /**
     * haalt een user op aan de hand van een id
     * @param id id van de op te halen user
     * @return user
     */
    @Override
    public User getUser(long id) {
        return userRepository.findOne(id);
    }

}
