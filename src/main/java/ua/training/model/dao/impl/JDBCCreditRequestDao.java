package ua.training.model.dao.impl;

import ua.training.model.dao.CreditRequestDao;
import ua.training.model.dao.impl.constants.CreditRequestQueries;
import ua.training.model.dao.util.ExtractUtil;
import ua.training.model.entity.CreditRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCCreditRequestDao implements CreditRequestDao {

    private Connection connection;

    JDBCCreditRequestDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<CreditRequest> findByUserId(int userId) {
        List<CreditRequest> resultList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement
                (CreditRequestQueries.FIND_BY_USER_ID)){

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while ( rs.next() ){
                resultList.add(ExtractUtil.extractCreditRequestFromResultSet(rs));
            }

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void create(CreditRequest entity) {
        try (PreparedStatement ps = connection.prepareStatement
                (CreditRequestQueries.CREATE)){

            prepareCreateUpdateQuery(entity, ps);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CreditRequest> findById(int id) {
        Optional<CreditRequest> creditRequest = Optional.empty();
        try (PreparedStatement ps = connection.prepareStatement
                (CreditRequestQueries.FIND_BY_ID)){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if( rs.next() ){
                creditRequest = Optional.of(ExtractUtil.extractCreditRequestFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return creditRequest;
    }

    @Override
    public List<CreditRequest> findAll() {
        List<CreditRequest> resultList = new ArrayList<>();
        try (Statement ps = connection.createStatement()){
            ResultSet rs = ps.executeQuery(CreditRequestQueries.FIND_ALL);

            while ( rs.next() ){
                resultList.add(ExtractUtil.extractCreditRequestFromResultSet(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(CreditRequest entity) {
        try (PreparedStatement ps = connection.prepareStatement(
                CreditRequestQueries.UPDATE)){
            prepareCreateUpdateQuery(entity, ps);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = connection.prepareStatement(CreditRequestQueries.DELETE)){
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void prepareCreateUpdateQuery(CreditRequest creditRequest, PreparedStatement ps) throws SQLException {
        ps.setLong(1 , creditRequest.getMoneyAmount().getNumber().longValue());
        ps.setTimestamp(2, Timestamp.valueOf(creditRequest.getDate()));
        ps.setInt(3, creditRequest.getUserId());
    }
}
