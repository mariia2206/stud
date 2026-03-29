package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import domain.Student;
import domain.Group;
import exception.DAOException;

public class StudentDbDAO implements RepositoryDAO<Student> {

    private static final String SELECT_ALL = "SELECT id, last_name, first_name, patronymic, birth_date, phone, email, group_id FROM students ORDER BY last_name";
    private static final String SELECT_BY_ID = "SELECT id, last_name, first_name, patronymic, birth_date, phone, email, group_id FROM students WHERE id = ?";
    private static final String INSERT = "INSERT INTO students(last_name, first_name, patronymic, birth_date, phone, email, group_id) VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE students SET last_name = ?, first_name = ?, patronymic = ?, birth_date = ?, phone = ?, email = ?, group_id = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM students WHERE id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    private GroupDbDAO groupDao = new GroupDbDAO();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Student student) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"id"})) {
            pst.setString(1, student.getLastName());
            pst.setString(2, student.getFirstName());
            pst.setString(3, student.getPatronymic());
            pst.setObject(4, student.getBirthDate());
            pst.setString(5, student.getPhone());
            pst.setString(6, student.getEmail());
            
            Long groupId = student.getGroup() != null ? student.getGroup().getId() : null;
            if (groupId != null) {
                pst.setLong(7, groupId);
            } else {
                pst.setNull(7, java.sql.Types.INTEGER);
            }
            
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            Long id = -1L;
            if (rs.next()) {
                id = rs.getLong("id");
            }
            rs.close();
            return id;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Student student) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, student.getLastName());
            pst.setString(2, student.getFirstName());
            pst.setString(3, student.getPatronymic());
            pst.setObject(4, student.getBirthDate());
            pst.setString(5, student.getPhone());
            pst.setString(6, student.getEmail());
            
            Long groupId = student.getGroup() != null ? student.getGroup().getId() : null;
            if (groupId != null) {
                pst.setLong(7, groupId);
            } else {
                pst.setNull(7, java.sql.Types.INTEGER);
            }
            
            pst.setLong(8, student.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Student findById(Long id) throws DAOException {
        Student student = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_BY_ID)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                student = fillStudent(rs);
            }
            rs.close();
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return student;
    }

    @Override
    public List<Student> findAll() throws DAOException {
        List<Student> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillStudent(rs));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    // ИСПРАВЛЕННЫЙ МЕТОД fillStudent
    private Student fillStudent(ResultSet rs) throws SQLException, DAOException {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setLastName(rs.getString("last_name"));
        student.setFirstName(rs.getString("first_name"));
        student.setPatronymic(rs.getString("patronymic"));
        student.setBirthDate(rs.getObject("birth_date", LocalDate.class));
        student.setPhone(rs.getString("phone"));
        student.setEmail(rs.getString("email"));
        
        // Исправление: получаем group_id как int, а не Long
        int groupIdInt = rs.getInt("group_id");
        if (!rs.wasNull() && groupIdInt > 0) {
            Long groupId = (long) groupIdInt;
            Group group = groupDao.findById(groupId);
            student.setGroup(group);
        }
        
        return student;
    }
}