package ua.training.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataValidatorTest {

    private String nonNullArgument = "nonNull";
    private String anotherNonNullArgument = "anotherNonNull";
    private String nullArgument = null;
    private String emptyArgument = "";
    private String correctPassword = "Vs8251094";
    private String incorrectPassword = "1231";
    private String correctLogin = "roydgar";
    private String incorrectLogin = "vsd";
    private String correctEmail = "roydgar@gmail.com";
    private String incorrectEmail = "royd";

    @Test
    public void nullArgument() {
        assertTrue(DataValidator.parameterIsEmptyOrNull(nonNullArgument, anotherNonNullArgument, nullArgument));
    }

    @Test
    public void nonNullArgument() {
        assertFalse(DataValidator.parameterIsEmptyOrNull(nonNullArgument));
    }

    @Test
    public void EmptyArgument() {
        assertTrue(DataValidator.parameterIsEmptyOrNull(anotherNonNullArgument, emptyArgument));
    }

    @Test
    public void checkCorrectRegistrationData() {
        assertTrue(DataValidator.checkUserInput(correctLogin, correctPassword, correctEmail));
    }

    @Test
    public void checkIncorrectLogin() {
        assertFalse(DataValidator.checkUserInput(incorrectLogin, correctPassword, correctEmail));
    }

    @Test
    public void checkIncorrectPassword() {
        assertFalse(DataValidator.checkUserInput(correctLogin, incorrectPassword, correctEmail));
    }

    @Test
    public void checkIncorrectEmail() {
        assertFalse(DataValidator.checkUserInput(correctLogin, correctPassword, incorrectEmail));
    }
}
