import com.orm.config.PostgreDatabase;
import com.orm.config.SQLiteDatabase;
import com.orm.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws SQLException {

        Connection postgreConnection = new PostgreDatabase().getConnection();
        Connection sqliteConnection = new SQLiteDatabase().getConnection();
        User user = new User();
        user.createTable(sqliteConnection);
        String firstName = new Scanner(System.in).nextLine();
        String lastName = new Scanner(System.in).nextLine();
        User newUser = user.create(sqliteConnection, firstName, lastName);
        System.out.println(newUser.toString());
        newUser = user.searchById(sqliteConnection);
        System.out.println(newUser.toString());

    }
}
