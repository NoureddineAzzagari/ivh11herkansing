package sample.web.ui.domain.user;


public class UserStateContext implements UserStateInterface {

    private UserStateInterface state;

    public UserStateInterface getState() {
        return state;
    }

    @Override
    public void logged() {
        this.state.logged();
    }

    public void setState(UserStateInterface state) {
        this.state = state;
    }
}
