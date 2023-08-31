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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().removeAttribute("user");
        request.getRequestDispatcher("/cadastroUsuario.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_user = request.getParameter("id_user");
        String name = request.getParameter("name");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String status = "ativo";
        String group_user = request.getParameter("grupo");

        HashServlet hashpass = new HashServlet();

        String hashedPassword = null;
        try {
            hashedPassword = hashpass.encryptarSenha(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        Pi4 pi4 = new Pi4(id_user, name, email, hashedPassword, cpf, status, group_user);
        pi4DAO db = new pi4DAO();

        if (email != null && !email.isEmpty()){

            if (db.isEmailAlreadyRegistered(email)){
                response.getWriter().println("E-mail já cadastrado!");
            }else {

                if (id_user != null && !id_user.isEmpty()) {
                    db.updatePi4(pi4);
                    response.getWriter().println("Usuário atualizado com sucesso!");
                } else {
                    pi4.setId_user(null);
                    db.createUser(pi4);
                    response.getWriter().println("Usuário cadastrado com sucesso!");
                }
            }
        }else{
            response.getWriter().println("E-mail inválido.");
        }

    }
}