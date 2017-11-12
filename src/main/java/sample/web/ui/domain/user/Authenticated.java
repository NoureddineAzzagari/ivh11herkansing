package sample.web.ui.domain.user;

public class Authenticated implements UserStateInterface {

    @Override
    public void logged() {
      System.out.println("You successfully logged in");
    }
}
