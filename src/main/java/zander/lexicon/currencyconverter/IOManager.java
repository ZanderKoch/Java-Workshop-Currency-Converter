package zander.lexicon.currencyconverter;

import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class IOManager {
    private final Scanner sc;
    private final PrintStream out;

    public IOManager(InputStream input, OutputStream output) {
        this.sc = new Scanner(input);
        this.out = new PrintStream(output);
    }

    public void printStartMessage(){
        out.println("Currency Converter app");
    }

    public void printCurrentTime() {
        LocalDateTime current = LocalDateTime.now();
        String formattedTime = current.format(DateTimeFormatter.ofPattern("MMMM dd, yyy, HH:mm:ss"));
        out.println(formattedTime);
    }

    /**
     * Prompts user to enter a number corresponding to an option using provided {@link InputStream} and
     * {@link OutputStream} until the user enters a valid one and returns the first valid choise.
     *
     * @return The first valid number the user enters
     */
    public int getOption() {
        int chosenOption = 0;
        while (true) {
            out.println("Enter your choise:");
            String input = sc.nextLine();

            int inputNum;
            try { //check that it's a number
                inputNum = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                out.println("Please enter a numeric integer between 0 and 4 (both inclusive)!");
                continue;
            }

            if (!(0 <= inputNum && inputNum <= 4)) {
                out.println("Please enter a numeric integer between 0 and 4 (both inclusive)!");
            } else {
                chosenOption = inputNum;
                break;
            }
        }
        return chosenOption;
    }

    /**
     * Prompts user to enter the amount of the original currency to be converted using the provided {@link InputStream}
     * and {@link OutputStream} until the user enters a positive number and returns the first one the user enters
     *
     * @return The first positive number the user enters
     */
    public double getOriginalAmount() {
        double chosenOriginalAmount = 0;
        while (true) {
            out.println("Enter amount of original currency:");
            String input = sc.nextLine();

            double inputNum;
            try { //check that it's a number
                inputNum = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                out.println("Please enter a positive numeric number!");
                continue;
            }

            if (inputNum <= 0) {
                out.println("Please enter a positive numeric number!");
            } else {
                chosenOriginalAmount = inputNum;
                break;
            }
        }
        return chosenOriginalAmount;
    }

    public CurrencyConversion getConversionFromMenu () {
        return null;
    }
}
