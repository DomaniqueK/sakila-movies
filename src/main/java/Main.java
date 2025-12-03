import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        dataManager.getActors("Bob", "Faucet");

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/sakila");
        dataSource.setUsername("root");
        dataSource.setPassword("Apples06");
    }

    public static void actorsByName(BasicDataSource dataSource) {
        System.out.println("Choose your favorite actor");
        String actorName = scanner.next();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT first_name, last_name FROM customer" +
                             "WHERE last_name LIKE ? ORDER BY first_name");
        ) {
            preparedStatement.setString(1, "Sa%");

            try (ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                while (resultSet.next()) {
                    System.out.printf("first_name = %s, last_name = %s;\n");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}

/*

 */