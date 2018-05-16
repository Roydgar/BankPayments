package ua.training.model.service;

import org.javamoney.moneta.Money;
import ua.training.model.dao.CreditRequestDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.entity.CreditRequest;
import ua.training.util.ConvertUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CreditRequestService{
    CreditRequestDao creditRequestDao = DaoFactory.getInstance().createCreditRequestDao();

    public List<CreditRequest> findByUserId(int userId) {
        return creditRequestDao.findByUserId(userId);
    }

    public void create(int userId, Money moneyAmount) {
        creditRequestDao.create(new CreditRequest.CreditRequestBuilder().setUserId(userId)
        .setDate(LocalDateTime.now()).setMoneyAmount(moneyAmount)
                .setStatus(CreditRequest.Status.NEW).create());
    }

    public Optional<CreditRequest> findById(int id) {
        return creditRequestDao.findById(id);
    }

    public List<CreditRequest> findAll() {
        return creditRequestDao.findAll();
    }

    public void update(CreditRequest entity) {
        creditRequestDao.update(entity);
    }

    public void delete(int id) {
        creditRequestDao.delete(id);
    }

    public void updateStatus(int id, CreditRequest.Status status) {
        creditRequestDao.updateStatus(id, status);
    }
}
