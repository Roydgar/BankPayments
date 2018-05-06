package ua.training.model.dao.impl.constants;


import java.util.ResourceBundle;

public interface UserQueries {
    ResourceBundle bundle = ResourceBundle.getBundle("dao");

    String CREATE        = bundle.getString("query.user.create");
    String FIND_BY_ID    = bundle.getString("query.user.findById");
    String FIND_ALL      = bundle.getString("query.user.findAll");
    String UPDATE        = bundle.getString("query.user.update");
    String DELETE        = bundle.getString("query.user.delete");
    String LOGIN         = bundle.getString("query.user.login");
    String FIND_BY_LOGIN = bundle.getString("query.user.findByLogin");

}
