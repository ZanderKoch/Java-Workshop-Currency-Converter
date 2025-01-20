package zander.lexicon.currencyconverter;

public class CurrencyConversion implements MenuItem {
    private final Currency input;
    private final Currency output;

    public Currency getInput() {
        return input;
    }

    public Currency getOutput() {
        return output;
    }

    public double getRate() {
        return rate;
    }

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

    public String getOptionString(){
        return String.format("Convert %s to %s", input.getName(), output.getName());
    }

    public String resultString(double amount){
        return String.format("%.3f %s = %.3f %s", amount, this.input, this.convert(amount), this.output);
    }

    @Override
    public String toString() {
        return String.format("CurrencyConversion{%s -> %s @ %.3f}", input, output, rate);
    }

    public String getDisplayString() {
        return getOptionString();
    }
}
