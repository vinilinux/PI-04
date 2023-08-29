package br.com.pi4.servlet;

import br.com.pi4.dao.pi4DAO;
import br.com.pi4.model.Pi4;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@WebServlet("/login")
public class LoginUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HashServlet hashpass = new HashServlet();

        String hashedPassword = null;
        try {
            hashedPassword = hashpass.encryptarSenha(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Email: " + email);
        System.out.println("Password: " + hashedPassword);

        pi4DAO db = new pi4DAO();
        Pi4 user = db.loginUser(email, hashedPassword);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("principal.jsp");
        } else {
            request.setAttribute("errorMessage", "E-mail ou senha inválido!");
            request.getRequestDispatcher("/loginUsuario.jsp").forward(request, response);
        }
    }

}
