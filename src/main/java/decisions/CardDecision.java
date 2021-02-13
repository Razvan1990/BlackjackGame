package decisions;

import cardValues.DefineCardValues;
import cardValues.SumOfCards;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CardDecision {
    public int requestCard(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want a new card?");
        System.out.println("0 - no/ 1- yes");
        int option = scanner.nextInt();

        while(option!=0 && option!=1){
            System.out.println("Not a valid choice.Please choose correctly");
            option = scanner.nextInt();

        }
        return option;
    }

    public int cpuChoose(int value){
        Random random = new Random();
        int cpuDecision = 0;
        if(value ==21){
            cpuDecision = 0;
        }
        else if(value>15 && value <=21){
            cpuDecision =0;
        }
        else if(value<=11){
            cpuDecision =1;
        }

        else{
            cpuDecision = random.nextInt(2);
        }

    return cpuDecision;
    }

    public int cpuChooseAceValue(List<String> cpuList, int value){
        int aceValue =0;
        Random random = new Random();
        for(int i =0;i<cpuList.size();i++){
            if(cpuList.contains("A")){
                if(cpuList.size()<3 && value<=10){
                    aceValue =11;
                    return aceValue;
                }
                else if(cpuList.size()<5 && (value <17 && value >=11)){
                    aceValue =0;
                    return aceValue;
                }
                else {
                    int chooseAceValue = random.nextInt(2);
                    switch (chooseAceValue){
                        case 0:
                            aceValue = 0;
                            break;
                        case 1:
                            aceValue =11;
                            break;
                    }
                    return aceValue;

                }

            }
        }
        return aceValue;
    }
}


