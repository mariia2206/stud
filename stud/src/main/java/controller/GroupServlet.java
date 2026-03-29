package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.GroupDbDAO;
import domain.Group;
import exception.DAOException;

@WebServlet("/group")
public class GroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        try {
            GroupDbDAO dao = new GroupDbDAO();
            List<Group> groups = dao.findAll();
            request.setAttribute("groups", groups);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        
        request.getRequestDispatcher("/views/group.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            GroupDbDAO dao = new GroupDbDAO();
            
            String groupName = request.getParameter("groupName");
            String faculty = request.getParameter("faculty");
            String courseStr = request.getParameter("course");
            String studyType = request.getParameter("studyType");
            
            Group group = new Group();
            group.setGroupName(groupName);
            group.setFaculty(faculty);
            group.setCourse(Integer.parseInt(courseStr));
            group.setStudyType(studyType);
            
            Long index = dao.insert(group);
            System.out.println("Group added with id: " + index);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect(request.getContextPath() + "/group");
    }
}