package zander.lexicon.currencyconverter;

public enum Currency {
    //taken from https://en.wikipedia.org/wiki/ISO_4217#Active_codes_(list_one)
    USD ("United States dollars"),
    SEK ("Swedish kronor"),
    EUR ("Euro"),
    DKK ("Danish kroner"),
    NOK ("Norwegian kroner"),
    ISK ("Icelandic krónur"),
    GBP ("Pound sterling");


    public final String name;

    private Currency(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return super.name();
    }
}
