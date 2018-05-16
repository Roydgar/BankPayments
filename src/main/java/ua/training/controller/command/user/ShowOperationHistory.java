package ua.training.controller.command.user;

import ua.training.controller.command.Command;
import ua.training.model.service.OperationService;
import ua.training.util.ConvertUtil;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.http.HttpServletRequest;

public class ShowOperationHistory implements Command{
    private OperationService operationService;

    public ShowOperationHistory(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(AttributeNames.OPERATIONS,
                ConvertUtil.convertOperationMoneyToDollars(operationService.findAll()));
        return PageURLs.OPERATION_HISTORY;
    }
}
