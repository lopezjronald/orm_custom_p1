import com.orm.config.Database;
import com.orm.model.User;

public class Driver {
    public static void main(String[] args) {

        User secondNewUser = new User(1, "ronald", "lopez");
        System.out.println(secondNewUser.createNewUser("   ", "dfsdf"));
        Database database = new Database();
        System.out.println(database.toString());

    }
}
