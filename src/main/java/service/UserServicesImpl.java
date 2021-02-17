/**
 * @author : Arjun Gautam
 * e-mail : arjungautam5431@gmail.com
 * Date :2021-02-17
 * Time :14:43
 */
package service;

import database.UserDatabase;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServicesImpl implements UserServices {
    UserDatabase userDatabase = new UserDatabase();

    /* Add User */
    @Override
    public String addUser(User user) {

        int id = user.getId();
        String fistName = user.getFirstName();
        String lastName = user.getLastName();
        int age = user.getAge();
        String query = "insert into users(id,firstName,lastName,age)values(" + id + ",'" + fistName + "','" + lastName + "'," + age + ")";
        String result = userDatabase.executeUpdate(query);
        return result;
    }


    /* Get all users */

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "select * from users";
        ResultSet resultSet = userDatabase.executeQuery(query);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setFirstName(resultSet.getString("firstName"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getInt("age"));
                    userList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    /* Get User By ID */
    @Override
    public User getUserById(int id) {
        User user = new User();
        String query = "Select * from users where id =" + id;
        ResultSet resultSet = userDatabase.executeQuery(query);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setFirstName(resultSet.getString("firstName"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getInt("age"));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    /* Update User by ID */

    @Override
    public User updateUser(int id, User user) {

        User previousUser = getUserById(id);

        if (previousUser.getFirstName() != user.getFirstName() && user.getFirstName() != null) {
            previousUser.setFirstName(user.getFirstName());
        }

        if (previousUser.getLastName() != user.getLastName() && user.getLastName() != null) {
            previousUser.setLastName(user.getLastName());
        }

        if (previousUser.getAge() != user.getAge() && user.getAge() != 0) {
            previousUser.setAge(user.getAge());
        }

        user = previousUser;
        String query = "update users set firstName='" + user.getFirstName() + "',lastName='" + user.getLastName() + "',age=" + user.getAge() + " where id=" + id;

        userDatabase.executeUpdate(query);

        return user;

    }

    /*Delete User by ID */

    @Override
    public String deleteUser(int id) {

        String query = "delete from users where id=" + id;
        userDatabase.executeUpdate(query);
        return "User with " + id + " has been deleted sucessfully ";
    }


}
