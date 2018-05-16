package ua.training.model.dao;

import ua.training.model.entity.CreditRequest;

import java.util.List;


public interface CreditRequestDao extends GenericDao<CreditRequest> {
    List<CreditRequest> findByUserId(int userId);
    void updateStatus(int id, CreditRequest.Status status);
}
