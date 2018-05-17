package ua.training.controller.command;

import ua.training.controller.annotation.CommandWithName;
import ua.training.controller.command.admin.ConfirmCreditRequest;
import ua.training.controller.command.admin.ShowCreditRequests;
import ua.training.controller.command.admin.AddNewAdmin;
import ua.training.controller.command.login.Login;
import ua.training.controller.command.login.Logout;
import ua.training.controller.command.login.Registration;
import ua.training.controller.command.user.*;
import ua.training.model.dao.impl.constants.ColumnNames;
import ua.training.model.service.AccountService;
import ua.training.model.service.CreditRequestService;
import ua.training.model.service.OperationService;
import ua.training.model.service.UserService;
import ua.training.util.constants.CommandNames;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandExecutor {
    private Map<String, Command> commands = new ConcurrentHashMap<>();

    private CommandExecutor() {
        initCommand(new Login(new UserService(), new AccountService()));
        initCommand(new Registration(new UserService()));
        initCommand(new Logout());
        initCommand(new OpenAccount(new AccountService(), new CreditRequestService()));
        initCommand(new AddUserToAccount(new UserService(), new AccountService()));
        initCommand(new AddNewAdmin(new UserService()));
        initCommand(new ShowCreditRequests(new CreditRequestService()));
        initCommand(new ConfirmCreditRequest(new CreditRequestService(), new AccountService()));
        initCommand(new DoOperation(new OperationService(), new AccountService()));
        initCommand(new ShowAccounts(new AccountService()));
        initCommand(new ShowOperationHistory(new OperationService()));
    }

    private void initCommand(Command command) {
        Class clazz = command.getClass();
        CommandWithName annotation = (CommandWithName) clazz.getAnnotation(CommandWithName.class);
        String location = annotation.name();
        commands.put(location, command);
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
