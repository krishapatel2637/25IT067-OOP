public class Customer implements Cloneable {
    String name;
    Address address;
    Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }
    public Address getAddress() {
        return address;
    }
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public static class Address {
        String line;
        String city;
        String pincode;
        Address(String line, String city, String pincode) {
            this.line = line;
            this.city = city;
            this.pincode = pincode;
        }
        public String getLine() {
            return line;
        }
        public String getCity() {
            return city;
        }
        public String getPincode() {
            return pincode;
        }
    }
}