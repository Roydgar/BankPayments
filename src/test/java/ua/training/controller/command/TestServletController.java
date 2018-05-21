package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.junit.Before;
import org.junit.Test;
import ua.training.controller.ServletController;
import ua.training.util.constants.PageURLs;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class TestServletController {

    private ServletController servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private CommandExecutor commandExecutor;

    @Before
    public void init() {
        servlet = new ServletController();
        response = mock(HttpServletResponse.class);
        request = mock(HttpServletRequest.class);
        dispatcher = mock(RequestDispatcher.class);
        commandExecutor = mock(CommandExecutor.class);
    }

    @Test
    public void doGet() {
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getRequestURI()).thenReturn(PageURLs.INDEX);
        when(commandExecutor.executeCommand(anyString(), anyObject())).thenReturn(PageURLs.INDEX);

        try {
            servlet.doGet(request, response);
            verify(dispatcher).forward(request, response);
        } catch (Exception e) {
            LogManager.getRootLogger().error(e);
        }
    }

    @Test
    public void doPost() {
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(request.getRequestURI()).thenReturn(PageURLs.INDEX);
        when(commandExecutor.executeCommand(anyString(), anyObject())).thenReturn(PageURLs.INDEX);

        try {
            servlet.doPost(request, response);
            verify(dispatcher).forward(request, response);
        } catch (Exception e) {
            LogManager.getRootLogger().error(e);
        }
    }
}
