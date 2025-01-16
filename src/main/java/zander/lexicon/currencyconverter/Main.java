package zander.lexicon.currencyconverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Currency Converter app");

        List<CurrencyConversion> permutations = ConversionPermutator.getPermutations(getInitialConversions());
        System.out.println(permutations);

        System.out.println("""
                1. Convert SEK to USD\s
                2. Convert USD to SEK\s
                3. Convert SEK to Euro\s
                4. Convert Euro to SEK\s
                0. Exit
                """);

        while (true) {


            int chosenOption = getOption();
            if (chosenOption == 0) {
                break;
            }

            double chosenOriginalAmount = getOriginalAmount();

            //set conversion rate and currency names based on previously chosen option
            String originalCurrencyName;
            String outputCurrencyName;
            double conversionRate;
            switch (chosenOption) {
                case 1:
                    originalCurrencyName = "SEK";
                    outputCurrencyName = "USD";
                    conversionRate = 0.090;
                    break;
                case 2:
                    originalCurrencyName = "USD";
                    outputCurrencyName = "SEK";
                    conversionRate = 11.16;
                    break;
                case 3:
                    originalCurrencyName = "SEK";
                    outputCurrencyName = "Euro";
                    conversionRate = 0.087;
                    break;
                case 4:
                    originalCurrencyName = "Euro";
                    outputCurrencyName = "SEK";
                    conversionRate = 11.50;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + chosenOption);
            }

            //print result of conversion
            System.out.printf("%.3f %s = %.3f %s\n",
                    chosenOriginalAmount,
                    originalCurrencyName,
                    (chosenOriginalAmount * conversionRate),
                    outputCurrencyName);

            //print current time for some reason
            printCurrentTime();
        }
    }

    private static void printCurrentTime() {
        LocalDateTime current = LocalDateTime.now();
        String formattedTime = current.format(DateTimeFormatter.ofPattern("MMMM dd, yyy, HH:mm:ss"));
        System.out.println(formattedTime);
    }

    /**
     * Prompts user to enter a number corresponding to an option using a system.in scanner
     * until the user enters a valid one and returns the first valid choise.
     *
     * @return The first valid number the user enters
     */
    private static int getOption() {
        Scanner sc = new Scanner(System.in);
        int chosenOption = 0;
        while (true) {
            System.out.println("Enter your choise:");
            String input = sc.nextLine();

            int inputNum;
            try { //check that it's a number
                inputNum = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a numeric integer between 0 and 4 (both inclusive)!");
                continue;
            }

            if (!(0 <= inputNum && inputNum <= 4)) {
                System.out.println("Please enter a numeric integer between 0 and 4 (both inclusive)!");
            } else {
                chosenOption = inputNum;
                break;
            }
        }
        return chosenOption;
    }

    /**
     * Prompts user to enter the amount of the original currency to be converted using a system.in scanner until the
     * user enters a positive number and returns the first one the user enters
     *
     * @return The first positive number the user enters
     */
    private static double getOriginalAmount() {
        Scanner sc = new Scanner(System.in);
        double chosenOriginalAmount = 0;
        while (true) {
            System.out.println("Enter amount of original currency:");
            String input = sc.nextLine();

            double inputNum;
            try { //check that it's a number
                inputNum = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a positive numeric number!");
                continue;
            }

            if (inputNum <= 0) {
                System.out.println("Please enter a positive numeric number!");
            } else {
                chosenOriginalAmount = inputNum;
                break;
            }
        }
        return chosenOriginalAmount;
    }

    /**
     * Returns a list of CurrencyConversions that all have the same base Currency as their input and no duplicate
     * outputs
     * @return a list of CurrencyConversions with the same input and no duplicate outputs.
     */
    private static List<CurrencyConversion> getInitialConversions() {
        Currency base = Currency.USD;

        List<CurrencyConversion> conversions = new ArrayList<>();
        conversions.add(new CurrencyConversion(base, Currency.SEK,11.15 ));
        conversions.add(new CurrencyConversion(base, Currency.EUR, 0.97));
//        conversions.add(new CurrencyConversion(base, Currency.NOK, 11.35));
//        conversions.add(new CurrencyConversion(base, Currency.ISK, 140.89));
//        conversions.add(new CurrencyConversion(base, Currency.DKK, 7.24));
//        conversions.add(new CurrencyConversion(base, Currency.GBP, 0.82));
        return conversions;
    }
}