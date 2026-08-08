import java.util.Random;
import java.util.Scanner;
public class Part3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        String[] moves = { "ROCK", "PAPER", "SCISSORS", "LIZARD", "SPOCK" };
        int player = 0;
        int computer = 0;
        for (int i = 1; i <= 5; i++){
            System.out.print("Enter your move: ");
            String user = sc.nextLine().toUpperCase();
            String comp = moves[random.nextInt(5)];
            System.out.println("Computer Move: " + comp);
            if(user.equals(comp)){
                System.out.println("Tie");
            }
            else if ((user.equals("ROCK") && (comp.equals("SCISSORS") || comp.equals("LIZARD"))) ||
                    (user.equals("PAPER") && (comp.equals("ROCK") || comp.equals("SPOCK"))) ||
                    (user.equals("SCISSORS") && (comp.equals("PAPER") || comp.equals("LIZARD"))) ||
                    (user.equals("LIZARD") && (comp.equals("PAPER") || comp.equals("SPOCK"))) ||
                    (user.equals("SPOCK") && (comp.equals("ROCK") || comp.equals("SCISSORS")))) {
                System.out.println("You Win");
                player++;
            }
            else{
                System.out.println("Computer Wins");
                computer++;
            }
        }
        System.out.println("Final Score");
        System.out.println("Player = " + player);
        System.out.println("Computer = " + computer);
        sc.close();
    }
}