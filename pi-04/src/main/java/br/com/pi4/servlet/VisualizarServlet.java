package br.com.pi4.servlet;

import br.com.pi4.dao.pi4DAO;
import br.com.pi4.model.Product;
import br.com.pi4.model.image;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/visualizarProduct")
public class VisualizarServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        pi4DAO productDAO = new pi4DAO();

        Product product = new Product();

        String id = request.getParameter("id");
        product.setDescription(request.getParameter("description"));
        product.setName(request.getParameter("productName"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setRate(Double.parseDouble(request.getParameter("rate")));


        List<image> imagens = productDAO.selectImage(id);

        Collections.sort(imagens, new Comparator<image>() {
            @Override
            public int compare(image o1, image o2) {
                if (o1.getImage_default().equals("yes")){
                    return -1;
                } else if (o2.getImage_default().equals("yes")){
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        request.setAttribute("product", product);

        request.setAttribute("product_img", imagens);

        request.getRequestDispatcher("/visualizarProduto.jsp").forward(request, response);
    }
}
