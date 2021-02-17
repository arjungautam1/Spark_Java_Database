import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import service.UserServicesImpl;

import java.util.List;

import static spark.Spark.*;

/**
 * @author : Arjun Gautam
 * e-mail : arjungautam5431@gmail.com
 * Date :2021-02-17
 * Time :15:04
 */

public class SparkDatabase {

    private static UserServicesImpl userServices = new UserServicesImpl();
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        port(8080);

        /* Add new User */

        post("/user", (request, response) -> {

            User user = mapper.readValue(request.body(), User.class);
            String result = userServices.addUser(user);
            return mapper.writeValueAsString(result);
        });

        /* Get all Users */

        get("/users", (request, response) -> {
            List<User> userList = userServices.getAllUsers();
            return mapper.writeValueAsString(userList);
        });





    }
}
