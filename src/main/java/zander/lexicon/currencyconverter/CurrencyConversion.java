package zander.lexicon.currencyconverter;

public class CurrencyConversion {
    private final Currency input;
    private final Currency output;
    private final double rate;

    public CurrencyConversion(Currency input, Currency output, double rate) {
        this.input = input;
        this.output = output;
        this.rate = rate;
    }

    public CurrencyConversion inverse(){
        return new CurrencyConversion(this.output, this.input, 1 / rate);
    }

    public double convert(double amount){
        return amount * rate;
    }

    public String optionString(){
        return String.format("Convert %s to %s", input, output);
    }

    public String resultString(double amount){
        return String.format("%.3f %s = %.3f %s", amount, this.input, this.convert(amount), this.output);
    }
}
