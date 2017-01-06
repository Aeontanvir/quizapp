/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edureka.quiz.controller;

import co.edureka.quiz.DatabaseConnectionFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checkRegister")
public class RegistrationController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Connection con = DatabaseConnectionFactory.createConnection();

        try {
            Statement st = con.createStatement();
            String sql = "INSERT INTO users values ('" + username + "','" + password + "','" + email + "')";
            System.out.println(sql);
            st.executeUpdate(sql);
        } catch (SQLException sqe) {
            System.out.println("Error : While Inserting record in database" + sqe);
        }
        try {
            con.close();
        } catch (SQLException se) {
            System.out.println("Error : While Closing Connection");
        }
        request.setAttribute("newUser", username);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/regSuccess.jsp");
        dispatcher.forward(request, response);
    }

}
