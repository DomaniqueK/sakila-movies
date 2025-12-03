import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataManager {

    public ArrayList<Actor> getActors(String firstName, String lastName){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("Apples06");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/sakila");

        String query = "SELECT first_name, last_name FROM customer" +
                "WHERE last_name LIKE ? ORDER BY first_name";

        ArrayList<Actor> actors = new ArrayList<>();
        try (Connection connection = basicDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstNameResult = resultSet.getString("first_name");
                String lastNameResult = resultSet.getString("last_name");
                int id = resultSet.getInt("actor_id");
                actors.add(new Actor(firstNameResult, lastNameResult, id));
            }


        } catch (SQLException e) {
            System.out.println("Try again " + e);
        }
    return actors;
    }
}
