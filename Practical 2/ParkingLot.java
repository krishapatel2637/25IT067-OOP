class ParkingLot{
    private int two;
    private int four;
    private final int twoCap;
    private final int fourCap;
    private static long money = 0;
    ParkingLot(int twoCap, int fourCap) {
        this.twoCap = twoCap;
        this.fourCap = fourCap;
    }
    void park(String type) {
        if (type.equals("two")) {
            if (two < twoCap) {
                two++;
                money = money + 20;
            } else {
                System.out.println("Full");
            }
        }
        else if (type.equals("four")) {
            if (four < fourCap) {
                four++;
                money = money + 40;
            }
            else {
                System.out.println("Full");
            }
        }
    }
    void leave(String type) {
        if (type.equals("two")) {
            if (two > 0) {
                two--;
            }
        }
        else if (type.equals("four")) {
            if (four > 0) {
                four--;
            }
        }
    }
    public static void main(String args[]) {
        ParkingLot obj = new ParkingLot(2, 2);
        obj.park("two");
        obj.park("two");
        obj.park("two");
        obj.park("four");
        obj.leave("two");
        obj.park("four");
        System.out.println("Two = " + obj.two);
        System.out.println("Four = " + obj.four);
        System.out.println("Revenue = " + money);
    }
}