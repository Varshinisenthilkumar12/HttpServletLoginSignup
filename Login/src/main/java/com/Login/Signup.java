package com.Login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Signup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!isValidEmail(email) || !isValidPassword(password)) {
            response.getWriter().append("Invalid email or password format. Please try again.");
            return;
        }
        LoginService loginService = new LoginService();

        if (loginService.createUser(email, password)) {
            response.sendRedirect("login.jsp");
        } else {
            response.getWriter().append("Failed to create account. Please try again later.");
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    private boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }
}
