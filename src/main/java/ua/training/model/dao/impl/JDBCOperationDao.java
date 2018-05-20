package ua.training.model.dao.impl;

import ua.training.model.dao.OperationDao;
import ua.training.model.dao.impl.constants.OperationQueries;
import ua.training.model.dao.util.ExtractUtil;
import ua.training.model.entity.Operation;
import ua.training.util.LoggerMessageUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCOperationDao implements OperationDao {

    private final Connection connection;

    JDBCOperationDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Operation> findByAccountId(int accountId) {
        List<Operation> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement
                (OperationQueries.FIND_BY_ACCOUNT_ID)){

            ps.setInt(1,accountId);
            ResultSet rs = ps.executeQuery();

            while ( rs.next() ){
                resultList.add(ExtractUtil.extractOperationFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void create(Operation entity) {
        try (PreparedStatement ps = connection.prepareStatement
                (OperationQueries.CREATE)){

            prepareCreateUpdateQuery(entity, ps);

            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Operation> findById(int id){
        Optional<Operation> operation = Optional.empty();

        try (PreparedStatement ps = connection.prepareStatement
                (OperationQueries.FIND_BY_ID)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if( rs.next() ){
                operation = Optional.of(ExtractUtil.extractOperationFromResultSet(rs));
            }
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
        return operation;
    }

    @Override
    public List<Operation> findAll() {
        List<Operation> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()){
            ResultSet rs = ps.executeQuery(OperationQueries.FIND_ALL);

            while ( rs.next() ){
                resultList.add(ExtractUtil.extractOperationFromResultSet(rs));
            }

        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(Operation entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                OperationQueries.UPDATE)){
            prepareCreateUpdateQuery(entity, ps);

            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement(OperationQueries.DELETE)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (Exception e) {
            logger.error(LoggerMessageUtil.daoException(), e);
            throw new RuntimeException(e);
        }
    }

    private void prepareCreateUpdateQuery(Operation operation, PreparedStatement ps) throws SQLException {
        ps.setString(1 , operation.getRecipient());
        ps.setString(2, operation.getType().toString());
        ps.setTimestamp(3 , Timestamp.valueOf(operation.getDate()));
        ps.setInt(4 , operation.getAccountId());
        ps.setLong(5, operation.getMoneyAmount().getNumber().longValue());
    }
}
