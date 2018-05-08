package ua.training.model.dao;

import ua.training.model.entity.Operation;

import java.util.List;

public interface OperationDao extends GenericDao<Operation> {
    List<Operation> findByAccountId(int accountId);
}
