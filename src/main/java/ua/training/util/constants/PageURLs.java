package ua.training.util.constants;

public interface PageURLs {
    String INDEX          = "/index.jsp";
    String INDEX_REDIRECT = "redirect/index.jsp";

    String LOGIN          = "view/login/login.jsp";
    String REGISTRATION   = "view/login/registration.jsp";
    String ADMIN_REGISTRATION   = "/view/admin/admin-registration.jsp";

    String USER_MENU            = "/view/user/user-menu.jsp";
    String REDIRECT_USER_MENU   = "/redirect:view/user/user-menu.jsp";
    String ADMIN_MENU           = "/view/admin/admin-menu.jsp";
    String REDIRECT_ADMIN_MENU  = "/redirect:view/admin/admin-menu.jsp";

    String ACCOUNT_INFO   = "/view/user/account-info.jsp";

    String OPEN_ACCOUNT   = "/view/user/open-account.jsp";
    String ADD_USER_TO_ACCOUNT = "view/user/add-user-to-account.jsp";
    String SHOW_CREDIT_REQUESTS = "/view/admin/show-credit-requests.jsp";
    String DO_OPERATION         = "/view/user/do-operation.jsp";
    String OPERATION_HISTORY    = "/view/user/operation-history.jsp";
    String ERROR          = "/error.jsp";
}
