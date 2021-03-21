import com.orm.config.PostgreQueries;
import com.orm.model.User;

import java.sql.SQLException;
import java.util.List;

public class Driver {

    public static void main(String[] args) throws SQLException {


        PostgreQueries postgreQueries = new PostgreQueries();
        User user = new User();



        User newUser = user.searchById(postgreQueries);
        System.out.println(newUser.toString());
        user.updateLastName(postgreQueries);

//        List<User> users = user.searchByFirstName(postgreQueries);
//
////        List<User> users = postgreQueries.searchByFirstName("joyce");
//        for (User eachUser : users) {
//            System.out.println(eachUser.getLastName());
//        }
//
//        users = user.searchByLastName(postgreQueries);
////        users = postgreQueries.searchByLastName("lopez");
//        for (User eachUser : users) {
//            System.out.println(eachUser.getFirstName());
//        }
//
//        users = user.searchByFirstAndLastName(postgreQueries);
//            for (User eachUser : users) {
//                System.out.print(eachUser.getId() + ": ");
//                System.out.print(eachUser.getFirstName() + " ");
//                System.out.println(eachUser.getLastName());
//        }

//        user.deleteById(postgreQueries);

    }
}
