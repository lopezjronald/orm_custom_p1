import com.orm.config.PostgreDatabase;
import com.orm.config.SQLiteDatabase;
import com.orm.io.Log;
import com.orm.model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Driver {

    public static void main(String[] args) throws SQLException, IOException {

        Connection postgreConnection = new PostgreDatabase().getConnection();
        Connection sqliteConnection = new SQLiteDatabase().getConnection();
        User user = new User();
//        user.updateFirstAndLastName(postgreConnection);
        Log newLog = new Log();

        List<User> users = user.searchByLastName(postgreConnection);


        try (FileWriter userFile = new FileWriter("C:\\Users\\Shadow\\Desktop\\Projects\\custom-java-orm\\CustomORM\\log-file.txt")) {
            for(User eachUser: users){
                userFile.write(eachUser.toString() + "\n");
            }
        }
    }
}
