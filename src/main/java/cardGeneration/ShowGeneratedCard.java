package cardGeneration;

import br.com.objectos.core.ArrayListMultimap;
import br.com.objectos.core.Multimap;

import java.util.HashMap;

public class ShowGeneratedCard {

    GenerationMethods generationMethods;

    public ShowGeneratedCard(){}

    public ShowGeneratedCard(GenerationMethods generationMethods){
        this.generationMethods = generationMethods;
    }

    public void seeGeneratedCard(){
        Multimap<String, String> myMap = ArrayListMultimap.create();
        String card1 = generationMethods.generateRandomCard();
        String figure1 = generationMethods.generateRandomFigure();
        myMap.put(card1, figure1);

        String card2 = generationMethods.generateRandomCard();
        String figure2 = generationMethods.generateRandomFigure();
        myMap.put(card2,figure2);
        generationMethods.showCardCorrectly(myMap);

    }
}
