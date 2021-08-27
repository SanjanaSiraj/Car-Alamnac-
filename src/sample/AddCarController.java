package sample;

import Utils.AddCarToDataBaseData;
import Utils.Car;
import Utils.LogOutData;
import Utils.ReturnDataManufacturer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AddCarController {
    public TextField RegNum;
    public TextField Year;
    public TextField Color1;
    public TextField Color2;
    public TextField Color3;
    public TextField Make;
    public TextField Model;
    public TextField Price;
    public TextField Quantity;
    public Button submitButton;
    private Car car = new Car();
    public Button backButton;
    public Button logOutButton;
    public Button resetButton;
    private Socket s;
    public void setSocket(Socket s){
        this.s=s;
    }

    public void reset(ActionEvent actionEvent) {
        RegNum.clear();
        Year.clear();
        Color1.clear();
        Color2.clear();
        Color3.clear();
        Make.clear();
        Model.clear();
        Price.clear();
        Quantity.clear();
    }

    public void returnHome(ActionEvent actionEvent) {
        ReturnDataManufacturer rdm = new ReturnDataManufacturer();
        rdm.setStatus(true);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeUnshared(rdm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOut(ActionEvent actionEvent) {
        LogOutData lod = new LogOutData();
        lod.setStatus(true);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeUnshared(lod);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Submit(ActionEvent actionEvent) {
        car.setRegistrationNumber(RegNum.getText());
        car.setYearMade(Integer.parseInt(Year.getText()));
        String []str = new String[3];
        str[0]=Color1.getText();
        str[1]=Color2.getText();
        str[2]=Color3.getText();
        car.setColours(str);
        car.setCarMake(Make.getText());
        car.setCarModel(Model.getText());
        car.setPrice(Integer.parseInt(Price.getText()));
        car.setQuantity(Integer.parseInt(Quantity.getText()));
        AddCarToDataBaseData addCarToDataBaseData = new AddCarToDataBaseData();
        addCarToDataBaseData.setC(car);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeUnshared(addCarToDataBaseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
