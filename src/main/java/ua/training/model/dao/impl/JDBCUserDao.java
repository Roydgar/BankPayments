package ua.training.model.dao.impl;

import ua.training.exception.NoResultFromDbException;
import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.constants.ColumnNames;
import ua.training.model.dao.impl.constants.UserQueries;
import ua.training.model.entity.User;
import ua.training.util.constants.ExceptionMessages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {

    private Connection connection;

    JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        try (PreparedStatement ps = connection.prepareStatement
                (UserQueries.CREATE)){

            prepareCreateUpdateQuery(user, ps);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(int id) throws NoResultFromDbException {
        try (PreparedStatement ps = connection.prepareStatement
                (UserQueries.FIND_BY_ID)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                return extractFromResultSet(rs);
            } else {
                throw new NoResultFromDbException(ExceptionMessages.NO_RESULT_FROM_DB);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User extractFromResultSet(ResultSet rs)
            throws SQLException {
        int userId       =  rs.getInt(ColumnNames.USER_ID);
        String login  =  rs.getString(ColumnNames.USER_LOGIN);
        String email     = rs.getString(ColumnNames.USER_EMAIL);
        String password  =  rs.getString(ColumnNames.USER_PASSWORD);
        String role      =  rs.getString(ColumnNames.USER_ROLE);
        return new User.UserBuilder().setId(userId).setLogin(login).setEmail(email).setPassword(password)
                .setRole(User.Role.valueOf(role.toUpperCase())).create();
    }

    @Override
    public List<User> findAll() {
        List<User> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()){
            ResultSet rs = ps.executeQuery(UserQueries.FIND_ALL);

            while ( rs.next() ){
                resultList.add(extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(User user) {
        try (PreparedStatement ps = connection.prepareStatement(
                UserQueries.UPDATE)){
            prepareCreateUpdateQuery(user, ps);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement(UserQueries.DELETE)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Todo: think about null
    @Override
    public User login(String login, String password) throws NoResultFromDbException {
        try (PreparedStatement ps = connection.prepareStatement(UserQueries.LOGIN)){
            ps.setString(1, login);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if( rs.next() ){
                return extractFromResultSet(rs);
            } else {
                throw new NoResultFromDbException(ExceptionMessages.LOGIN_FAILED);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean userExists(String login) {
        try (PreparedStatement ps = connection.prepareStatement(UserQueries.FIND_BY_LOGIN)){
            ps.setString(1, login.toLowerCase());

            ResultSet rs = ps.executeQuery();

            if( rs.next() ){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private void prepareCreateUpdateQuery(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1 , user.getLogin().toLowerCase());
        ps.setString(2, user.getEmail().toLowerCase());
        ps.setString(3 , user.getPassword());
        ps.setString(4 , user.getRole().toString());
    }
}