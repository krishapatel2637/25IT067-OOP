import java.util.Scanner;
public class TollBooth {
    record Vehicle(String number, String type) {
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = 0;
        int bike = 0;
        int car = 0;
        int truck = 0;
        while(true){
            System.out.print("Enter Vehicle Number: ");
            String number = sc.nextLine();
            if(number.equalsIgnoreCase("done")){
                break;
            }
            System.out.print("Enter Vehicle Type: ");
            String type = sc.nextLine().toLowerCase();
            Vehicle v = new Vehicle(number, type);
            if(v.type().equals("bike")){
                total += 20;
                bike++;
            } 
            else if(v.type().equals("car")){
                total += 50;
                car++;
            } 
            else if(v.type().equals("truck")){
                total += 150;
                truck++;
            }
        }
        System.out.println("Total Toll Collected = " + total);
        if(bike > car && bike > truck){
            System.out.println("Most Vehicles : Bike");
        }
        else if(car > bike && car > truck){
            System.out.println("Most Vehicles : Car");
        }
        else{
            System.out.println("Most Vehicles : Truck");
        }
        sc.close();
    }
}