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
    UserDatabase userDatabase=new UserDatabase();
    /* Add User */
    @Override
    public String addUser(User user){

        int id=user.getId();
        String fistName=user.getFirstName();
        String lastName=user.getLastName();
        int age=user.getAge();
        String query="insert into users(id,firstName,lastName,age)values("+id+",'"+fistName+"','"+lastName+"',"+age+")";
        String result =userDatabase.executeUpdate(query);
        return result;
    }


    /* Get all users */

    @Override
    public List<User> getAllUsers(){
        List<User> userList=new ArrayList<>();
        String query="select * from users";
        ResultSet resultSet=userDatabase.executeQuery(query);

        if(resultSet!=null){
            try{
                while (resultSet.next()){
                    User user=new User();
                    user.setId(resultSet.getInt("id"));
                    user.setFirstName(resultSet.getString("firstName"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getInt("age"));
                    userList.add(user);

                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return userList;
    }


}
