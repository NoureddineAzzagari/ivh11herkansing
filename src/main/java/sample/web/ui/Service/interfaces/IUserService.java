package sample.web.ui.Service.interfaces;

import sample.web.ui.domain.user.User;
import sample.web.ui.viewModel.LoginViewModel;

public interface IUserService {
    void addUser(User user);
    User getUser(String userName, String password);
    LoginViewModel authenticate(String userName, String password);
    User getUser(long id);
}
