package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.OperationDao;
import ua.training.model.entity.Operation;

import java.util.List;
import java.util.Optional;

public class OperationService{
    private OperationDao operationDao = DaoFactory.getInstance().createOperationDao();

    public List<Operation> findByAccountId(int accountId) {
        return operationDao.findByAccountId(accountId);
    }

    public void create(Operation entity) {
        operationDao.create(entity);
    }

    public Optional<Operation> findById(int id) {
        return operationDao.findById(id);
    }

    public List<Operation> findAll() {
        return operationDao.findAll();
    }

    public void update(Operation entity) {
        operationDao.update(entity);
    }

    public void delete(int id) {
        operationDao.delete(id);
    }
}
