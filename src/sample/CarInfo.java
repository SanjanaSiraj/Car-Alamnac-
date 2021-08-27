package sample;

public class CarInfo {
    private String registrationNumber;
    private int yearMade;
    private String colors;
    private String carMake;
    private String carModel;
    private String price;
    private int truePrice;
    private int quantity;
    CarInfo(String s1,int i,String[] s2,String s3,String s4,int s5,int i2){
        registrationNumber=s1;
        yearMade=i;
        if(s2.length==1) colors=s2[0];
        else if (s2.length==2) colors=s2[0]+","+s2[1];
        else if (s2.length>=3) colors=s2[0]+","+s2[1]+","+s2[2];
        carMake=s3;
        carModel=s4;
        price="$"+String.valueOf(s5);
        truePrice=s5;
        quantity=i2;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public void setYearMade(int yearMade) {
        this.yearMade = yearMade;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarMake() {
        return carMake;
    }

    public int getYearMade() {
        return yearMade;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColors() {
        return colors;
    }

    public String getPrice() {
        return price;
    }

    public int getTruePrice() {
        return truePrice;
    }
}
