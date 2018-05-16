package ua.training.model.dao.impl.constants;

import ua.training.util.constants.PropertyFileNames;

import javax.print.DocFlavor;
import java.util.ResourceBundle;

public interface CreditRequestQueries {
    ResourceBundle bundle = ResourceBundle.getBundle(PropertyFileNames.DAO);

    String CREATE               = bundle.getString("query.creditRequest.create");
    String FIND_BY_ID           = bundle.getString("query.creditRequest.findById");
    String FIND_ALL             = bundle.getString("query.creditRequest.findAll");
    String UPDATE               = bundle.getString("query.creditRequest.update");
    String DELETE               = bundle.getString("query.creditRequest.delete");
    String FIND_BY_USER_ID      = bundle.getString("query.creditRequest.findByUserId");
    String UPDATE_STATUS        = bundle.getString("query.creditRequest.updateStatus");
}
