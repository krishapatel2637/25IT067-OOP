import java.util.Scanner;
public class Part1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int price=15;
        int total=0;
        while (total<price){
            System.out.print("Enter coin (ONE, TWO, FIVE, TEN): ");
            String coin=sc.nextLine();
            if(coin.equalsIgnoreCase("ONE")){
                total+=1;
            }
            else if(coin.equalsIgnoreCase("TWO")){
                total+=2;
            }
            else if(coin.equalsIgnoreCase("FIVE")){
                total+=5;
            }
            else if(coin.equalsIgnoreCase("TEN")){
                total+=10;
            }
            else{
                System.out.println("Invalid Coin");
            }
        }
        System.out.println("Total Amount=" +total);
        System.out.println("Change=" +(total-price));
    }
}