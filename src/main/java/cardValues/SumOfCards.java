package cardValues;

import java.util.List;

public class SumOfCards {

    public int sumOfCardsInHand(List<Integer>numberCardsList, String name){
        int sum  =0;

        for(int i =0;i<numberCardsList.size();i++){
            sum+=numberCardsList.get(i);
        }
        return sum;
    }
}
