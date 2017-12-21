package sample.web.ui.viewModel;

import lombok.Getter;

/**
 * Created by valiant on 26/06/2017.
 */
@Getter
public class UserInfo {
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
    private final int phone;

    public UserInfo(String userName, String firstName, String lastName, String email, String address, int phone) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
}
