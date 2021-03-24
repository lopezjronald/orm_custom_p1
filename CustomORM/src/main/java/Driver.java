import com.orm.config.PostgreDatabase;
import com.orm.config.Queries;
import com.orm.config.SQLiteDatabase;
import com.orm.model.Table;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) throws SQLException {

        Connection postgreConnection = new PostgreDatabase().getConnection();
        Connection sqliteConnection = new SQLiteDatabase().getConnection();
        Queries user = new Queries();
        Table table = new Table();
        ArrayList<String> tables = user.showTables(postgreConnection);
        table.showColumnsInTable();
//        user.createTable(postgreConnection, user.askForTableName());
//        System.out.println(user.createColumn(postgreConnection, user.askForTableName(), user.askForColumnName(), user.askForDataType(), user.getFullConstraintRequest(user.askForColumnName())));
//        user.dropTable(postgreConnection, user.askForTableName());
//
//        String constraint = user.getFullConstraintRequest(user.askForColumnName());
//        System.out.println(constraint);

//        user.updateFirstAndLastName(postgreConnection);
//        Log newLog = new Log();
//
//        List<User> users = user.searchByLastName(postgreConnection);
//
//
//        try (FileWriter userFile = new FileWriter("C:\\Users\\Shadow\\Desktop\\Projects\\custom-java-orm\\CustomORM\\log-file.txt")) {
//            for(User eachUser: users){
//                userFile.write(eachUser.toString() + "\n");
//            }
//        }
    }
}
