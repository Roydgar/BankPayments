package ua.training.model.dao.impl.constants;

public interface ColumnNames {
    String USER_ID       = "user_id";
    String USER_LOGIN    = "login";
    String USER_EMAIL    = "email";
    String USER_PASSWORD = "password";
    String USER_ROLE     = "role";

    String ACCOUNT_ID               = "account_id";
    String ACCOUNT_NUMBER           = "number";
    String ACCOUNT_BALANCE          = "balance";
    String ACCOUNT_CREATION_DATE    = "creation_date";
    String ACCOUNT_VALIDITY_DATE    = "validity_date";
    String ACCOUNT_RATE             = "rate";
    String ACCOUNT_ACCRUED_INTEREST = "accrued_interest";
    String ACCOUNT_LIMIT            = "limit";
    String ACCOUNT_TYPE             = "account_type";

    String OPERATION_ID             = "operation_id";
    String OPERATION_RECIPIENT      = "recipient";
    String OPERATION_DATE           = "date";
    String OPERATION_TYPE           = "operation_type";
    String OPERATION_MONEY_AMOUNT   = "money_amount";

    String CREDIT_REQUEST_ID           = "credit_request_id";
    String CREDIT_REQUEST_MONEY_AMOUNT = "money_amount";
    String CREDIT_REQUEST_DATE         = "date";
    String CREDIT_REQUEST_STATUS       = "request_status";
    String CREDIT_REQUEST_USER_LOGIN   = "user_login";
}
