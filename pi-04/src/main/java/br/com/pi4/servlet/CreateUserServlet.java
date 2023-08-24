package br.com.pi4.servlet;


import br.com.pi4.dao.pi4DAO;
import br.com.pi4.model.Pi4;

public class CreateUserServlet {
    public static void main(String[] args) {

        Pi4 pi4 = new Pi4("vinicius", "vini@teste.com", "123@Mudar", "12345678910", "habilitado", "admin");

        pi4DAO db = new pi4DAO();
        db.deleteUserById("1");


    }
}