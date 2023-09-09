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

@WebServlet("/createProduct")
public class CreateProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String productName = request.getParameter("productName");
            double rate = Double.parseDouble(request.getParameter("rate"));
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            int amount = Integer.parseInt(request.getParameter("amount"));
            String status = "ativo";

        Product product = new Product(productName, rate, description, price, amount, status);

        pi4DAO db = new pi4DAO();

        db.createProduct(product);



    }
}
