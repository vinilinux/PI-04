package br.com.pi4.servlet;

import br.com.pi4.dao.pi4DAO;
import br.com.pi4.model.Pi4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("id");

        pi4DAO userDao = new pi4DAO();
        Pi4 user = userDao.getUserById(userId);

        request.setAttribute("user", user);



        request.getRequestDispatcher("/cadastroUsuario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_user = request.getParameter("id_user");
        String name = request.getParameter("name");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String status = "ativo";
        String group_user = request.getParameter("grupo");

        System.out.println("ID: " + id_user);
        System.out.println("Name: " + name);
        String hashedPassword = hashPassword(password);

        Pi4 pi4 = new Pi4(id_user, name, email, hashedPassword, cpf, status, group_user);
        pi4DAO db = new pi4DAO();

        db.updatePi4(pi4);

        response.getWriter().println("Usuário atualizado com sucesso!");
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