package cardValues;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DefineCardValues {


    public List<Integer> numberCardListPlayer(List<String> cardsListP) {

        List<Integer> valuesList = new ArrayList<>();
        int x = 0;
        for (int i = 0; i < cardsListP.size(); i++) {
            String card = cardsListP.get(i);
            switch (card) {
                case "2":
                    x = 2;
                    valuesList.add(x);
                    break;
                case "3":
                    x = 3;
                    valuesList.add(x);
                    break;
                case "4":
                    x = 4;
                    valuesList.add(x);
                    break;
                case "5":
                    x = 5;
                    valuesList.add(x);
                    break;
                case "6":
                    x = 6;
                    valuesList.add(x);
                    break;
                case "7":
                    x = 7;
                    valuesList.add(x);
                    break;
                case "8":
                    x = 8;
                    valuesList.add(x);
                    break;
                case "9":
                    x = 9;
                    valuesList.add(x);
                    break;
                case "A":
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("What value does your ace want to be? 1 or 11.Please write the number");
                    int aceValue = scanner.nextInt();

                    while (aceValue != 11 && aceValue != 1) {
                        System.out.println("Not a valid number. Please enter again");
                        aceValue = scanner.nextInt();

                    }
                    switch (aceValue) {
                        case 1:
                            x = 1;
                            valuesList.add(x);
                            break;
                        case 11:
                            x = 11;
                            valuesList.add(x);
                            break;
                    }
                    break;
                default:
                    x = 10;
                    valuesList.add(x);
                    break;


            }

        }
        return valuesList;
    }

    public List<Integer> numberCardListCPU_initial(List<String> cardsListCpuPart1) {

        List<Integer> valuesList = new ArrayList<>();
        int x = 0;
        for (int i = 0; i < cardsListCpuPart1.size(); i++) {
            String card = cardsListCpuPart1.get(i);
            switch (card) {
                case "2":
                    x = 2;
                    valuesList.add(x);
                    break;
                case "3":
                    x = 3;
                    valuesList.add(x);
                    break;
                case "4":
                    x = 4;
                    valuesList.add(x);
                    break;
                case "5":
                    x = 5;
                    valuesList.add(x);
                    break;
                case "6":
                    x = 6;
                    valuesList.add(x);
                    break;
                case "7":
                    x = 7;
                    valuesList.add(x);
                    break;
                case "8":
                    x = 8;
                    valuesList.add(x);
                    break;
                case "9":
                    x = 9;
                    valuesList.add(x);
                    break;
                case "A":
                    Random random = new Random();
                    int chooseFirst = random.nextInt(2);
                    switch (chooseFirst) {
                        case 0:
                            x = 1;
                            valuesList.add(x);
                            break;
                        case 1:
                            x = 11;
                            valuesList.add(x);
                            break;

                    }
                    break;


                default:
                    x = 10;
                    valuesList.add(x);
                    break;


            }

        }
        return valuesList;
    }

    public List<Integer> numberCardListCPU_after(List<String> cardsListCpuPart2, int sum) {

        List<Integer> valuesList = new ArrayList<>();
        int x = 0;
        for (int i = 0; i < cardsListCpuPart2.size(); i++) {
            String card = cardsListCpuPart2.get(i);
            switch (card) {
                case "2":
                    x = 2;
                    valuesList.add(x);
                    break;
                case "3":
                    x = 3;
                    valuesList.add(x);
                    break;
                case "4":
                    x = 4;
                    valuesList.add(x);
                    break;
                case "5":
                    x = 5;
                    valuesList.add(x);
                    break;
                case "6":
                    x = 6;
                    valuesList.add(x);
                    break;
                case "7":
                    x = 7;
                    valuesList.add(x);
                    break;
                case "8":
                    x = 8;
                    valuesList.add(x);
                    break;
                case "9":
                    x = 9;
                    valuesList.add(x);
                    break;
                case "A":
                    if (cardsListCpuPart2.size() < 4 && sum <= 10) {
                        x = 11;
                        valuesList.add(x);
                        break;
                    } else if (cardsListCpuPart2.size() < 5 && (sum < 17 && sum >= 11)) {
                        x = 1;
                        valuesList.add(x);
                        break;
                    } else {
                        Random random = new Random();
                        int chooseAceValue = random.nextInt(2);
                        switch (chooseAceValue) {
                            case 0:
                                x = 1;
                                valuesList.add(x);
                                break;
                            case 1:
                                x = 11;
                                valuesList.add(x);
                                break;
                        }
                    }
                    break;


                default:
                    x = 10;
                    valuesList.add(x);
                    break;


            }

        }
        return valuesList;
    }


    public void showCardNumberList(List<Integer> myList) {
        for (int i : myList) {
            System.out.print(i + " ");
        }
    }
}

