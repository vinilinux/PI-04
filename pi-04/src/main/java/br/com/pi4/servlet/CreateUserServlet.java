package br.com.pi4.servlet;

import br.com.pi4.dao.pi4DAO;
import br.com.pi4.model.Pi4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {

    /*public static void main(String[] args) {

        Pi4 pi4 = new Pi4("vinicius", "vini@teste.com", "123@Mudar", "12345678910", "habilitado", "admin");

        pi4DAO db = new pi4DAO();
        db.createUser(pi4);
        //db.deleteUserById("1");


    }*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String status = "ativo";
        String group_user = request.getParameter("grupo");

        Pi4 pi4 = new Pi4(name, email, password, cpf, status, group_user);

        pi4DAO db = new pi4DAO();
        db.createUser(pi4);

        response.getWriter().println("Usuário cadastrado com sucesso!");
    }
}