package sample.web.ui.domain.user;

public class Offline implements UserStateInterface {

    @Override
    public void logged() {
        System.out.println("You are offline");
    }
}
