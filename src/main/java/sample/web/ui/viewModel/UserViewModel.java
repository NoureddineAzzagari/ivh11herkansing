package sample.web.ui.viewModel;


import lombok.Getter;
import sample.web.ui.domain.user.User;

import java.util.ArrayList;

/**
 * Created by valiant on 26/06/2017.
 */
@Getter
public class UserViewModel {
    private final Iterable<User> users;

    public UserViewModel(ArrayList<User> allUsers) {
        this.users = allUsers;
    }
}
