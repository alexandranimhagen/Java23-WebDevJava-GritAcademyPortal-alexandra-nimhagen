package servlets;

import models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.LinkedList;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private int sessionMaxInterval = 8000;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("errorMessage","");
        req.getRequestDispatcher("JSP/login.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        //retrieving data from loginForm

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String userType = req.getParameter("user_type");


        if (userType != null && userType.equals("student")) {
            LinkedList<String[]> data = MySQLConnector.getConnector().selectQuery("studentLogin", username, password);


                if (data.size() > 1) {
                    req.getSession().setMaxInactiveInterval(sessionMaxInterval);
                    UserBean userBean = new UserBean((data.get(1))[0], USER_TYPE.student, PRIVILEGE_TYPE.user, STATE_TYPE.confirmed);
                    req.getSession().setAttribute("userBean", userBean);
                    req.getRequestDispatcher("/userPage").forward(req, resp);
                } else {//if login not found goes back to login form and sows a message
                    req.getSession().setAttribute("errorMessage", "Student not found");
                    req.getRequestDispatcher("JSP/login.jsp").forward(req, resp);
                  }
        }else if (userType != null && userType.equals("teacher")) {
                LinkedList<String[]> data = MySQLConnector.getConnector().selectQuery("teacherLogin", username, password);
                //data object always returns row with column names
                if (data.size() > 1) {
                    req.getSession().setMaxInactiveInterval(sessionMaxInterval);
                    UserBean userBean = new UserBean((data.get(1))[0],USER_TYPE.teacher, resolvePrivilegeType(data.get(1)[8]), STATE_TYPE.confirmed);
                    req.getSession().setAttribute("userBean", userBean);
                    req.getRequestDispatcher("/userPage").forward(req,resp);
                }else{
                    req.getSession().setAttribute("errorMessage","Teacher not found");
                    req.getRequestDispatcher("JSP/login.jsp").forward(req, resp);
                }

        }else {
            req.getRequestDispatcher("JSP/login.jsp").forward(req, resp);
        }
    }

    private PRIVILEGE_TYPE resolvePrivilegeType(String privilegeType){
        if(privilegeType.equals("user")){
            return PRIVILEGE_TYPE.user;
        }else if(privilegeType.equals("admin")){
            return PRIVILEGE_TYPE.admin;
        }else if(privilegeType.equals("superadmin")){
            return PRIVILEGE_TYPE.superadmin;
        }else {
            return null;
        }
    }

}
