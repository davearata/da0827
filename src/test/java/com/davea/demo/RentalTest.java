package com.davea.demo;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import picocli.CommandLine;

public class RentalTest {
    @Test
    public void testMissingRequiredParamGivesExitCode2() {
        int exitCode = new CommandLine(new Rental()).setErr(devNull()).execute();
        assertEquals(CommandLine.ExitCode.USAGE, exitCode);
    }

    @Test
    public void testBadDiscount() {
        final String[] args = {"-c", "2015-09-03", "-d", "101", "-r", "5", "-t", "JAKR"};
        int exitCode = new CommandLine(new Rental()).setErr(devNull()).execute(args);
        assertEquals(CommandLine.ExitCode.USAGE, exitCode);
    }

    private PrintWriter devNull() {
        return new PrintWriter(new StringWriter());
    }
}
