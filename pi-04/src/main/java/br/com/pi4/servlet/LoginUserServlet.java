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
import java.util.List;


@WebServlet("/login")
public class LoginUserServlet extends HttpServlet {

    private pi4DAO userDao = new pi4DAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        Pi4 user = userDao.loginUser(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            String loggedInUserId = user.getId_user();
            session.setAttribute("loggedInUserId", loggedInUserId);

            List<Pi4> users = userDao.findAllUser(loggedInUserId);
            session.setAttribute("otherUsers", users);

            response.sendRedirect("index.jsp");
        }

    }
}
