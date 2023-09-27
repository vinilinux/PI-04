package br.com.pi4.servlet;

import br.com.pi4.dao.pi4DAO;
import br.com.pi4.model.Arquivo;
import br.com.pi4.model.Pi4;
import br.com.pi4.model.Product;
import br.com.pi4.model.image;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Comparator;
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

        System.out.println("xxxxxxxxx" + productId);

        pi4DAO productDao = new pi4DAO();
        Product product = productDao.getProductById(productId);

        String imagePath = product.getImageProductPath();
        System.out.println(imagePath);

        List<image> imagens = productDao.selectImage(productId);

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

        System.out.println(imagens);

        request.setAttribute("product", product);

        request.setAttribute("product_img", imagens);

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
        }

        pi4DAO userDao = new pi4DAO();

        pi4DAO db = new pi4DAO();
        String id_user = request.getParameter("id_user");
        Pi4 user = db.getUserById(id_user);


        if (user != null) {
            if (user.getStatus().equals("ativo")) {


                if (user.getGroup_user().equals("administrador")) {

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

                    List<image> imagens = productDao.selectImage(productId);
                    System.out.println("Imagens >>>>>>>>>>>" + imagens);


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

                    String nameImgDefault = request.getParameter("selectedImage");
                    System.out.println(nameImgDefault);


                    for (Part newfile : request.getParts()) {
                        if (newfile.getName().equals("images[]")) {
                            String imgDefault;
                            InputStream arquivoCarrgado = newfile.getInputStream();
                            Arquivo arquivo = new Arquivo();
                            String caminho = arquivo.upload("src/main/webapp/img", newfile, arquivoCarrgado);
                            if(caminho != null){


                                productDao.deleteImageById(productId);

                                String nomeImg = arquivo.nomeArquivoOriginal(newfile);
                                System.out.println(nomeImg + " Nome original");

                                if (nomeImg.equals(nameImgDefault)) {
                                    imgDefault = "yes";
                                } else {
                                    imgDefault = "no";
                                }
                                System.out.println(imgDefault);
                                db.inserirImg(caminho, imgDefault, productId);
                            }

                        }
                    }

                    response.sendRedirect("/ListProductServlet");
                    System.out.println("ImagensDeletadas >>>>>>>>>>>" + imagens);


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