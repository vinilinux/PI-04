package br.com.pi4.servlet;

import br.com.pi4.dao.pi4DAO;
import br.com.pi4.model.Pi4;
import br.com.pi4.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@WebServlet("/EditProductStockServlet")
public class EditProductStockServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productId = request.getParameter("id").trim();
        if (productId == null || productId.isEmpty()) {
            request.getSession().removeAttribute("product");
            response.sendRedirect("/cadastroProduto.jsp");
            return;
        }

        pi4DAO productDao = new pi4DAO();
        Product product = productDao.getProductById(productId);

        request.setAttribute("product", product);
        request.getRequestDispatcher("/cadastroProduto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        pi4DAO userDao = new pi4DAO();

        pi4DAO db = new pi4DAO();
        String id_user = request.getParameter("id_user");
        Pi4 user = db.getUserById(id_user);


        if (user != null) {
            if (user.getStatus().equals("ativo")) {


                if (user.getGroup_user().equals("administrador")) {

                    System.out.println("Linha 55");

                    String productId = request.getParameter("id_product");
                    String productName = request.getParameter("productName");
                    String description = request.getParameter("description");
                    String rate = request.getParameter("rate");
                    String price = request.getParameter("price");
                    String amount = request.getParameter("amount");

                    System.out.println("Linha 64");
                    System.out.println("Product ID: " + productId);
                    System.out.println("Name: " + productName);
                    System.out.println("description: " + description);
                    System.out.println("rate: " + rate);
                    System.out.println("price: " + price);
                    System.out.println("Amount: " + amount);

                    if (productId == null || productId.isEmpty() ||
                            productName == null || productName.isEmpty() ||
                            description == null || description.isEmpty()) {
                        System.out.println("Condição null");
                        return;
                    }

                    System.out.println("Linha 79");


                    pi4DAO productDao = new pi4DAO();
                    boolean isUpdated = productDao.updateAllProduct(productId, productName, Double.parseDouble(String.valueOf(rate)),
                            description, Double.parseDouble(String.valueOf(price)), Integer.parseInt(String.valueOf(amount)));
                    System.out.println("Product ID: " + productId);
                    System.out.println("Name: " + productName);
                    System.out.println("Rate: " + rate);
                    System.out.println("Description: " + description);
                    System.out.println("Price: " + price);
                    System.out.println("Amount: " + amount);

                    if (isUpdated) {
                        System.out.println("Product quantity updated successfully!");
                    } else {
                        System.out.println("Failed to update product quantity!");
                    }

                    response.sendRedirect("/ListProductServlet");


                } else if(user.getGroup_user().equals("estoquista")){
                    String productId = request.getParameter("id_product");
                    String amount = request.getParameter("amount");

                    System.out.println("Product ID: " + productId);
                    System.out.println("Amount: " + amount);

                    if (productId == null || productId.isEmpty()) {
                        return;
                    }


                    pi4DAO productDao = new pi4DAO();
                    boolean isUpdated = productDao.updateProductAmount(productId, Integer.parseInt(String.valueOf(amount)));
                    System.out.println("Product ID: " + productId);
                    System.out.println("Amount: " + amount);

                    if (isUpdated) {
                        System.out.println("Product quantity updated successfully!");
                    } else {
                        System.out.println("Failed to update product quantity!");
                    }

                    response.sendRedirect("/ListProductStockServlet");

                }else{
                    System.out.println("Deu rui");
                }
            } else {
                System.out.println("Login não foi possível. Usuário inativo.");
            }
        } else {
            request.setAttribute("errorMessage", "E-mail ou senha inválido!");
        }

    }

}


