package br.com.pi4.dao;

import br.com.pi4.model.Pi4;
import br.com.pi4.model.Product;
import br.com.pi4.model.image;

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


public class pi4DAO {
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
        List<Pi4> pi4List = new ArrayList<>();

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


    public boolean isEmailAlreadyRegistered(String email) {
        String SQL = "SELECT * FROM USER_BACKOFFICE WHERE EMAIL = ?";

        try {
            Class.forName(DB_DRIVER);
            Connection connection = conexao();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            boolean isRegistered = resultSet.next();

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return isRegistered;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }

        return false;
    }


    public void deleteUserById(String id) {
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

    public Pi4 getUserById(String id) {
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

    public Product getProductById(String id) {
        Product product = null;
        String sql = "SELECT P.ID_PRODUCT, P.NAME_PRODUCT, P.RATING_PRODUCT, P.DESCRIPTION_PRODUCT, P.PRICE_PRODUCT, " +
                "P.AMOUNT_PRODUCT, P.STATUS, PI.IMAGE_PRODUCT_PATH, PI.IMAGE_DEFAULT " +
                "FROM TBL_PRODUCT P " +
                "LEFT JOIN TBL_PRODUCT_IMAGE PI ON P.ID_PRODUCT = PI.ID_PRODUCT " +
                "WHERE P.ID_PRODUCT = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    product = new Product();
                    product.setId_product(rs.getString("ID_PRODUCT"));
                    product.setName(rs.getString("NAME_PRODUCT"));
                    product.setRate(rs.getDouble("RATING_PRODUCT"));
                    product.setDescription(rs.getString("DESCRIPTION_PRODUCT"));
                    product.setPrice(rs.getDouble("PRICE_PRODUCT"));
                    product.setAmount(rs.getInt("AMOUNT_PRODUCT"));
                    product.setStatus(rs.getString("STATUS"));
                    product.setImageProductPath(rs.getString("IMAGE_PRODUCT_PATH"));
                    product.setImageDefault(rs.getString("IMAGE_DEFAULT"));
                    System.out.println("Product found in database!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Product not found in database!");
        }
        return product;
    }


    public boolean updateProductAmount(String productId, int amount) {
        String sql = "UPDATE TBL_PRODUCT SET AMOUNT_PRODUCT = ? WHERE ID_PRODUCT = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, amount);
            stmt.setInt(2, Integer.parseInt(productId));

            int affectedRows = stmt.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAllProduct(String productId, String name, double rate, String description, double price, int amount) {
        String sql = "UPDATE TBL_PRODUCT SET NAME_PRODUCT = ?, RATING_PRODUCT = ?, DESCRIPTION_PRODUCT = ?, " +
                "PRICE_PRODUCT = ?, AMOUNT_PRODUCT = ? WHERE ID_PRODUCT = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Certifique-se de que os valores não nulos sejam passados
            if (productId == null || name == null || description == null) {
                System.out.println("Parâmetros nulos não permitidos");
                return false;
            }

            // Verifique se os valores podem ser convertidos em números antes de tentar a conversão
            double parsedRate;
            try {
                parsedRate = Double.parseDouble(String.valueOf(rate));
            } catch (NumberFormatException e) {
                System.out.println("Erro de conversão para 'rate': " + e.getMessage());
                return false;
            }

            double parsedPrice;
            try {
                parsedPrice = Double.parseDouble(String.valueOf(price));
            } catch (NumberFormatException e) {
                System.out.println("Erro de conversão para 'price': " + e.getMessage());
                return false;
            }

            int parsedAmount;
            try {
                parsedAmount = Integer.parseInt(String.valueOf(amount));
            } catch (NumberFormatException e) {
                System.out.println("Erro de conversão para 'amount': " + e.getMessage());
                return false;
            }

            int parsedProductId;
            try {
                parsedProductId = Integer.parseInt(productId);
            } catch (NumberFormatException e) {
                System.out.println("Erro de conversão para 'productId': " + e.getMessage());
                return false;
            }

            stmt.setString(1, name);
            stmt.setDouble(2, parsedRate);
            stmt.setString(3, description);
            stmt.setDouble(4, parsedPrice);
            stmt.setInt(5, parsedAmount);
            stmt.setInt(6, parsedProductId);

            int affectedRows = stmt.executeUpdate();
            System.out.println("Linha 287 - TRY");
            System.out.println("Affected rows: " + affectedRows);

            if (affectedRows > 0) {
                System.out.println("Product quantity updated successfully!");
                return true;
            } else {
                System.out.println("Failed to update product quantity!");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Linha 295 - Exception");
            e.printStackTrace();
            return false;
        }
    }



    public String getGroupUserByEmail(String email) {
        String SQL = "SELECT GROUP_USER FROM USER_BACKOFFICE WHERE EMAIL = ?";

        try {
            Class.forName(DB_DRIVER);
            Connection connection = conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String groupUser = resultSet.getString("GROUP_USER");
                return groupUser;
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Fail in database connection!");
            e.printStackTrace();
        }

        return null; // Retorna null se não encontrar o group_user
    }


    public Pi4 getEmail(String email) {
        String SQL = "SELECT * FROM USER_BACKOFFICE WHERE EMAIL = ? ";

        Pi4 user = null;

        try {
            Class.forName(DB_DRIVER);
            Connection connection = conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, email);

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

    public boolean atualizarStatusUsuario(String userId, String novoStatus) {
        String SQL = "UPDATE USER_BACKOFFICE SET STATUS = ? WHERE ID_USER = ?";
        try {
            Connection connection = conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, novoStatus);
            preparedStatement.setString(2, userId);

            int rowsAffected = preparedStatement.executeUpdate();

            connection.close();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarStatusProduto(String productId, String novoStatus) {
        String SQL = "UPDATE TBL_PRODUCT SET STATUS = ? WHERE ID_PRODUCT = ?";
        try {
            Connection connection = conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, novoStatus);
            preparedStatement.setString(2, productId);

            int rowsAffected = preparedStatement.executeUpdate();

            connection.close();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public List<Product> findAllProducts() {
        String SQL = "SELECT P.ID_PRODUCT, P.NAME_PRODUCT, P.RATING_PRODUCT, P.DESCRIPTION_PRODUCT, P.PRICE_PRODUCT, " +
                "P.AMOUNT_PRODUCT, P.STATUS, PI.IMAGE_PRODUCT_PATH, PI.IMAGE_DEFAULT " +
                "FROM TBL_PRODUCT P " +
                "LEFT JOIN TBL_PRODUCT_IMAGE PI ON P.ID_PRODUCT = PI.ID_PRODUCT " +
                "ORDER BY P.ID_PRODUCT DESC";

        List<Product> listProduct = new ArrayList<>();

        try {
            Class.forName(DB_DRIVER);
            Connection connection = conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id_product = resultSet.getString("ID_PRODUCT");
                String name = resultSet.getString("NAME_PRODUCT");
                double rate = resultSet.getDouble("RATING_PRODUCT");
                String description = resultSet.getString("DESCRIPTION_PRODUCT");
                double price = resultSet.getDouble("PRICE_PRODUCT");
                int amount = resultSet.getInt("AMOUNT_PRODUCT");
                String status = resultSet.getString("STATUS");
                String imageProductPath = resultSet.getString("IMAGE_PRODUCT_PATH");
                String imageDefault = resultSet.getString("IMAGE_DEFAULT");

                Product product = new Product(id_product, name, rate, description, price, amount, status);
                product.setImageProductPath(imageProductPath);
                product.setImageDefault(imageDefault);

                listProduct.add(product);
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Fail in database connection!");
            e.printStackTrace();
        }

        return listProduct;
    }

    public List<Product> allProduct() {
        String sql = "Select * from TBL_PRODUCT";
        List<Product> listProduct = new ArrayList<>();

        try {
            Class.forName(DB_DRIVER);
            Connection connection = conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id_product = resultSet.getString("ID_PRODUCT");
                String name = resultSet.getString("NAME_PRODUCT");
                double rate = resultSet.getDouble("RATING_PRODUCT");
                String description = resultSet.getString("DESCRIPTION_PRODUCT");
                double price = resultSet.getDouble("PRICE_PRODUCT");
                int amount = resultSet.getInt("AMOUNT_PRODUCT");
                String status = resultSet.getString("STATUS");

                Product product = new Product(id_product, name, rate, description, price, amount, status);

                listProduct.add(product);
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Fail in database connection!");
            e.printStackTrace();
        }

        return listProduct;

    }

    public String createProduct(Product product) {
        String SQL = "INSERT INTO TBL_PRODUCT (NAME_PRODUCT, RATING_PRODUCT, DESCRIPTION_PRODUCT, PRICE_PRODUCT, " +
                "AMOUNT_PRODUCT, STATUS) VALUES (?,?,?,?,?,?)";

        String idProduct = null;

        try {
            Class.forName(DB_DRIVER);
            Connection connection = conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getRate());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setInt(5, product.getAmount());
            preparedStatement.setString(6, product.getStatus());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                idProduct = generatedKeys.getString(1);
            }

            preparedStatement.close();
            connection.close();

            System.out.println("Success in insertion");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
        return idProduct;

    }

    public void inserirImg(String imgPath, String imgDefault, String idProduct) {
        String sql = "INSERT INTO TBL_PRODUCT_IMAGE (IMAGE_PRODUCT_PATH, IMAGE_DEFAULT, ID_PRODUCT) VALUES (?,?,?)";

        try {
            Class.forName(DB_DRIVER);
            Connection connection = conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, imgPath);
            preparedStatement.setString(2, imgDefault);
            preparedStatement.setString(3, idProduct);

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

    public List<image> selectImage(String id) {
        String sql = "Select IMAGE_PRODUCT_PATH, IMAGE_DEFAULT from TBL_PRODUCT_IMAGE where ID_PRODUCT = ? ";
        List<image> images = new ArrayList<>();

        try {
            Class.forName(DB_DRIVER);
            Connection connection = conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String image_path = resultSet.getString("IMAGE_PRODUCT_PATH");
                String image_default = resultSet.getString("IMAGE_DEFAULT");

                image image = new image(image_path, image_default);

                images.add(image);
            }

            connection.close();
            return images;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateImage(String imgPath, String imgDefault, String idProduct){
        String sql = "UPDATE TBL_PRODUCT_IMAGE SET IMAGE_PRODUCT_PATH = ?, IMAGE_DEFAULT = ? WHERE ID_PRODUCT = ?";

        try {
            Class.forName(DB_DRIVER);
            Connection connection = conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, imgPath);
            preparedStatement.setString(2, imgDefault);
            preparedStatement.setString(3, idProduct);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            System.out.println("Success in update");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

    public void deleteImageById(String id) {
        String sql = "DELETE FROM TBL_PRODUCT_IMAGE WHERE ID_PRODUCT = ?";

        try {
            Class.forName(DB_DRIVER);
            Connection connection = conexao();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);

            // Use executeUpdate() para executar a instrução DELETE
            int rowsAffected = preparedStatement.executeUpdate();

            // Verifique quantas linhas foram afetadas (opcional)
            System.out.println("Linhas afetadas: " + rowsAffected);

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}