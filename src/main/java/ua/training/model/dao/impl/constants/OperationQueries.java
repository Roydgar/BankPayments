package ua.training.model.dao.impl.constants;

import ua.training.util.constants.PropertyFileNames;

import java.util.ResourceBundle;

public interface OperationQueries{
    ResourceBundle bundle = ResourceBundle.getBundle(PropertyFileNames.DAO);

    String CREATE               = bundle.getString("query.operation.create");
    String FIND_BY_ID           = bundle.getString("query.operation.findById");
    String FIND_ALL             = bundle.getString("query.operation.findAll");
    String UPDATE               = bundle.getString("query.operation.update");
    String DELETE               = bundle.getString("query.operation.delete");
    String FIND_BY_ACCOUNT_ID   = bundle.getString("query.operation.findByAccountId");
}
