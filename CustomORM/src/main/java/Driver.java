import com.orm.model.*;

public class Driver {
    public static void main(String[] args) {

        User newUser = new User();
        User secondNewUser = new User(1, "ronald", "lopez");
        System.out.println(newUser.toString());
        System.out.println(secondNewUser.toString());

    }
}
