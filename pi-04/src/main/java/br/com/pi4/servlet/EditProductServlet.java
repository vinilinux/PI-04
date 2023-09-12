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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String productId = request.getParameter("id");
        if (productId == null || productId.isEmpty()) {
            request.getSession().removeAttribute("product");
            response.sendRedirect("/cadastroProduto.jsp"); //Precisa alterar
            return;
        }

        pi4DAO productDao = new pi4DAO();
        Product product = productDao.getProductById(productId);

        request.setAttribute("product", product);
        request.getRequestDispatcher("/cadastroProduto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("updateStatus")) {
            String productId = request.getParameter("productId");
            String newStatus = request.getParameter("status");

            pi4DAO productDao = new pi4DAO();
            boolean updateSuccess = productDao.atualizarStatusProduto(productId, newStatus);

            if (updateSuccess) {
                response.sendRedirect("/ListProductServlet");
            } else {
                System.out.println("Erro na atualização do Status!");
            }
        } else {
            String id_product = request.getParameter("ID_PRODUCT");
            String name = request.getParameter("NAME_PRODUCT");
            String rate = request.getParameter("RATING_PRODUCT");
            String description = request.getParameter("DESCRIPTION_PRODUCT");
            String price = request.getParameter("PRICE_PRODUCT");
            String status = request.getParameter("STATUS");
            String amount = request.getParameter("AMOUNT_PRODUCT");

            System.out.println("ID: " + id_product);
            System.out.println("Name: " + name);

            pi4DAO db = new pi4DAO();
            Product product = db.getProductById(id_product);

            if (product != null) {
//                String currentHashedPassword = product.getPassword();
//                String newHashedPassword = currentHashedPassword;
//
//                if (!password.isEmpty() && !password.equals(currentHashedPassword)) {
//                    newHashedPassword = hashPassword(password);
//                }

                Product product1 = new Product(id_product, name, rate, description, price, status, amount);
                db.updateProduto(product1);

                response.getWriter().println("Produto atualizado com sucesso!");
            } else {
                response.getWriter().println("Produto não encontrado!");
            }
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
