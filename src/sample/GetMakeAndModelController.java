package sample;

import Utils.Car;
import Utils.LogOutData;
import Utils.ReturnDataViewer;
import Utils.ShowCarByMakeAndModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class GetMakeAndModelController {
    public Button BackButton;
    public Button LogOutButton;
    public TextField CarMake;
    public TextField CarModel;
    public Button ResetButton;
    public Button SubmitButton;
    public TableView tableView;
    ObservableList<CarInfo> data = FXCollections.observableArrayList();
    private List<Car> carList;
    Socket s;
    public void setSocket(Socket s) {
        this.s = s;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public void Reset(ActionEvent actionEvent) {
        CarMake.clear();
        CarModel.clear();
    }

    public void Submit(ActionEvent actionEvent) {
        ShowCarByMakeAndModel scbmm = new ShowCarByMakeAndModel();
        scbmm.setMake(CarMake.getText());
        scbmm.setModel(CarModel.getText());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeUnshared(scbmm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReturnToViewerMenu(ActionEvent actionEvent) {
        ReturnDataViewer rdv = new ReturnDataViewer();
        rdv.setStatus(true);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeUnshared(rdv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LogOut(ActionEvent actionEvent) {
        LogOutData lod = new LogOutData();
        lod.setStatus(true);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeUnshared(lod);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeColumns(){
        TableColumn<CarInfo,String> RegNumcol = new TableColumn<>("Registration Number");
        RegNumcol.setMinWidth(50);
        RegNumcol.setCellValueFactory(new PropertyValueFactory<>("registrationNumber"));

        TableColumn<CarInfo, Integer> Yearcol = new TableColumn<>("Year Made");
        Yearcol.setMinWidth(20);
        Yearcol.setCellValueFactory(new PropertyValueFactory<>("yearMade"));

        TableColumn<CarInfo, String> Colourscol = new TableColumn<>("Colors");
        Colourscol.setMinWidth(250);
        Colourscol.setCellValueFactory(new PropertyValueFactory<>("colors"));

        TableColumn<CarInfo, String> Makecol = new TableColumn<>("Car Make");
        Makecol.setMinWidth(50);
        Makecol.setCellValueFactory(new PropertyValueFactory<>("carMake"));

        TableColumn<CarInfo, String> Modelcol = new TableColumn<>("Car Model");
        Modelcol.setMinWidth(50);
        Modelcol.setCellValueFactory(new PropertyValueFactory<>("carModel"));

        TableColumn<CarInfo, String> Pricecol = new TableColumn<>("Price");
        Pricecol.setMinWidth(30);
        Pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<CarInfo, Integer> Quantitycol = new TableColumn<>("Quantity");
        Quantitycol.setMinWidth(30);
        Quantitycol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableView.getColumns().addAll(RegNumcol,Yearcol,Colourscol,Makecol,Modelcol,Pricecol,Quantitycol);
    }

    public void loadTable(List<Car> cl){
        setCarList(cl);
        if(carList!=null)
        {
            initializeColumns();
            for (Car c : carList){
                data.add(new CarInfo(c.getRegistrationNumber(),c.getYearMade(),c.getColours(),c.getCarMake(),c.getCarModel(),c.getPrice(),c.getQuantity()));
            }
            tableView.setEditable(true);
            tableView.setItems(data);
        }
    }
}
