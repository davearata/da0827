package com.davea.demo;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RentalIT {

    static final String helpText = String.format("" +
    "Usage: rental [-hV] -c=<checkoutDate> -d=<discountPercent> -r=<rentalDayCount>%n" +
    "              -t=<toolCode>%n" +
    "Generate a Rental Agreement from Checkout.%n" +
    "  -c, --checkoutdate=<checkoutDate>%n" +
    "                  Date to checkout tools in yyyy-MM-dd format%n" +
    "  -d, --discountpercent=<discountPercent>%n" +
    "                  Amount of discount to apply as a whole number 0-100(e.g. 20%n" +
    "                      is 20%%)%n" +
    "  -h, --help      Show this help message and exit.%n" +
    "  -r, --rentaldaycount=<rentalDayCount>%n" +
    "                  The number of days for which the customer wants to rent the%n" +
    "                      tool. (e.g. 4days)%n" +
    "  -t, --toolcode=<toolCode>%n" +
    "                  The Tool Code to rent CHNS, LADW, JAKD or JAKR%n" +
    "  -V, --version   Print version information and exit.%n").replaceAll("\\s", "").replaceAll("\\n", "");

    static final String executable = "target/davea-demo" + extension();

    private static String extension() {
        return System.getProperty("os.name").toLowerCase().startsWith("win") ? ".exe" : "";
    }

    @Test
    public void testUsageHelp() throws IOException, InterruptedException {
        final Process process = new ProcessBuilder(executable, "--help").start();

        final String expected = helpText;
        assertEquals(expected, getStdOut(process).replaceAll("\\s", "").replaceAll("\\n", ""));
        assertEquals("", getStdErr(process));
        process.waitFor(3, TimeUnit.SECONDS);
        assertEquals(0, process.exitValue());
    }

    @Test
    public void testBadDiscount() throws IOException, InterruptedException {
        final Process process = new ProcessBuilder(executable, "-c", "2015-09-03", "-d", "101", "-r", "5", "-t", "JAKR").start();

        process.waitFor(3, TimeUnit.SECONDS);
        assertEquals(2, process.exitValue());
    }

    @Test
    public void testVersionInfo() throws IOException, InterruptedException {
        final Process process = new ProcessBuilder(executable, "--version").start();

        final String expected = String.format("rental 1.0%n");

        assertEquals(expected, getStdOut(process));
        assertEquals("", getStdErr(process));
        process.waitFor(3, TimeUnit.SECONDS);
        assertEquals(0, process.exitValue());
    }

    private String getStdOut(Process process) throws IOException {
        return readFully(process.getInputStream());
    }

    private String getStdErr(Process process) throws IOException {
        return readFully(process.getErrorStream());
    }

    private String readFully(InputStream in) throws IOException {
        byte[] buff = new byte[10 * 1024];
        int len = 0;
        int total = 0;
        while ((len = in.read(buff, total, buff.length - total)) > 0) {
            total += len;
        }
        return new String(buff, 0, total);
    }
}
