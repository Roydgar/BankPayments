package ua.training.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataValidatorTest {

    private String nonNullArgument = "nonNull";
    private String anotherNonNullArgument = "anotherNonNull";
    private String nullArgument = null;
    private String emptyArgument = "";

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
}
