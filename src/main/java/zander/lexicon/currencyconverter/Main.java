package zander.lexicon.currencyconverter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        IOManager io = new IOManager(System.in, System.out);

        io.printStartMessage();

        List<CurrencyConversion> permutations = ConversionPermutator.getPermutations(getInitialConversions());
        Menu<CurrencyConversion> conversionMenu = new Menu<CurrencyConversion>(permutations, 5);
        while(true){
            int optionNum = io.getOptionNumFromMenu(conversionMenu);
            if (optionNum == 0){
                break;
            }
            CurrencyConversion chosenConversion = conversionMenu.getCurrentPageItems().get(optionNum-1);

            double originalAmount = io.getOriginalAmount();

            io.convert(chosenConversion,originalAmount);

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
        return conversions;
    }
}