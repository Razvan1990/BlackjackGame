package cardGeneration;

import br.com.objectos.core.ArrayListMultimap;
import br.com.objectos.core.Multimap;
import constant.CardsAndSymbols;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class GenerationMethods {
    public String generateRandomCard() {
        int value = (int) Math.floor(Math.random() * CardsAndSymbols.cards.length);
        return CardsAndSymbols.cards[value];

    }

    public String generateRandomFigure() {
        int value = (int) Math.floor(Math.random() * CardsAndSymbols.symbols.length);
        return CardsAndSymbols.symbols[value];
    }

    public Multimap<String, String> playerCard(String a, String b) {
        Multimap<String, String> cardMap = ArrayListMultimap.create();
        cardMap.put(a, b);
        return cardMap;
    }

    public void showCardCorrectly(Multimap<String, String> map) {
        for (String s : map.keySet()) {
            System.out.print(s + " " + map.get(s));
        }
    }
}

