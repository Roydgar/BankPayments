package ua.training.model.dao.impl.constants;

import ua.training.util.constants.PropertyFileNames;

import java.util.ResourceBundle;

public interface UserHasAccountQueries {
    ResourceBundle bundle = ResourceBundle.getBundle(PropertyFileNames.DAO);

    String CREATE = bundle.getString("query.userHasAccount.create");
    String FIND_ACCOUNTS_BY_USER_ID = bundle.getString("query.userHasAccount.findAccountsByUserId");
    String FIND_USERS_BY_ACCOUNT_ID = bundle.getString("query.userHasAccount.findUsersByAccountId");
    String FIND_BY_USER_AND_ACCOUNT_ID = bundle.getString("query.userHasAccount.findByUserAndAccountId");
}
