package ua.training.model.dao.impl;

import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.constants.ColumnNames;
import ua.training.model.dao.impl.constants.Queries;
import ua.training.model.entity.User;

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
                (Queries.USER_CREATE)){

            ps.setString(1 , user.getLogin());
            ps.setString(2 , user.getPassword());
            ps.setString(3 , user.getRole().toString());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(int id) {
        try (PreparedStatement ps = connection.prepareStatement
                (Queries.USER_FIND_BY_ID)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){
                return extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private User extractFromResultSet(ResultSet rs)
            throws SQLException {
        int userId       =  rs.getInt(ColumnNames.USER_ID);
        String login  =  rs.getString(ColumnNames.USER_LOGIN) ;
        String password  =  rs.getString(ColumnNames.USER_PASSWORD);
        String role      =  rs.getString(ColumnNames.USER_ROLE);

        return new User.UserBuilder().setId(userId).setLogin(login).setPassword(password)
                .setRole(User.Role.valueOf(role)).create();
    }

    @Override
    public List<User> findAll() {
        List<User> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()){
            ResultSet rs = ps.executeQuery(Queries.USER_FIND_ALL);

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
                Queries.USER_UPDATE)){
            ps.setString(1 , user.getLogin());
            ps.setString(2 , user.getPassword());
            ps.setString(3 , user.getRole().toString());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement(Queries.USER_DELETE)){
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

    @Override
    public User login(String login, String password) {
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement(Queries.USER_LOGIN)){
            ps.setString(1, login);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if( rs.next() ){
                user = extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public boolean userExists(String login) {
        try (PreparedStatement ps = connection.prepareStatement(Queries.USER_FIND_BY_LOGIN)){
            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            if( rs.next() ){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}