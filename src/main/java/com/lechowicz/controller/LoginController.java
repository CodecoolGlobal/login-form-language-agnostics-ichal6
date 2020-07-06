package com.lechowicz.controller;

import com.lechowicz.dao.DataSourceReader;
import com.lechowicz.dao.UserDAO;
import com.lechowicz.dao.UserJDBCDAO;
import com.lechowicz.exception.DatabaseException;
import com.lechowicz.exception.NoUserException;
import com.lechowicz.exception.ReadException;
import com.lechowicz.model.User;
import org.postgresql.ds.PGSimpleDataSource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private UserDAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        PGSimpleDataSource dataSource = null;

        try {
            dataSource = DataSourceReader.getDataSource("src/main/resources/database.properties");
        } catch (IOException e) {
            throw new DatabaseException(e.getMessage());
        }

        dao = new UserJDBCDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        HttpSession session = request.getSession(true);
        try {
            int hashCode = Objects.hash(name, password);

            User user = dao.getUser(name, hashCode);

            session.setAttribute("username", user.name);
            response.sendRedirect(request.getContextPath() + "/welcome");
        } catch (ReadException e) {
            throw new ServletException(e);
        } catch (NoUserException e){
            session.setAttribute("message", "Invalid email/password");
            response.sendRedirect("/login");
        }
    }

}
