package decisions;

import br.com.objectos.core.Multimap;
import cardGeneration.GenerationMethods;

import java.util.*;

public class CardFigureSetString {

    GenerationMethods generationMethods;
    CardDecision cardDecision;

    public CardFigureSetString() {
    }

    public CardFigureSetString(GenerationMethods generationMethods, CardDecision cardDecision) {
        this.generationMethods = generationMethods;
        this.cardDecision = cardDecision;
    }


    public List<String> introduceFirstCardList(Multimap<String,String> cardMap) {
        CardDecision cardDecision = new CardDecision();
        GenerationMethods generationMethods = new GenerationMethods();
        List<String> cardList = new ArrayList<>();
        for (String s : cardMap.keySet()) {
            cardList.add(s);
        }

        return cardList;
    }
    public List<String> introduceFirstFigureList(Multimap<String,String> cardMap) {
        CardDecision cardDecision = new CardDecision();
        GenerationMethods generationMethods = new GenerationMethods();
        List<String> figureList = new ArrayList<>();
        for (String s : cardMap.keySet()) {
            figureList.add(s);
        }

        return figureList;
    }




    public List<String> addAllCardInList (List<String> originalList, String s){

        List<String> fullCardList = new ArrayList<>();
        fullCardList.addAll(originalList);
        fullCardList.add(s);
        return fullCardList;

    }



    public void showListOfCards(List<String> cardList) {
        for (String card : cardList) {
            System.out.print(card + " ");

        }
    }
}
