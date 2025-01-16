package zander.lexicon.currencyconverter;

import java.util.ArrayList;
import java.util.List;

public class ConversionPermutator {

    public static List<CurrencyConversion> getPermutations(List<CurrencyConversion> initialConversions){
        List<CurrencyConversion> derivedList = new ArrayList<>();
        /*Initial conversions list is like this:
        *  A -> B
        *  A -> C
        *  A -> D
        *  ...
        * */

        //go through list of base currency -> other currency conversions
        for (int i = 0; i < initialConversions.size(); i++){
            /*1st iteration: B -> A,
            * 2nd iteration: C -> A,
            * 3rd iteration: D -> A,
            * all the way until the nth
            * */
            CurrencyConversion outerInverted = initialConversions.get(i).inverse();
            derivedList.add(outerInverted);

            for (int j = 0; j < initialConversions.size(); j++){
                if(j == i){
                    continue;
                }

                /*Each iteration of this loop we get:
                 * A -> B except we already skipped this whole iteration!,
                 * A -> C,
                 * A -> D,
                 * ...
                 * */
                CurrencyConversion inner = initialConversions.get(j);

                /*B -> A -> B = B which is why we already skipped it,
                * B -> A -> C = B -> C,
                * B -> A -> D = B -> D,
                * [... n-3 more times],
                * C -> A -> B = B -> C,
                * C -> A -> C = C which is why we already skipped it,
                * C -> A -> D = C -> D
                * ...
                */
                double derived = inner.convert(outerInverted.convert(1));
                derivedList.add(new CurrencyConversion(outerInverted.getInput(), inner.getOutput(), derived));
            }
        }
        //combine the two lists
        List<CurrencyConversion> result = new ArrayList<>();
        result.addAll(initialConversions);
        result.addAll(derivedList);

        return result;
    }
}
