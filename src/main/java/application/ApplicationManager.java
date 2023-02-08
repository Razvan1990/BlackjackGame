package application;

//import br.com.objectos.core.*;
//import br.com.objectos.core.ArrayListMultimap;

import cardGeneration.GenerationMethods;
import cardGeneration.ShowGeneratedCard;
import cardValues.DefineCardValues;
import cardValues.SumOfCards;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import decisions.CardDecision;
import decisions.CardFigureSetString;
import winner.CheckWinner;

import java.util.List;
import java.util.Scanner;

//import com.google.common.collect.ArrayListMultimap;

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
                Multimap<String, String> playerMap = ArrayListMultimap.create();
                String card1Player = generationMethods.generateRandomCard();
                String figure1Player = generationMethods.generateRandomFigure();
                playerMap.put(card1Player, figure1Player);

                String card2Player = generationMethods.generateRandomCard();
                String figure2Player = generationMethods.generateRandomFigure();
                //because of multimap we need to generate first 2 different cards which are not put in the map correctly because of same key

                while (card1Player.equals(card2Player)) {
                    card2Player = generationMethods.generateRandomCard();
                    figure2Player = generationMethods.generateRandomFigure();
                }
                playerMap.put(card2Player, figure2Player);
                generationMethods.showCardCorrectly(playerMap);
                System.out.println();


                //calculator de vazut doar o carte


                List<String> cardsListPlayer = cardFigureSetString.introduceFirstCardList(playerMap);
                List<String> figuresListPlayer = cardFigureSetString.introduceFirstFigureList(playerMap);

                System.out.println("First CPU card");

                Multimap<String, String> cpuMap = ArrayListMultimap.create();
                String card1Cpu = generationMethods.generateRandomCard();
                String figure1Cpu = generationMethods.generateRandomFigure();


                while (cpuMap.containsEntry(card1Cpu, figure1Cpu) || playerMap.containsEntry(card1Cpu, figure1Cpu)) {
                    card1Cpu = generationMethods.generateRandomCard();
                    figure1Cpu = generationMethods.generateRandomFigure();
                }

                cpuMap.put(card1Cpu, figure1Cpu);
                generationMethods.showCardCorrectly(cpuMap);

                String card2Cpu = generationMethods.generateRandomCard();
                String figure2Cpu = generationMethods.generateRandomFigure();

                while (cpuMap.containsEntry(card2Cpu, figure2Cpu) || playerMap.containsEntry(card2Cpu, figure2Cpu)) {
                    card2Cpu = generationMethods.generateRandomCard();
                    figure2Cpu = generationMethods.generateRandomFigure();
                }


                cpuMap.put(card2Cpu, figure2Cpu);

                System.out.println();

                List<String> cardsListCPU = cardFigureSetString.introduceFirstCardList(cpuMap);
                List<String> figuresListCPU = cardFigureSetString.introduceFirstFigureList(cpuMap);


                //player

                int newCardRequest = cardDecision.requestCard();
                //we need temp values to store the new generated figure and card
                while (newCardRequest == 1) {

                    String extraCardPlayer = generationMethods.generateRandomCard();
                    String extraFigurePlayer = generationMethods.generateRandomFigure();
                    while (playerMap.containsEntry(extraCardPlayer, extraFigurePlayer) || cpuMap.containsEntry(extraCardPlayer, extraFigurePlayer)) {
                        System.out.println("Regenerating card!");
                        //add in new list
                        extraCardPlayer = generationMethods.generateRandomCard();
                        extraFigurePlayer = generationMethods.generateRandomFigure();
                    }
                    playerMap.put(extraCardPlayer, extraFigurePlayer);
                    cardsListPlayer = cardFigureSetString.addAllCardInList(cardsListPlayer, extraCardPlayer);
                    figuresListPlayer = cardFigureSetString.addAllCardInList(figuresListPlayer, extraFigurePlayer);
                    generationMethods.showCardCorrectly(playerMap);
                    newCardRequest = cardDecision.requestCard();

                }


                //change String into ints for values of cards
                List<Integer> playerValuesList = defineCardValues.numberCardListPlayer(cardsListPlayer);
                List<Integer> cpuValuesList = defineCardValues.numberCardListCPU_initial(cardsListCPU);
                int cpuSum = sumOfCards.sumOfCardsInHand(cpuValuesList, "cpu");

                int cpuDecision = cardDecision.cpuChoose(cpuSum);

                while (cpuDecision == 1) {

                    String extraCardCpu = generationMethods.generateRandomCard();
                    String extraFigureCpu = generationMethods.generateRandomFigure();
                    while (playerMap.containsEntry(extraCardCpu, extraFigureCpu) || cpuMap.containsEntry(extraCardCpu, extraFigureCpu)) {
                        System.out.println("Regenerating");
                        extraCardCpu = generationMethods.generateRandomCard();
                        extraFigureCpu = generationMethods.generateRandomFigure();
                    }
                    cpuMap.put(extraCardCpu, extraFigureCpu);
                    cardsListCPU = cardFigureSetString.addAllCardInList(cardsListCPU, extraCardCpu);
                    figuresListCPU = cardFigureSetString.addAllCardInList(figuresListCPU, extraFigureCpu);
                    cpuValuesList = defineCardValues.numberCardListCPU_after(cardsListCPU, cpuSum);
                    cpuSum = sumOfCards.sumOfCardsInHand(cpuValuesList, "cpu");
                    cpuDecision = cardDecision.cpuChoose(cpuSum);


                }


                System.out.println("Showing final cards");

                try {
                    Thread thread = new Thread();

                    thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.print("Player's cards ");
                generationMethods.showCardCorrectly(playerMap);
                System.out.println();
                System.out.print("CPU's cards ");
                generationMethods.showCardCorrectly(cpuMap);
                System.out.println();
                System.out.println("Calculating scores");


                try {
                    Thread thread = new Thread();

                    thread.sleep(3000);
                } catch (InterruptedException e) {
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
                Multimap<String, String> playerMap2 = ArrayListMultimap.create();
                String card1Player1 = generationMethods.generateRandomCard();
                String figurePlayer1 = generationMethods.generateRandomFigure();
                playerMap2.put(card1Player1, figurePlayer1);

                String card2Player1 = generationMethods.generateRandomCard();
                String figure2Player1 = generationMethods.generateRandomFigure();
                //because of multimap we need to generate first 2 different cards which are not put in the map correctly because of same key
                while (card1Player1.equals(card2Player1)) {
                    card2Player1 = generationMethods.generateRandomCard();
                    figure2Player1 = generationMethods.generateRandomFigure();
                }
                playerMap2.put(card2Player1, figure2Player1);
                generationMethods.showCardCorrectly(playerMap2);
                System.out.println();
                    

                //calculator de vazut doar o carte


                List<String> cardsListPlayerB = cardFigureSetString.introduceFirstCardList(playerMap2);
                List<String> figuresListPlayerB = cardFigureSetString.introduceFirstFigureList(playerMap2);


                Multimap<String, String> cpuMap2 = ArrayListMultimap.create();
                String card1Cpu2 = generationMethods.generateRandomCard();
                String figure1Cpu2 = generationMethods.generateRandomFigure();


                while(cpuMap2.containsEntry(card1Cpu2, figure1Cpu2)|| playerMap2.containsEntry(card1Cpu2, figure1Cpu2)){
                    card1Cpu2 = generationMethods.generateRandomCard();
                    figure1Cpu2 = generationMethods.generateRandomFigure();
                }

                cpuMap2.put(card1Cpu2, figure1Cpu2);

                String card2Cpu2 = generationMethods.generateRandomCard();
                String figure2Cpu2 = generationMethods.generateRandomFigure();

                while(cpuMap2.containsEntry(card2Cpu2, figure2Cpu2)||playerMap2.containsEntry(card2Cpu2, figure2Cpu2)){
                    card2Cpu2 = generationMethods.generateRandomCard();
                    figure2Cpu2 = generationMethods.generateRandomFigure();
                }

                cpuMap2.put(card2Cpu2, figure2Cpu2);

                System.out.println();

                List<String> cardsListCPU2 = cardFigureSetString.introduceFirstCardList(cpuMap2);
                List<String> figuresListCPU2 = cardFigureSetString.introduceFirstFigureList(cpuMap2);


                //player

                int newCardRequest2 = cardDecision.requestCard();
                //we need temp values to store the new generated figure and card
                while (newCardRequest2 == 1) {

                    String extraCardPlayer = generationMethods.generateRandomCard();
                    String extraFigurePlayer = generationMethods.generateRandomFigure();
                    while (playerMap2.containsEntry(extraCardPlayer, extraFigurePlayer) || cpuMap2.containsEntry(extraCardPlayer, extraFigurePlayer)) {
                        System.out.println("Regenerating card!");
                        //add in new list
                        extraCardPlayer = generationMethods.generateRandomCard();
                        extraFigurePlayer = generationMethods.generateRandomFigure();
                    }
                    playerMap2.put(extraCardPlayer, extraFigurePlayer);
                    cardsListPlayerB = cardFigureSetString.addAllCardInList(cardsListPlayerB, extraCardPlayer);
                    figuresListPlayerB = cardFigureSetString.addAllCardInList(figuresListPlayerB, extraFigurePlayer);
                    generationMethods.showCardCorrectly(playerMap2);
                    newCardRequest2 = cardDecision.requestCard();

                }


                //change String into ints for values of cards
                List<Integer> playerValuesListB = defineCardValues.numberCardListPlayer(cardsListPlayerB);
                List<Integer> cpuValuesListB = defineCardValues.numberCardListCPU_initial(cardsListCPU2);
                int cpuSumB = sumOfCards.sumOfCardsInHand(cpuValuesListB, "cpu");

                int cpuDecision2 = cardDecision.cpuChoose(cpuSumB);

                while (cpuDecision2 == 1) {

                    String extraCardCpu = generationMethods.generateRandomCard();
                    String extraFigureCpu = generationMethods.generateRandomFigure();
                    while (playerMap2.containsEntry(extraCardCpu, extraFigureCpu) || cpuMap2.containsEntry(extraCardCpu, extraFigureCpu)) {
                        System.out.println("Regenerating");
                        extraCardCpu = generationMethods.generateRandomCard();
                        extraFigureCpu = generationMethods.generateRandomFigure();
                    }
                    cpuMap2.put(extraCardCpu, extraFigureCpu);
                    cardsListCPU2 = cardFigureSetString.addAllCardInList(cardsListCPU2, extraCardCpu);
                    figuresListCPU2 = cardFigureSetString.addAllCardInList(figuresListCPU2, extraFigureCpu);
                    cpuValuesListB = defineCardValues.numberCardListCPU_after(cardsListCPU2, cpuSumB);
                    cpuSumB = sumOfCards.sumOfCardsInHand(cpuValuesListB, "cpu");
                    cpuDecision2 = cardDecision.cpuChoose(cpuSumB);
                }

                System.out.println("Showing cards");

                try {
                    Thread thread = new Thread();


                    thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print("Player's cards ");
                generationMethods.showCardCorrectly(playerMap2);
                System.out.println();
                System.out.print("CPU's cards ");
                generationMethods.showCardCorrectly(cpuMap2);
                System.out.println();

                System.out.println("Calculating");

                try {
                    Thread thread = new Thread();

                    thread.sleep(3000);
                } catch (InterruptedException e) {
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
