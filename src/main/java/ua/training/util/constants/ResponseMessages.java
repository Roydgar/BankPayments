package ua.training.util.constants;

public interface ResponseMessages {

    String LOGIN_USER_IS_LOGGED         = "login.error.userIsLogged";
    String LOGIN_ERROR                  = "login.error";
    String LOGIN_DOESNT_EXIST           = "login.doesnt.exist";

    String REGISTRATION_PASSWORDS_DONT_MATCH    = "registration.error.passwordsDontMatch";
    String REGISTRATION_USER_EXISTS             = "registration.error.userExists";
    String REGISTRATION_NOT_VALID_PARAMETERS    = "registration.error.notValidParameters";

    String OPEN_ACCOUNT_DENIED          = "openAccount.denied";

    String RECIPIENT_DOESNT_EXIST       = "recipient.doesnt.exist";
    String INSUFFICIENT_FUNDS           = "insufficient.funds";

    String REQUEST_ALREADY_PROCESSED    = "creditRequest.alreadyProcessed";
}
