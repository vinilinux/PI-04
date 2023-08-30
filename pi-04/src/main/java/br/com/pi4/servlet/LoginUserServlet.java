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
import java.util.List;

@WebServlet("/login")
public class LoginUserServlet extends HttpServlet {
    private pi4DAO userDao = new pi4DAO();

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

            String loggedInUserId = user.getId_user();
            session.setAttribute("loggedInUserId", loggedInUserId);

            List<Pi4> users = userDao.findAllUser(loggedInUserId);
            session.setAttribute("otherUsers", users);

            if (user.getGroup_user().equals("administrador")) {
                response.sendRedirect("admin.jsp");
            } else {
                response.sendRedirect("estoque.jsp");
            }
        } else {
            request.setAttribute("errorMessage", "E-mail ou senha inválido!");
            request.getRequestDispatcher("/loginUsuario.jsp").forward(request, response);
        }

    }
}
