package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.GroupDbDAO;
import exception.DAOException;

@WebServlet("/deleteGroup")
public class DeleteGroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        GroupDbDAO dao = new GroupDbDAO();
        
        String strId = request.getParameter("id");
        Long deleteId = null;
        
        if (strId != null && !strId.isEmpty()) {
            deleteId = Long.parseLong(strId);
        }
        
        try {
            dao.delete(deleteId);
            System.out.println("Group deleted with id: " + deleteId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        
        response.sendRedirect(request.getContextPath() + "/group");
    }
}