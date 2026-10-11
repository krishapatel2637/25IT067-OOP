class Thermostat {
    private String location;
    private int temp;
    private static final int MIN = 16;
    private static final int MAX = 30;
    private static int count = 0;
    Thermostat(String location, int temp) {
        this.location = location;
        if(temp>=MIN && temp<=MAX)
            this.temp=temp;
        else
            this.temp=22;

        count++;
    }
    Thermostat(String location){
        this(location,22);
    }
    void raise(){
        if(temp<MAX){
            temp++;
        }
        else{
            System.out.println("Already at maximum");
        }
    }
    void lower(){
        if(temp>MIN){
            temp--;
        }
        else{
            System.out.println("Already at minimum");
        }
    }
    int getTemperature(){
        return temp;
    }
    static int getActiveCount(){
        return count;
    }
    public static void main(String args[]){
        Thermostat obj1=new Thermostat("Hall");
        Thermostat obj2=new Thermostat("Bedroom",25);
        for(int i=0;i<10;i++){
            obj1.raise();
            System.out.println(obj1.getTemperature());

        }
        for(int i=0;i<20;i++){
            obj1.lower();
            System.out.println(obj1.getTemperature());

        }
        System.out.println("Count = "+getActiveCount());
    }
}