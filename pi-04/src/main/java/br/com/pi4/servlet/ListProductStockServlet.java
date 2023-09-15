package br.com.pi4.servlet;

import br.com.pi4.dao.pi4DAO;
import br.com.pi4.model.Pi4;
import br.com.pi4.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet ("/ListProductStockServlet")
public class ListProductStockServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        pi4DAO productDAO = new pi4DAO();

        String loggedInUserId = (String) request.getSession().getAttribute("loggedInUserId");
        List<Product> products = productDAO.findAllProducts();
        request.setAttribute("productsList", products);

        request.getRequestDispatcher("/listProductStock.jsp").forward(request, response);
    }
}