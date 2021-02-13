package winner;

import cardValues.SumOfCards;

import java.util.List;

public class CheckWinner {

    public String winnerOfBlackJack(List<Integer> playerList, List<Integer> cpuList){
        SumOfCards sumOfCards = new SumOfCards();
        String s1 = "player";
        String s2 = "cpu";
        int playerScore = sumOfCards.sumOfCardsInHand(playerList, s1);
        int cpuScore = sumOfCards.sumOfCardsInHand(cpuList, s2);

        //check winnings for player

        //1.blackJack player
        if(playerScore ==21 && playerList.size()==2 &&(cpuScore!=21 || cpuList.size()!=2)){
            return "Blackjack player. You won!";
        }
        //2.player1 has blackjack from sum
        else if(playerScore ==21 && cpuScore!=21){
            return "You made 21. Congratulations, you won!";
        }
        //3.player1 has a higher score without passing
        else if(playerScore > cpuScore && playerScore < 22){
            return "You have won. You are closer to 21";
        }
        //4.player1 wins because cpu had more than 21
        else if(playerScore <22 && cpuScore >21){
            return "Opponent has busted. You have won";
        }

        //check winning for cpu

        //1.blackJackCpu
        if(cpuScore ==21 && cpuList.size()==2 &&(playerScore!=21 || playerList.size()!=2)){
            return "Blackjack cpu. Computer won!";
        }
        //2.cpu has blackjack from sum
        else if(cpuScore ==21 && playerScore!=21){
            return "Cpu has 21. The computer beats you";
        }
        //3.cpu has a higher score without passing
        else if(cpuScore > playerScore && cpuScore<22){
            return "Cpu score close to 21. The computer beats you";
        }
        //4.cpu wins because you had more than 21
        else if(cpuScore<22 && playerScore >21) {
            return "You have busted. Cpu won";
        }

        //check draws

        //1. both have blackjack from hand
        else if(playerScore ==cpuScore){
           return "Draw. You have the same scores";
        }
        else if(playerScore>21 && cpuScore>21){
            return "you both have busted! Draw";
        }
        return "";
    }
}
