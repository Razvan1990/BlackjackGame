package application;

import br.com.objectos.core.ArrayListMultimap;
import br.com.objectos.core.Multimap;
import cardGeneration.GenerationMethods;
import cardGeneration.ShowGeneratedCard;
import cardValues.DefineCardValues;
import cardValues.SumOfCards;
import decisions.CardDecision;
import decisions.CardFigureSetString;
import winner.CheckWinner;

import java.util.List;
import java.util.Scanner;

public class ApplicationManager {
    public void manageGame() {


        GenerationMethods generationMethods = new GenerationMethods();
        ShowGeneratedCard showGeneratedCard = new ShowGeneratedCard(generationMethods);
        CardDecision cardDecision = new CardDecision();
        CardFigureSetString cardFigureSetString = new CardFigureSetString();
        DefineCardValues defineCardValues = new DefineCardValues();
        SumOfCards sumOfCards = new SumOfCards();
        CheckWinner checkWinner = new CheckWinner();

        System.out.println("Hello! Welcome to the Blackjack program");
        System.out.println("Please select what type of blackjack you want");
        System.out.println("1.With dealer. You will see the first card of the CPU");
        System.out.println("2.Without dealer. Just you vs CPU, but you will see the CPU cards at showdown");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        while (option != 1 && option != 2) {
            System.out.println("Wrong decision.Please reformulate");
            option = scanner.nextInt();
        }
        switch (option) {
            case 1:
                Multimap<String, String> myMap = ArrayListMultimap.create();
                String card1 = generationMethods.generateRandomCard();
                String figure1 = generationMethods.generateRandomFigure();
                myMap.put(card1, figure1);

                String card2 = generationMethods.generateRandomCard();
                String figure2 = generationMethods.generateRandomFigure();
                //because of multimap we need to generate first 2 different cards which are not put in the map correctly because of same key
                if (!card1.equals(card2)) {
                    myMap.put(card2, figure2);
                    generationMethods.showCardCorrectly(myMap);
                    System.out.println();
                } else {
                    card2 = generationMethods.generateRandomCard();
                    figure2 = generationMethods.generateRandomFigure();
                    myMap.put(card2, figure2);
                    generationMethods.showCardCorrectly(myMap);
                    System.out.println();


                }


                //calculator de vazut doar o carte


                List<String> cardsListPlayer = cardFigureSetString.introduceFirstCardList(myMap);
                List<String> figuresListPlayer = cardFigureSetString.introduceFirstFigureList(myMap);

                System.out.println("First CPU card");

                Multimap<String, String> cpuMap = ArrayListMultimap.create();
                String card4 = generationMethods.generateRandomCard();
                String figure4 = generationMethods.generateRandomFigure();


                while ((figure4.equals(figure1) && card4.equals(card1)) || (figure4.equals(figure2) && card4.equals(card2))) {
                    System.out.println("Regenerating");
                    card4 = generationMethods.generateRandomCard();
                    figure4 = generationMethods.generateRandomFigure();
                }

                cpuMap.put(card4, figure4);
                generationMethods.showCardCorrectly(cpuMap);

                String card5 = generationMethods.generateRandomCard();
                String figure5 = generationMethods.generateRandomFigure();

                if (card4.equals(card5)) {
                    while ((figure5.equals(figure1) && card5.equals(card1)) || (figure5.equals(figure2) && card5.equals(card2)) ||
                            (figure5.equals(figure4) && card5.equals(card4))) {
                        System.out.println("Regenerating");
                        card5 = generationMethods.generateRandomCard();
                        figure5 = generationMethods.generateRandomFigure();
                    }
                }

                cpuMap.put(card5, figure5);

                System.out.println();

                List<String> cardsListCPU = cardFigureSetString.introduceFirstCardList(cpuMap);
                List<String> figuresListCPU = cardFigureSetString.introduceFirstFigureList(cpuMap);


                //player

                int newCardRequest = cardDecision.requestCard();
                //we need temp values to store the new generated figure and card
                String tempCard = "";
                String tempFigure = "";
                while (newCardRequest == 1) {

                    String card3 = generationMethods.generateRandomCard();
                    String figure3 = generationMethods.generateRandomFigure();
                    if ((figure3.equals(figure1) && card3.equals(card1)) || (figure3.equals(figure2) && card3.equals(card2)) ||
                            (cardsListPlayer.contains(card3) && figuresListPlayer.contains(figure3))) {
                        System.out.println("try again because a same card has been generated");
                    } else {
                        //add in new list
                        myMap.put(card3, figure3);
                        tempCard = card3;
                        tempFigure = figure3;

                        cardsListPlayer = cardFigureSetString.addAllCardInList(cardsListPlayer, card3);
                        figuresListPlayer = cardFigureSetString.addAllCardInList(figuresListPlayer, figure3);

                    }

                    generationMethods.showCardCorrectly(myMap);

                    newCardRequest = cardDecision.requestCard();


                }


                //change String into ints for values of cards
                List<Integer> playerValuesList = defineCardValues.numberCardListPlayer(cardsListPlayer);
                List<Integer> cpuValuesList = defineCardValues.numberCardListCPU_initial(cardsListCPU);
                int cpuSum = sumOfCards.sumOfCardsInHand(cpuValuesList, "cpu");

                int cpuDecision = cardDecision.cpuChoose(cpuSum);

                while (cpuDecision == 1) {

                    String card6 = generationMethods.generateRandomCard();
                    String figure6 = generationMethods.generateRandomFigure();
                    if ((figure6.equals(figure1) && card6.equals(card1)) || (figure6.equals(figure2) && card6.equals(card2)) ||
                            (figure6.equals(tempFigure) && card6.equals(tempCard)) || (figure6.equals(figure4) && card6.equals(card4)) ||
                            (figure6.equals(figure5) && card6.equals(card5)) ||
                            (cardsListCPU.contains(card6) && figuresListCPU.contains(card6)) //||
                        //(cardsListPlayer.contains(card6) && figuresListPlayer.contains(card6))
                    ) {
                        System.out.println("Regenerating");

                    } else {
                        cpuMap.put(card6, figure6);
                        cardsListCPU = cardFigureSetString.addAllCardInList(cardsListCPU, card6);
                        figuresListCPU = cardFigureSetString.addAllCardInList(figuresListCPU, figure6);
                        cpuValuesList = defineCardValues.numberCardListCPU_after(cardsListCPU, cpuSum);
                        cpuSum = sumOfCards.sumOfCardsInHand(cpuValuesList, "cpu");
                        cpuDecision = cardDecision.cpuChoose(cpuSum);


                    }
                }

                System.out.println("Showing final cards");

                try{
                Thread thread = new Thread();

                thread.sleep(2500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                System.out.print("Player's cards ");
                generationMethods.showCardCorrectly(myMap);
                System.out.println();
                System.out.print("CPU's cards ");
                generationMethods.showCardCorrectly(cpuMap);
                System.out.println();
                System.out.println("Calculating scores");


                try{
                    Thread thread = new Thread();

                    thread.sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }


                System.out.print("Player score ");
                System.out.print(sumOfCards.sumOfCardsInHand(playerValuesList, "player"));
                System.out.println();
                System.out.print("Cpu score ");
                System.out.print(sumOfCards.sumOfCardsInHand(cpuValuesList, "cpu"));
                System.out.println();


                System.out.println(checkWinner.winnerOfBlackJack(playerValuesList, cpuValuesList));


                break;
            case 2:
                Multimap<String, String> myMap2 = ArrayListMultimap.create();
                String card1b = generationMethods.generateRandomCard();
                String figure1b = generationMethods.generateRandomFigure();
                myMap2.put(card1b, figure1b);

                String card2b = generationMethods.generateRandomCard();
                String figure2b = generationMethods.generateRandomFigure();
                //because of multimap we need to generate first 2 different cards which are not put in the map correctly because of same key
                if (!card1b.equals(card2b)) {
                    myMap2.put(card2b, figure2b);
                    generationMethods.showCardCorrectly(myMap2);
                    System.out.println();
                } else {
                    card2b = generationMethods.generateRandomCard();
                    figure2b = generationMethods.generateRandomFigure();
                    myMap2.put(card2b, figure2b);
                    generationMethods.showCardCorrectly(myMap2);
                    System.out.println();


                }


                //calculator de vazut doar o carte


                List<String> cardsListPlayerB = cardFigureSetString.introduceFirstCardList(myMap2);
                List<String> figuresListPlayerB = cardFigureSetString.introduceFirstFigureList(myMap2);


                Multimap<String, String> cpuMap2 = ArrayListMultimap.create();
                String card4b = generationMethods.generateRandomCard();
                String figure4b = generationMethods.generateRandomFigure();


                while ((figure4b.equals(figure1b) && card4b.equals(card1b)) || (figure4b.equals(figure2b) && card4b.equals(card2b))) {
                    System.out.println("Regenerating");
                    card4b = generationMethods.generateRandomCard();
                    figure4b = generationMethods.generateRandomFigure();
                }

                cpuMap2.put(card4b, figure4b);

                String card5b = generationMethods.generateRandomCard();
                String figure5b = generationMethods.generateRandomFigure();

                if (card4b.equals(card5b)) {
                    while ((figure5b.equals(figure1b) && card5b.equals(card1b)) || (figure5b.equals(figure2b) && card5b.equals(card2b)) ||
                            (figure5b.equals(figure4b) && card5b.equals(card4b))) {
                        System.out.println("Regenerating");
                        card5b = generationMethods.generateRandomCard();
                        figure5b = generationMethods.generateRandomFigure();
                    }
                }

                cpuMap2.put(card5b, figure5b);

                System.out.println();

                List<String> cardsListCPU2 = cardFigureSetString.introduceFirstCardList(cpuMap2);
                List<String> figuresListCPU2 = cardFigureSetString.introduceFirstFigureList(cpuMap2);


                //player

                int newCardRequest2 = cardDecision.requestCard();
                //we need temp values to store the new generated figure and card
                String tempCard2 = "";
                String tempFigure2 = "";
                while (newCardRequest2 == 1) {

                    String card3b = generationMethods.generateRandomCard();
                    String figure3b = generationMethods.generateRandomFigure();
                    if ((figure3b.equals(figure1b) && card3b.equals(card1b)) || (figure3b.equals(figure2b) && card3b.equals(card2b)) ||
                            (cardsListPlayerB.contains(card3b) && figuresListPlayerB.contains(figure3b))) {
                        System.out.println("try again because a same card has been generated");
                    } else {
                        //add in new list
                        myMap2.put(card3b, figure3b);
                        tempCard2 = card3b;
                        tempFigure2 = figure3b;

                        cardsListPlayerB = cardFigureSetString.addAllCardInList(cardsListPlayerB, card3b);
                        figuresListPlayerB = cardFigureSetString.addAllCardInList(figuresListPlayerB, figure3b);

                    }

                    generationMethods.showCardCorrectly(myMap2);

                    newCardRequest2 = cardDecision.requestCard();


                }


                //change String into ints for values of cards
                List<Integer> playerValuesListB = defineCardValues.numberCardListPlayer(cardsListPlayerB);
                List<Integer> cpuValuesListB = defineCardValues.numberCardListCPU_initial(cardsListCPU2);
                int cpuSumB = sumOfCards.sumOfCardsInHand(cpuValuesListB, "cpu");

                int cpuDecision2 = cardDecision.cpuChoose(cpuSumB);

                while (cpuDecision2 == 1) {

                    String card6b = generationMethods.generateRandomCard();
                    String figure6b = generationMethods.generateRandomFigure();
                    if ((figure6b.equals(figure1b) && card6b.equals(card1b)) || (figure6b.equals(figure2b) && card6b.equals(card2b)) ||
                            (figure6b.equals(tempFigure2) && card6b.equals(tempCard2)) || (figure6b.equals(figure4b) && card6b.equals(card4b)) ||
                            (figure6b.equals(figure5b) && card6b.equals(card5b)) ||
                            (cardsListCPU2.contains(card6b) && figuresListCPU2.contains(card6b)) //||
                        //(cardsListPlayer.contains(card6) && figuresListPlayer.contains(card6))
                    ) {
                        System.out.println("Regenerating");

                    } else {
                        cpuMap2.put(card6b, figure6b);
                        cardsListCPU2 = cardFigureSetString.addAllCardInList(cardsListCPU2, card6b);
                        figuresListCPU2 = cardFigureSetString.addAllCardInList(figuresListCPU2, figure6b);
                        cpuValuesList = defineCardValues.numberCardListCPU_after(cardsListCPU2, cpuSumB);
                        cpuSumB = sumOfCards.sumOfCardsInHand(cpuValuesList, "cpu");
                        cpuDecision2 = cardDecision.cpuChoose(cpuSumB);


                    }
                }

                System.out.println("Showing cards");

                try {
                    Thread thread = new Thread();


                    thread.sleep(2500);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.print("Player's cards ");
                generationMethods.showCardCorrectly(myMap2);
                System.out.println();
                System.out.print("CPU's cards ");
                generationMethods.showCardCorrectly(cpuMap2);
                System.out.println();

                System.out.println("Calculating");

                try{
                    Thread thread = new Thread();

                    thread.sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                System.out.print("Player score ");
                System.out.print(sumOfCards.sumOfCardsInHand(playerValuesListB, "player"));
                System.out.println();
                System.out.print("Cpu score ");
                System.out.print(sumOfCards.sumOfCardsInHand(cpuValuesListB, "cpu"));
                System.out.println();


                System.out.println(checkWinner.winnerOfBlackJack(playerValuesListB, cpuValuesListB));
                break;


        }
    }
}

//