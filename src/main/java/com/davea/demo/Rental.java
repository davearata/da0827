package com.davea.demo;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParameterException;
import picocli.CommandLine.Spec;

import jakarta.validation.constraints.*;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.Callable;

@Command(name = "rental", mixinStandardHelpOptions = true, version = "rental 1.0",
         description = "Generate a Rental Agreement from Checkout.")
public class Rental implements Callable<Integer> {

    @Pattern(regexp = "^(CHNS|LADW|JAKD|JAKR)$",  message = "Valid Tool Codes are CHNS, LADW, JAKD or JAKR")
    @Option(names = {"-t", "--toolcode"}, required = true, description = "The Tool Code to rent CHNS, LADW, JAKD or JAKR")
    private String toolCode;

    @Min(value = 1, message = "Rental Day Count must be at least 1")
    @Option(names = {"-r", "--rentaldaycount"}, required = true, description = "The number of days for which the customer wants to rent the tool. (e.g. 4days)")
    private int rentalDayCount;

    @Min(value = 0, message = "Discount Percent can not be less than 0")
    @Max(value = 100, message = "Discount Percent can not be greater than 100")
    @Option(names = {"-d", "--discountpercent"}, required = true, description = "Amount of discount to apply as a whole number 0-100(e.g. 20 is 20%%)")
    private int discountPercent;

    @Option(names = {"-c", "--checkoutdate"}, required = true, description = "Date to checkout tools in yyyy-MM-dd format")
    private LocalDate checkoutDate;

    @Spec
    CommandSpec spec;

    @Override
    public Integer call() throws Exception {
        validate();
        try {
            final RentalSummary rentalSummary = Checkout.checkout(toolCode, rentalDayCount, checkoutDate, discountPercent);
            rentalSummary.printSummary();
            return 0;
        } catch (Exception error) {
            error.printStackTrace();
            return 1;
        }
    }

    private void validate() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        final Set<ConstraintViolation<Rental>> violations = validator.validate(this);

        if (!violations.isEmpty()) {
            String errorMsg = "";
            for (ConstraintViolation<Rental> violation : violations) {
                errorMsg += "ERROR: " + violation.getMessage() + "\n";
            }
            throw new ParameterException(spec.commandLine(), errorMsg);
        }
    }

    public static void main(String... args) {
        final int exitCode = new CommandLine(new Rental()).execute(args);
        System.exit(exitCode);
    }
}