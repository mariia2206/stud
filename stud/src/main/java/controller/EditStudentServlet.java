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

@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String strId = request.getParameter("id");
            Long id = null;
            if (strId != null && !strId.isEmpty()) {
                id = Long.parseLong(strId);
            }
            
            StudentDbDAO studentDao = new StudentDbDAO();
            GroupDbDAO groupDao = new GroupDbDAO();
            
            Student editStudent = studentDao.findById(id);
            List<Group> groups = groupDao.findAll();
            List<Student> students = studentDao.findAll();
            
            request.setAttribute("studentEdit", editStudent);
            request.setAttribute("groups", groups);
            request.setAttribute("students", students);
            
        } catch (DAOException e) {
            e.printStackTrace();
        }
        
        request.getRequestDispatcher("/views/editStudent.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            StudentDbDAO dao = new StudentDbDAO();
            
            String strId = request.getParameter("id");
            Long id = Long.parseLong(strId);
            
            String lastName = request.getParameter("lastname");
            String firstName = request.getParameter("firstname");
            String patronymic = request.getParameter("patronymic");
            String birthDateStr = request.getParameter("birthDate");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String groupIdStr = request.getParameter("groupId");
            
            Student student = new Student();
            student.setId(id);
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
            
            dao.update(student);
            System.out.println("Student updated with id: " + id);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect(request.getContextPath() + "/student");
    }
}