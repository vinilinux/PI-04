package br.com.pi4.servlet;

import br.com.pi4.dao.pi4DAO;
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
    }

}


