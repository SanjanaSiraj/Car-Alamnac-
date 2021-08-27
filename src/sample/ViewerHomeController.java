package sample;

import Utils.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ViewerHomeController {
    public Button viewCarsButton;
    public Button searchByRegButton;
    public Button searchByMakeAndModelButton;
    public Button buyCarButton;
    public Button logOutButton;
    Socket s;
    public void setSocket(Socket s){
        this.s = s;
    }

    public void viewAllCars(ActionEvent actionEvent) {
        viewAllCarsData vacd = new viewAllCarsData();
        vacd.setStatus(true);
        vacd.setPrevious("ViewerHome");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeUnshared(vacd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchByReg(ActionEvent actionEvent) {
        viewCarByReg vcbr = new viewCarByReg();
        vcbr.setStatus(true);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeUnshared(vcbr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchByMakeAndModel(ActionEvent actionEvent) {
        viewCarByMakeAndModel vcbmm = new viewCarByMakeAndModel();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeUnshared(vcbmm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buyCar(ActionEvent actionEvent) {
        BuyCarData bcd = new BuyCarData();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeUnshared(bcd);
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
