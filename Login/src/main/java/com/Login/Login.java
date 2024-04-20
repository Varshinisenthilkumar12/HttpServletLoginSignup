package com.Login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("pass");

        if (!isValidEmail(name)) {
            res.getWriter().append("Invalid email");
            return;
        }

        if (!isValidPassword(password)) {
            res.getWriter().append("Invalid password");
            return;
        }
        LoginService loginService = new LoginService();

        if (loginService.authenticateUser(name, password)) {
            res.getWriter().append("Logged in successfully");
        } else {
            res.getWriter().append("Invalid Username or Password");
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 8; 
    }
}
