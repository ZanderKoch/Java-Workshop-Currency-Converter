package zander.lexicon.currencyconverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Currency Converter app");

        System.out.println("""
                1. Convert SEK to USD\s
                2. Convert USD to SEK\s
                3. Convert SEK to Euro\s
                4. Convert Euro to SEK\s
                0. Exit
                """);

        boolean exiting = false;
        while (!exiting) {

            Scanner sc = new Scanner(System.in);

            //ask user what to do
            int chosenOption = 0;
            boolean optionChosen = false;
            while (!optionChosen) {
                System.out.println("Enter your choise:");
                String input = sc.nextLine();

                int inputNum;
                try { //check that it's a number
                    inputNum = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a numeric integer between 0 and 4 (both inclusive)!");
                    continue;
                }

                if(inputNum == 0){
                    exiting = true;
                    break;
                }

                if (!(0 <= inputNum && inputNum <= 4)) {
                    System.out.println("Please enter a numeric integer between 0 and 4 (both inclusive)!");
                } else {
                    chosenOption = inputNum;
                    optionChosen = true;
                }
            }
            //abort immediately if they wanted to exit
            if (exiting){
                break;
            }

            //ask user for amount of original currency
            double chosenOriginalAmount = 0;
            boolean originalAmountChosen = false;
            while (!originalAmountChosen) {
                System.out.println("Enter amount of original currency:");
                String input = sc.nextLine();

                double inputNum;
                try { //check that it's a number
                    inputNum = Double.parseDouble(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a positive numeric  number!");
                    continue;
                }

                if (inputNum <= 0){
                    System.out.println("Please enter a positive numeric  number!");
                } else {
                    chosenOriginalAmount = inputNum;
                    originalAmountChosen = true;
                }
            }

            //set conversion rate and currency names based on previously chosen option
            String originalCurrencyName;
            String outputCurrencyName;
            double conversionRate;
            switch (chosenOption){
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
            System.out.printf("%.3f %s = %f %s\n",
                    chosenOriginalAmount,
                    originalCurrencyName,
                    (chosenOriginalAmount * conversionRate),
                    outputCurrencyName);

            //print current time for some reason
            LocalDateTime current = LocalDateTime.now();
            String formattedTime = current.format(DateTimeFormatter.ofPattern("MMMM dd, yyy, HH:mm:ss"));
            System.out.println(formattedTime);
        }
    }


}