package ua.training.controller.command.user;

import org.junit.Before;
import org.junit.Test;
import ua.training.model.entity.Operation;
import ua.training.model.service.AccountService;
import ua.training.model.service.OperationService;
import ua.training.util.constants.AttributeNames;
import ua.training.util.constants.PageURLs;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowOperationHistoryTest {

    private ShowOperationHistory showOperationHistoryCommand;
    private OperationService operationService;
    private HttpServletRequest request;
    private HttpSession session;
    private ServletContext servletContext;
    private int userId;

    @Before
    public void init() {
        operationService = mock(OperationService.class);
        showOperationHistoryCommand = new ShowOperationHistory(operationService);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
        servletContext = mock(ServletContext.class);
        userId = 0;
    }


    @Test
    public void successfulRegistration() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(AttributeNames.ACCOUNTS)).thenReturn(new ArrayList<Operation>());

        String page = showOperationHistoryCommand.execute(request);
        assertNotNull(page);
        assertEquals(page, PageURLs.OPERATION_HISTORY);
    }
}
