package ua.training.model.dao;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;


public interface GenericDao<T> extends AutoCloseable {
    Logger logger = Logger.getRootLogger();

    void create(T entity);
    Optional<T> findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
}