package ua.training.model.dao.impl;

import ua.training.model.dao.UserDao;
import ua.training.model.dao.impl.constants.UserQueries;
import ua.training.model.dao.util.ExtractUtil;
import ua.training.model.entity.User;
import ua.training.util.LoggerMessageUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(int id){
        Optional<User> user = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement
                (UserQueries.FIND_BY_ID)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if( rs.next() ){
                user = Optional.of(ExtractUtil.extractUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
        return user;
    }



    @Override
    public List<User> findAll() {
        List<User> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()){
            ResultSet rs = ps.executeQuery(UserQueries.FIND_ALL);

            while ( rs.next() ){
                resultList.add(ExtractUtil.extractUserFromResultSet(rs));
            }

        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
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
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception{
        try {
            connection.close();
        } catch (Exception e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> login(String login, String password) {
        Optional<User> user = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement(UserQueries.LOGIN)){
            ps.setString(1, login);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if( rs.next() ){
                user = Optional.of(ExtractUtil.extractUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserByLogin(String login){
        Optional<User> user = Optional.empty();

        try (PreparedStatement ps = connection.prepareStatement(UserQueries.FIND_BY_LOGIN)){
            ps.setString(1, login.toLowerCase());

            ResultSet rs = ps.executeQuery();

            if( rs.next() ){
                user = Optional.of(ExtractUtil.extractUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
        return user;
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
            logger.error(LoggerMessageUtil.daoException(), e);
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