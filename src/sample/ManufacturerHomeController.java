package sample;

import Utils.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ManufacturerHomeController {
    public Button viewCarsButton;
    public Button addCarButton;
    public Button editCarButton;
    public Button deleteACarButton;
    public Button logOutButton;
    Socket s;
    Main m;

    public void setSocket(Socket s){
        this.s = s;
    }

    public void viewAllCars(ActionEvent actionEvent) {
        viewAllCarsData vacd = new viewAllCarsData();
        vacd.setStatus(true);
        vacd.setPrevious("ManufacturerHome");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeUnshared(vacd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCar(ActionEvent actionEvent) {
        AddCarData acd = new AddCarData();
        acd.setStatus(true);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeUnshared(acd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editCar(ActionEvent actionEvent) {
        EditCarData ecd = new EditCarData();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeUnshared(ecd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteCar(ActionEvent actionEvent) {
        DeleteCarData dcd = new DeleteCarData();
        dcd.setStatus(true);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeUnshared(dcd);
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
}
