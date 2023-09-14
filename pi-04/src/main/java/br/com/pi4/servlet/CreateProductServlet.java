package br.com.pi4.servlet;

import br.com.pi4.dao.pi4DAO;
import br.com.pi4.model.Arquivo;
import br.com.pi4.model.Pi4;
import br.com.pi4.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/createProduct")
@MultipartConfig
public class CreateProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().removeAttribute("user");
        request.getRequestDispatcher("/cadastroProduto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        pi4DAO db = new pi4DAO();

        String productName = request.getParameter("productName");
        double rate = Double.parseDouble(request.getParameter("rate"));
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        String status = "ativo";

        Product product = new Product(productName, rate, description, price, amount, status);

        String idProduct = db.createProduct(product);



        String nameImgDefault = request.getParameter("selectedImage");
        System.out.println(nameImgDefault);

        
        for (Part newfile : request.getParts()) {
            if (newfile.getName().equals("images[]")) {
                String imgDefault;
                InputStream arquivoCarrgado = newfile.getInputStream();
                Arquivo arquivo = new Arquivo();
                String caminho = arquivo.upload("src/main/webapp/img", newfile, arquivoCarrgado);
                String nomeImg = arquivo.nomeArquivoOriginal(newfile);
                System.out.println(nomeImg + " Nome original");

                if (nomeImg.equals(nameImgDefault)) {
                    imgDefault = "yes";
                } else {
                    imgDefault = "no";
                }
                System.out.println(imgDefault);
                db.inserirImg(caminho, imgDefault, idProduct);
            }
        }
        response.sendRedirect("ListProductServlet");
    }
}
