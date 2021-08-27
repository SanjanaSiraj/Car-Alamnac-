package Utils;

import java.io.Serializable;

public class Car implements Serializable {
    private String registrationNumber;
    private int yearMade;
    private String[] colours = new String[3];
    private String carMake;
    private String carModel;
    private int price;
    private int quantity;

    public Car(){
        registrationNumber=null;
        yearMade=0;
        colours=null;
        carMake=null;
        carModel=null;
        price=0;
        quantity=0;
    }

    public Car(String regNumber, int year, String[] str, String make, String model, int pri,int quantity){
        registrationNumber=regNumber;
        yearMade=year;
        colours=str;
        carMake=make;
        carModel=model;
        this.price=pri;
        this.quantity=quantity;
    }
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setYearMade(int yearMade){
        this.yearMade = yearMade;
    }

    public void setColours(String[] colours) {
        this.colours = colours;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getYearMade() {
        return yearMade;
    }

    public String[] getColours() {
        return colours;
    }

    public String getCarMake() {
        return carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
