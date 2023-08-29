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

public class pi4DAO
{
    private static final String DB_URL = "jdbc:mysql://localhost/PI4";
    private static final String DB_USER = "vini";
    private static final String DB_PASSWORD = "123@Mudar";
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

    public List<Pi4> findAllUser() {
        String SQL = "SELECT * FROM USER_BACKOFFICE";

        try
        {
            Class.forName(DB_DRIVER);

            Connection connection = conexao();
            System.out.println("Success in database connection");
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Pi4> pi4 = new ArrayList<Pi4>();

            while(resultSet.next())
            {
                String id_user = resultSet.getString("ID_USER");
                String name = resultSet.getString("NAME");
                String email = resultSet.getString("EMAIL");
                String password = resultSet.getString("PASSWORD");
                String cpf = resultSet.getString("CPF");
                String status = resultSet.getString("STATUS");
                String group_user = resultSet.getString("GROUP_USER");

                Pi4 pi4s = new Pi4(id_user, name, email, password, cpf, status, group_user);
                pi4.add(pi4s);
            }

            System.out.println("Success in select * USER_BACKOFFICE");

            connection.close();

            return pi4;

        } catch (Exception e)
        {
            System.out.println("Fail in database connection!");
            return Collections.emptyList();
        }
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

            preparedStatement.execute();

            connection.close();

            System.out.println("success in update");

        } catch (Exception e) {

            System.out.println("fail in connection");
            System.out.println("Error: " + e.getMessage());

        }
            return pi4;

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

                System.out.println(user.getGroup_user());

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

    }
