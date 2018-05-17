package ua.training.controller.command.user;

import ua.training.controller.command.Command;
import ua.training.model.entity.Account;
import ua.training.model.entity.Operation;
import ua.training.model.service.AccountService;
import ua.training.model.service.OperationService;
import ua.training.util.ConvertUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowOperationHistory implements Command{
    private OperationService operationService;
    public ShowOperationHistory(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Operation> operations = new ArrayList<>();
        for (Account account : (List<Account>)request.getSession().getAttribute(AttributeNames.ACCOUNTS)) {
            operations.addAll(operationService.findByAccountId(account.getId()));
        }

        request.setAttribute(AttributeNames.OPERATIONS,
                ConvertUtil.convertOperationMoneyToDollars(operations));
        return PageURLs.OPERATION_HISTORY;
    }
}
