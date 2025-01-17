package zander.lexicon.currencyconverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        IOManager io = new IOManager(System.in, System.out);

        io.foobarbaz();

        io.printStartMessage();

        List<CurrencyConversion> permutations = ConversionPermutator.getPermutations(getInitialConversions());
        System.out.println(permutations);
        System.out.println(permutations.size());

        System.out.println("""
                1. Convert SEK to USD\s
                2. Convert USD to SEK\s
                3. Convert SEK to Euro\s
                4. Convert Euro to SEK\s
                0. Exit
                """);

        while (true) {


            int chosenOption = io.getOption();
            if (chosenOption == 0) {
                break;
            }

            double chosenOriginalAmount = io.getOriginalAmount();

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
            io.printCurrentTime();
        }
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
        conversions.add(new CurrencyConversion(base, Currency.DKK, 7.24));
        conversions.add(new CurrencyConversion(base, Currency.GBP, 0.82));
        return conversions;
    }
}