package ua.training.controller.command;

import ua.training.controller.command.admin.ConfirmCreditRequest;
import ua.training.controller.command.admin.ShowCreditRequests;
import ua.training.controller.command.login.AdminRegistration;
import ua.training.controller.command.login.Login;
import ua.training.controller.command.login.Logout;
import ua.training.controller.command.login.Registration;
import ua.training.model.dao.impl.constants.ColumnNames;
import ua.training.model.service.AccountService;
import ua.training.model.service.CreditRequestService;
import ua.training.model.service.UserService;
import ua.training.util.constants.CommandNames;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandExecutor {
    private Map<String, Command> commands = new ConcurrentHashMap<>();

    private CommandExecutor() {
        commands.put(CommandNames.LOGIN, new Login(new UserService(), new AccountService()));
        commands.put(CommandNames.REGISTRATION, new Registration(new UserService()));
        commands.put(CommandNames.LOGOUT, new Logout());
        commands.put(CommandNames.OPEN_ACCOUNT, new OpenAccount(new AccountService(), new CreditRequestService()));
        commands.put(CommandNames.ADD_USER_TO_ACCOUNT, new AddUserToAccount(new UserService(), new AccountService()));
        commands.put(CommandNames.ADMIN_REGISTRATION, new AdminRegistration(new UserService()));
        commands.put(CommandNames.SHOW_CREDIT_REQUESTS, new ShowCreditRequests(new CreditRequestService()));
        commands.put(CommandNames.CONFIRM_CREDIT_REQUEST, new ConfirmCreditRequest(new CreditRequestService(),
                new AccountService()));
        commands.put(CommandNames.SORT_USER_ACCOUNTS, new SortUserAccounts());
        commands.put(CommandNames.SORT_CREDIT_REQUESTS, new SortCreditRequests());
    }

    public String executeCommand(String command, HttpServletRequest request) {
        return commands.getOrDefault(command, (r)->"index.jsp").execute(request);
    }

    private static class CommandCreatorHolder {
        private static final CommandExecutor instance = new CommandExecutor();
    }

    public static CommandExecutor getInstance() {
        return CommandCreatorHolder.instance;
    }

}
