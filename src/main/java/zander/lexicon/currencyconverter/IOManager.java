package zander.lexicon.currencyconverter;

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

    public void printStartMessage() {
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

    public <T extends MenuItem> int getOptionNumFromMenu(Menu<T> menu) {
        while (true) {
            boolean prevPageAvailable = menu.getCurrentPage() > 0;
            boolean nextPageAvailable = menu.getCurrentPage() < menu.getPageCount() - 1;
            String validOptions = buildValidOptionsMessage(prevPageAvailable, nextPageAvailable);

            //print items on current menu page
            for (int i = 0; i < menu.getCurrentPageItems().size(); i++ ){
                out.printf("%d: %s\n", i + 1, menu.getCurrentPageItems().get(i).getDisplayString());
            }

            out.printf("0: Exit,\n%s%s\n",
                    prevPageAvailable ? "P: Previous page " : "",
                    nextPageAvailable ? "N: Next page" : "");

            String input = sc.nextLine().trim();

            try {
                // Check if the input is a valid number
                int selection = Integer.parseInt(input);
                if (selection >= 0 && selection <= menu.getCurrentPageItems().size()) {
                    return selection;
                } else {
                    // Number is outside valid range
                    out.printf("Invalid choice! Please enter a number between 0 and %d%s\n",
                            menu.getCurrentPageItems().size(), validOptions);
                }
            } catch (NumberFormatException e) {
                // Handle non-numeric input
                if (input.equalsIgnoreCase("p") && prevPageAvailable) {
                    menu.setCurrentPage(menu.getCurrentPage() - 1);
                } else if (input.equalsIgnoreCase("n") && nextPageAvailable) {
                    menu.setCurrentPage(menu.getCurrentPage() + 1);
                } else {
                    // Invalid non-numeric input
                    out.printf("Invalid input! Enter a number between 0 and %d%s\n",
                            menu.getCurrentPageItems().size(), validOptions);
                }
            }
        }
    }

    //helper method to construct the valid options message
    private String buildValidOptionsMessage(boolean prevPageAvailable, boolean nextPageAvailable) {
        if (prevPageAvailable && nextPageAvailable) {
            return ", \"P\", or \"N\"";
        } else if (prevPageAvailable) {
            return ", or \"P\"";
        } else if (nextPageAvailable) {
            return ", or \"N\"";
        } else {
            return "";
        }
    }

    /**
     * Actually prints 50 blank lines to simulate the effect since I could not find a simple way to clear the console.
     */
    private void clearConsole() {
        StringBuilder sb = new StringBuilder().repeat("\n", 50);
        out.print(sb);
    }

    public void convert(CurrencyConversion conversion, double amount){
        out.println(conversion.resultString(amount));
    }

}
