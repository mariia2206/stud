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

@WebServlet("/editGroup")
public class EditGroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String strId = request.getParameter("id");
            Long id = null;
            if (strId != null && !strId.isEmpty()) {
                id = Long.parseLong(strId);
            }
            
            GroupDbDAO dao = new GroupDbDAO();
            Group editGroup = dao.findById(id);
            List<Group> groups = dao.findAll();
            
            request.setAttribute("groupEdit", editGroup);
            request.setAttribute("groups", groups);
            
        } catch (DAOException e) {
            e.printStackTrace();
        }
        
        request.getRequestDispatcher("/views/editGroup.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            GroupDbDAO dao = new GroupDbDAO();
            
            String strId = request.getParameter("id");
            Long id = Long.parseLong(strId);
            
            String groupName = request.getParameter("groupName");
            String faculty = request.getParameter("faculty");
            String courseStr = request.getParameter("course");
            String studyType = request.getParameter("studyType");
            
            Group group = new Group();
            group.setId(id);
            group.setGroupName(groupName);
            group.setFaculty(faculty);
            group.setCourse(Integer.parseInt(courseStr));
            group.setStudyType(studyType);
            
            dao.update(group);
            System.out.println("Group updated with id: " + id);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect(request.getContextPath() + "/group");
    }
}