package br.com.pi4.servlet;

import br.com.pi4.dao.pi4DAO;
import br.com.pi4.model.Pi4;
import br.com.pi4.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String email = request.getParameter("email"); // Suponhamos que você tenha o email do usuário

        if (email == null || email.isEmpty()) {
            // Trate o caso em que o email não está presente
            return;
        }

        pi4DAO dao = new pi4DAO();
        String groupUser = dao.getGroupUserByEmail(email);

        if (groupUser != null && groupUser.equals("estoquista")) {
            String productId = request.getParameter("id_product");
            String amount = request.getParameter("amount");

            System.out.println("Product ID: " + productId);
            System.out.println("Amount: " + amount);

            if (productId == null || productId.isEmpty() || amount == null || amount.isEmpty()) {
                return;
            }


            pi4DAO productDao = new pi4DAO();
            boolean isUpdated = productDao.updateProductAmount(productId, Integer.parseInt(amount));
            System.out.println("Product ID: " + productId);
            System.out.println("Amount: " + amount);

            if (isUpdated) {
                System.out.println("Product quantity updated successfully!");
            } else {
                System.out.println("Failed to update product quantity!");
            }

            response.sendRedirect("/ListProductStockServlet");
        } else {

            String productId = request.getParameter("id_product");
            String productName = request.getParameter("name");
            String description = request.getParameter("description");
            String rate = request.getParameter("rate");
            String price = request.getParameter("price");
            String amount = request.getParameter("amount");

            System.out.println("Product ID: " + productId);
            System.out.println("Name: " + productName);
            System.out.println("description: " + description);
            System.out.println("rate: " + rate);
            System.out.println("price: " + price);
            System.out.println("Amount: " + amount);

            if (productId == null || productId.isEmpty() ||
                    productName == null || productName.isEmpty() ||
                    description == null || description.isEmpty() ||
                    rate == null || rate.isEmpty() ||
                    price == null || price.isEmpty() ||
                    amount == null || amount.isEmpty()) {
                return;
            }


            pi4DAO productDao = new pi4DAO();
            boolean isUpdated = productDao.updateAllProduct(productId, Integer.parseInt(amount));
            System.out.println("Product ID: " + productId);
            System.out.println("Amount: " + amount);

            if (isUpdated) {
                System.out.println("Product quantity updated successfully!");
            } else {
                System.out.println("Failed to update product quantity!");
            }

            response.sendRedirect("/ListProductStockServlet");

        }
//        Pi4 pi4 = new Pi4();
//        if (pi4.getGroup_user().equals ("estoquista")){
//            String productId = request.getParameter("id_product");
//            String amount = request.getParameter("amount");
//
//            System.out.println("Product ID: " + productId);
//            System.out.println("Amount: " + amount);
//
//            if (productId == null || productId.isEmpty() || amount == null || amount.isEmpty()) {
//                return;
//            }
//
//
//            pi4DAO productDao = new pi4DAO();
//            boolean isUpdated = productDao.updateProductAmount(productId, Integer.parseInt(amount));
//            System.out.println("Product ID: " + productId);
//            System.out.println("Amount: " + amount);
//
//            if (isUpdated) {
//                System.out.println("Product quantity updated successfully!");
//            } else {
//                System.out.println("Failed to update product quantity!");
//            }
//
//            response.sendRedirect("/ListProductStockServlet");
//        } else {
//            String productId = request.getParameter("id_product");
//            String productName = request.getParameter("name");
//            String description = request.getParameter("description");
//            String rate = request.getParameter("rate");
//            String price = request.getParameter("price");
//            String amount = request.getParameter("amount");
//
//            System.out.println("Product ID: " + productId);
//            System.out.println("Name: " + productName);
//            System.out.println("description: " + description);
//            System.out.println("rate: " + rate);
//            System.out.println("price: " + price);
//            System.out.println("Amount: " + amount);
//
//            if (productId == null || productId.isEmpty() ||
//                    productName == null || productName.isEmpty() ||
//                    description == null || description.isEmpty() ||
//                    rate == null || rate.isEmpty() ||
//                    price == null || price.isEmpty() ||
//                    amount == null || amount.isEmpty()) {
//                return;
//            }
//
//
//            pi4DAO productDao = new pi4DAO();
//            boolean isUpdated = productDao.updateAllProduct(productId, Integer.parseInt(amount));
//            System.out.println("Product ID: " + productId);
//            System.out.println("Amount: " + amount);
//
//            if (isUpdated) {
//                System.out.println("Product quantity updated successfully!");
//            } else {
//                System.out.println("Failed to update product quantity!");
//            }
//
//            response.sendRedirect("/ListProductStockServlet");
//        }

    }

}


