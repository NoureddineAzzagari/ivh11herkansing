package sample.web.ui.viewModel;

import lombok.Getter;

/**
 * Created by valiant on 26/06/2017.
 */

@Getter
public class LoginViewModel {
    private final boolean authenticated;
    private final long userId;

    public LoginViewModel(boolean authenticated, long userId) {
        this.authenticated = authenticated;
        this.userId = userId;
    }
}