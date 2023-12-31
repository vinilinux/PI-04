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

        // Método para hash de senha incorporado na LoginUserServlet
        String hashedPassword = hashPassword(password);

        pi4DAO db = new pi4DAO();
        Pi4 user = db.loginUser(email, hashedPassword);

        if (user != null) {
            if (user.getStatus().equals("ativo")) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                String loggedInUserId = user.getId_user();
                session.setAttribute("loggedInUserId", loggedInUserId);

                List<Pi4> users = userDao.findAllUser(loggedInUserId);
                session.setAttribute("otherUsers", users);

                System.out.println("Email: " + email);
                System.out.println("Password: " + hashedPassword);

                if (user.getGroup_user().equals("administrador")) {
                    response.sendRedirect("admin.jsp");
                } else {
                    response.sendRedirect("estoque.jsp");
                }
            } else {
                System.out.println("Login não foi possível. Usuário inativo.");
                request.setAttribute("errorMessage", "Usuário inativo. Entre em contato com o administrador.");
                request.getRequestDispatcher("/loginUsuario.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "E-mail ou senha inválido!");
            request.getRequestDispatcher("/loginUsuario.jsp").forward(request, response);
        }
    }

    // Método para hash de senha incorporado na LoginUserServlet
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
