import com.orm.config.Queries;
import com.orm.model.User;

import java.sql.SQLException;

public class Driver {

    public static void main(String[] args) throws SQLException {

        Queries queries = new Queries();
        User user = new User();
        User newUser = user.searchById(queries);
        System.out.println(newUser.toString());

    }
}
