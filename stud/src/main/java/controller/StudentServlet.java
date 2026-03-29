package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import dao.StudentDbDAO;
import dao.GroupDbDAO;
import domain.Student;
import domain.Group;
import exception.DAOException;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        
        try {
            StudentDbDAO studentDao = new StudentDbDAO();
            GroupDbDAO groupDao = new GroupDbDAO();
            
            List<Student> students = studentDao.findAll();
            List<Group> groups = groupDao.findAll();
            
            request.setAttribute("students", students);
            request.setAttribute("groups", groups);
            
        } catch (DAOException e) {
            e.printStackTrace();
        }
        
        request.getRequestDispatcher("/views/student.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            StudentDbDAO dao = new StudentDbDAO();
            
            String lastName = request.getParameter("lastname");
            String firstName = request.getParameter("firstname");
            String patronymic = request.getParameter("patronymic");
            String birthDateStr = request.getParameter("birthDate");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String groupIdStr = request.getParameter("groupId");
            
            Student student = new Student();
            student.setLastName(lastName);
            student.setFirstName(firstName);
            student.setPatronymic(patronymic);
            student.setBirthDate(LocalDate.parse(birthDateStr));
            student.setPhone(phone);
            student.setEmail(email);
            
            if (groupIdStr != null && !groupIdStr.isEmpty()) {
                Group group = new Group();
                group.setId(Long.parseLong(groupIdStr));
                student.setGroup(group);
            }
            
            Long index = dao.insert(student);
            System.out.println("Student added with id: " + index);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect(request.getContextPath() + "/student");
    }
}