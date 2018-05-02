package ua.training.model.dao.impl.constants;


import java.util.ResourceBundle;

public interface Queries {
    ResourceBundle bundle = ResourceBundle.getBundle("dao");

    String USER_CREATE         = bundle.getString("query.user.create");
    String USER_FIND_BY_ID     = bundle.getString("query.user.findById");
    String USER_FIND_ALL       = bundle.getString("query.user.findAll");
    String USER_UPDATE         = bundle.getString("query.user.update");
    String USER_DELETE         = bundle.getString("query.user.delete");
    String USER_LOGIN          = bundle.getString("query.user.login");
    String USER_FIND_BY_LOGIN  = bundle.getString("query.user.findByLogin");
}
