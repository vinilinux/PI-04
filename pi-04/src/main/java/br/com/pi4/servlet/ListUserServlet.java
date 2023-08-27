package br.com.pi4.servlet;

import br.com.pi4.dao.pi4DAO;
import br.com.pi4.model.Pi4;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet ("/listUser")
public class ListUserServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pi4DAO db = new pi4DAO();
        db.findAllUser();
        List<Pi4> pi4Select = db.findAllUser();

        request.setAttribute("pi4List", pi4Select);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/listUser.jsp");
        dispatcher.forward(request, response);
    }

}
