package ua.training.model.dao.impl.constants;

import ua.training.util.constants.PropertyFileNames;

import java.util.ResourceBundle;

public interface AccountQueries {
    ResourceBundle bundle = ResourceBundle.getBundle(PropertyFileNames.DAO);

    String CREATE                  = bundle.getString("query.account.create");
    String FIND_BY_ID              = bundle.getString("query.account.findById");
    String FIND_ALL                = bundle.getString("query.account.findAll");
    String UPDATE                  = bundle.getString("query.account.update");
    String DELETE                  = bundle.getString("query.account.delete");
    String UPDATE_BALANCE          = bundle.getString("query.account.updateBalance");
    String UPDATE_ACCRUED_INTEREST = bundle.getString("query.account.updateAccruedInterest");
    String FIND_BY_NUMBER          = bundle.getString("query.account.findByNumber");
}
