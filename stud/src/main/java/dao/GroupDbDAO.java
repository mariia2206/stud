package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import domain.Group;
import exception.DAOException;

public class GroupDbDAO implements RepositoryDAO<Group> {

    private static final String SELECT_ALL = "SELECT id, group_name, faculty, course, study_type FROM groups ORDER BY group_name";
    private static final String SELECT_BY_ID = "SELECT id, group_name, faculty, course, study_type FROM groups WHERE id = ?";
    private static final String INSERT = "INSERT INTO groups(group_name, faculty, course, study_type) VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE groups SET group_name = ?, faculty = ?, course = ?, study_type = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM groups WHERE id = ?";

    private ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Group group) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"id"})) {
            pst.setString(1, group.getGroupName());
            pst.setString(2, group.getFaculty());
            pst.setInt(3, group.getCourse());
            pst.setString(4, group.getStudyType());
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
    public void update(Group group) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE)) {
            pst.setString(1, group.getGroupName());
            pst.setString(2, group.getFaculty());
            pst.setInt(3, group.getCourse());
            pst.setString(4, group.getStudyType());
            pst.setLong(5, group.getId());
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
    public Group findById(Long id) throws DAOException {
        Group group = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_BY_ID)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                group = fillGroup(rs);
            }
            rs.close();
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return group;
    }

    @Override
    public List<Group> findAll() throws DAOException {
        List<Group> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillGroup(rs));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    private Group fillGroup(ResultSet rs) throws SQLException {
        Group group = new Group();
        group.setId(rs.getLong("id"));
        group.setGroupName(rs.getString("group_name"));
        group.setFaculty(rs.getString("faculty"));
        group.setCourse(rs.getInt("course"));
        group.setStudyType(rs.getString("study_type"));
        return group;
    }
}