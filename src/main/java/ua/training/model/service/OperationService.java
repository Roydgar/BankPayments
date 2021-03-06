package ua.training.model.service;

import org.javamoney.moneta.Money;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.OperationDao;
import ua.training.model.entity.Account;
import ua.training.model.entity.Operation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class OperationService{
    private final OperationDao operationDao = DaoFactory.getInstance().createOperationDao();

    public List<Operation> findByAccountId(int accountId) {
        return operationDao.findByAccountId(accountId);
    }

    public void create(int accountId, String recipient, Operation.Type type, Money moneyAmount) {
        operationDao.create(new Operation.OperationBuilder().setAccountId(accountId)
        .setDate(LocalDateTime.now()).setRecipient(recipient).setType(type).
                        setMoneyAmount(moneyAmount).create());
    }

    public void create(int accountId, Account recipient, Money moneyAmount) {
        operationDao.create(new Operation.OperationBuilder().setAccountId(accountId)
                .setDate(LocalDateTime.now()).setRecipient(recipient.getNumber())
                .setType(recipient.getType()== Account.Type.CREDIT ? Operation.Type.LOAN_PAYMENT :
                        Operation.Type.TRANSFER).setMoneyAmount(moneyAmount).create());
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
