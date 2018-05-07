package ua.training.controller.command;

import ua.training.controller.command.login.Login;
import ua.training.controller.command.login.Logout;
import ua.training.controller.command.login.Registration;
import ua.training.model.service.AccountService;
import ua.training.model.service.UserService;
import ua.training.util.constants.CommandNames;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandExecutor {
    private Map<String, Command> commands = new ConcurrentHashMap<>();

    public CommandExecutor() {
        UserService userService = new UserService();
        AccountService accountService = new AccountService();

        commands.put(CommandNames.LOGIN, new Login(userService));
        commands.put(CommandNames.REGISTRATION, new Registration(userService));
        commands.put(CommandNames.LOGOUT, new Logout());
        commands.put(CommandNames.OPEN_ACCOUNT, new OpenAccount(accountService));
    }


    public String executeCommand(String command, HttpServletRequest request) {
        return commands.getOrDefault(command, (r)->"index.jsp").execute(request);
    }
}
