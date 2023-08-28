package br.com.pi4.dao;

import br.com.pi4.model.Pi4;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


public class pi4DAO
{
    private static final String DB_URL = "jdbc:mysql://localhost/PI4";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "N0va-S3nh@";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    private Connection conexao() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void createUser(Pi4 pi4) {
        String SQL = "INSERT INTO USER_BACKOFFICE (NAME, EMAIL, PASSWORD, CPF, STATUS, GROUP_USER) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Class.forName(DB_DRIVER);

            Connection connection = conexao();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, pi4.getName());
            preparedStatement.setString(2, pi4.getEmail());
            preparedStatement.setString(3, pi4.getPassword());
            preparedStatement.setString(4, pi4.getCpf());
            preparedStatement.setString(5, pi4.getStatus());
            preparedStatement.setString(6, pi4.getGroup_user());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            System.out.println("Success in insertion");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

    public List<Pi4> findAllUser(String loggedInUserId) {
        String SQL = "SELECT * FROM USER_BACKOFFICE WHERE ID_USER <> ?";
        List<Pi4> pi4List = new ArrayList<>();  // Renomeado para evitar confusões

        try {
            Class.forName(DB_DRIVER);
            Connection connection = conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, loggedInUserId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id_user = resultSet.getString("ID_USER");
                String name = resultSet.getString("NAME");
                String email = resultSet.getString("EMAIL");
                String password = resultSet.getString("PASSWORD");
                String cpf = resultSet.getString("CPF");
                String status = resultSet.getString("STATUS");
                String group_user = resultSet.getString("GROUP_USER");

                Pi4 pi4 = new Pi4(id_user, name, email, password, cpf, status, group_user);
                pi4List.add(pi4);
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Fail in database connection!");
            e.printStackTrace();
        }

        return pi4List;
    }


    public void deleteUserById (String id){
        String SQL = "DELETE FROM USER_BACKOFFICE WHERE ID_USER = ?";

        try {

            Class.forName(DB_DRIVER);

            Connection connection = conexao();

            System.out.println("success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, id);
            preparedStatement.execute();

            System.out.println("success on delete user with id: " + id);

            connection.close();

        } catch (Exception e) {

            System.out.println("fail in database connection Delete");

        }
    }

        public Pi4 loginUser(String email, String password) {
            String SQL = "SELECT * FROM USER_BACKOFFICE WHERE EMAIL = ? AND PASSWORD = ?";

            Pi4 user = null;

            try {
                Class.forName(DB_DRIVER);
                Connection connection = conexao();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);

                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    user = new Pi4(
                            resultSet.getString("ID_USER"),
                            resultSet.getString("NAME"),
                            resultSet.getString("EMAIL"),
                            resultSet.getString("PASSWORD"),
                            resultSet.getString("CPF"),
                            resultSet.getString("STATUS"),
                            resultSet.getString("GROUP_USER")
                    );
                }

                resultSet.close();
                preparedStatement.close();
                connection.close();
                System.out.println("success in login attempt");

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("fail in login attempt");
            }

            return user;
        }

    public Pi4 getUserById(String id)
    {
        Pi4 user = null;
        String sql = "SELECT * FROM USER_BACKOFFICE WHERE ID_USER = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // Correção feita aqui
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new Pi4();
                    user.setId_user(rs.getString("ID_USER"));
                    user.setName(rs.getString("NAME"));
                    user.setEmail(rs.getString("EMAIL"));
                    user.setPassword(rs.getString("PASSWORD"));
                    user.setCpf(rs.getString("CPF"));
                    user.setStatus(rs.getString("STATUS"));
                    user.setGroup_user(rs.getString("GROUP_USER"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public Pi4 updatePi4(Pi4 pi4) {
        String SQL = "UPDATE USER_BACKOFFICE SET NAME = ?, EMAIL = ?, PASSWORD = ?, CPF = ?, STATUS = ?, GROUP_USER = ? WHERE ID_USER = ?";
        try {

            Class.forName(DB_DRIVER);

            Connection connection = conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, pi4.getName());
            preparedStatement.setString(2, pi4.getEmail());
            preparedStatement.setString(3, pi4.getPassword());
            preparedStatement.setString(4, pi4.getCpf());
            preparedStatement.setString(5, pi4.getStatus());
            preparedStatement.setString(6, pi4.getGroup_user());
            preparedStatement.setString(7, pi4.getId_user());

            System.out.println("Attempting to update user with ID: " + pi4.getId_user());

            preparedStatement.executeUpdate();

            connection.close();

            System.out.println("success in update");

        } catch (Exception e) {

            System.out.println("fail in connection");
            System.out.println("Error: " + e.getMessage());

        }
        return pi4;

    }


}
