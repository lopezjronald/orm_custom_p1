import com.orm.config.PostgreDatabase;
import com.orm.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public class Driver {

    public static void main(String[] args) throws SQLException {

        Connection connection = new PostgreDatabase().getConnection();
        User user = new User();
        user.create(connection);

    }
}
