package servlets;

import models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;

@WebServlet("/userPage")
public class UserPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserBean userBean = req.getSession().getAttribute("userBean") != null ? (UserBean)req.getSession().getAttribute("userBean") : null;
        LinkedList<String[]> data = null;
        LinkedList<String[]> chooserData = null;

        if (userBean != null && userBean.getUserType() == USER_TYPE.student && userBean.getStateType() == STATE_TYPE.confirmed) {

            data = MySQLConnector.getConnector().selectQuery("allCoursesForStudentID", ((UserBean) req.getSession().getAttribute("userBean")).getId());
            chooserData = data;
            if(req.getParameter("courseChooserSubmit")!=null){
                data = MySQLConnector.getConnector().selectQuery("allPeopleInCourse",  req.getParameter("courseChooserChoiceId"));

            }
            req.setAttribute("data", data);
            req.setAttribute("chooserData", chooserData);
            req.getRequestDispatcher("views/userPage.jsp").forward(req, resp);

        }else if (userBean != null && userBean.getUserType() == USER_TYPE.teacher && userBean.getPrivilegeType()==PRIVILEGE_TYPE.user && userBean.getStateType() == STATE_TYPE.confirmed) {
            //TODO If statement for sub-nav options
            if(req.getParameter("showAllStudents") != null) {
                data = MySQLConnector.getConnector().selectQuery("allFromStudents");
            }else if(req.getParameter("showAllCourses") != null) {
                data = MySQLConnector.getConnector().selectQuery("allFromCourses");

            }else if(req.getParameter("showAllTeachers") != null) {
                data = MySQLConnector.getConnector().selectQuery("allFromTeachers");

            }else if(req.getParameter("showCoursesForTeacher") != null ) {
                data = MySQLConnector.getConnector().selectQuery("allFromTeachers");
                chooserData = data;//MySQLConnector.getConnector().selectQuery("allFromTeachers");
            }else if(req.getParameter("teacherChooserSubmit") != null ) {
                data = MySQLConnector.getConnector().selectQuery("allCoursesForTeacherID", req.getParameter("teacherChooserChoiceId") );
                chooserData = MySQLConnector.getConnector().selectQuery("allFromTeachers");

            }else if(req.getParameter("showCoursesForStudent") != null){
                data = MySQLConnector.getConnector().selectQuery("allFromStudents");
                chooserData = data;//MySQLConnector.getConnector().selectQuery("allFromTeachers");
            }else if(req.getParameter("studentChooserSubmit") != null ) {
                data = MySQLConnector.getConnector().selectQuery("allCoursesForStudentID", req.getParameter("studentChooserChoiceId") );
                chooserData = MySQLConnector.getConnector().selectQuery("allFromStudents");
            }

            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()){
                String[] paramVals = req.getParameterValues(paramNames.nextElement());
                for (String s : paramVals) {
                    System.out.println(s+" - ");
                }

            }

            req.setAttribute("chooserData", chooserData);
            req.setAttribute("data", data);
            req.getRequestDispatcher("views/userPage.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("views/login.jsp").forward(req, resp);
        }
    }
}
