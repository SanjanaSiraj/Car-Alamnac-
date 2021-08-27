package sample;

import Utils.Car;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Main extends Application {
    private Socket s = null;
    Stage stage;
    public Socket getSocket(){
        return s;
    }
    public void LoginPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setSocket(s);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void ManufacturerHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ManufacturerHome.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ManufacturerHomeController controller = loader.getController();
        controller.setSocket(s);

        // Set the primary stage
        stage. setTitle("ManufacturerHome");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void ViewerHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewerHome.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ViewerHomeController controller = loader.getController();
        controller.setSocket(s);

        // Set the primary stage
        stage.setTitle("ViewerHome");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void ViewAllCarsPage(List<Car> carList) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewAllCars.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ViewAllCarsController controller = loader.getController();
        controller.setSocket(s);
        controller.setList(carList);
        controller.loadTable();

        // Set the primary stage
        stage.setTitle("ViewAllCars");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void AddCarPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddCar.fxml"));
        Parent root = loader.load();

        // Loading the controller
        AddCarController controller = loader.getController();
        controller.setSocket(s);

        // Set the primary stage
        stage.setTitle("AddCar");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void DeleteCarPage(List<Car> carList) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DeleteCar.fxml"));
        Parent root = loader.load();

        // Loading the controller
        DeleteCarController controller = loader.getController();
        controller.setSocket(s);
        controller.setList(carList);
        controller.loadTable();

        // Set the primary stage
        stage.setTitle("DeleteCar");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void EditCarPage(List<Car> carList) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EditCar.fxml"));
        Parent root = loader.load();

        // Loading the controller
        EditCarController controller = loader.getController();
        controller.setSocket(s);
        controller.setList(carList);
        controller.loadTable();

        // Set the primary stage
        stage.setTitle("EditCar");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void GetRegNumPage(List<Car> cl) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GetRegNum.fxml"));
        Parent root = loader.load();

        // Loading the controller
        GetRegNumController controller = loader.getController();
        controller.setSocket(s);
        controller.loadTable(cl);

        // Set the primary stage
        stage.setTitle("GetRegistrationNumber");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void GetMakeAndModelPage(List<Car> cl) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GetMakeAndModel.fxml"));
        Parent root = loader.load();

        // Loading the controller
        GetMakeAndModelController controller = loader.getController();
        controller.setSocket(s);
        controller.loadTable(cl);

        // Set the primary stage
        stage.setTitle("GetMakeAndModel");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void BuyCarPage(List<Car> cl) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BuyCar.fxml"));
        Parent root = loader.load();

        // Loading the controller
        BuyCarController controller = loader.getController();
        controller.setSocket(s);
        controller.loadTable(cl);

        // Set the primary stage
        stage.setTitle("BuyCar");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Input");
        alert.setHeaderText("Invalid Input");
        alert.setContentText("Invalid Username or Password");
        alert.showAndWait();
    }

    public void FailedtoAddAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Input");
        alert.setHeaderText("Invalid Input");
        alert.setContentText("Car Already Exists in Database");
        alert.showAndWait();
    }

    public void FailedtoDeleteAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Input");
        alert.setHeaderText("Invalid Input");
        alert.setContentText("Car Doesn't Exist in Database");
        alert.showAndWait();
    }

    public void BuyAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Successful Transaction");
        alert.setHeaderText("Successful Transaction");
        alert.setContentText("Car Bought Successfully");
        alert.showAndWait();
    }

    public void AddAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Successful Update");
        alert.setHeaderText("Successful Update");
        alert.setContentText("Car Added Successfully");
        alert.showAndWait();
    }

    public void DeleteAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Successful Update");
        alert.setHeaderText("Successful Update");
        alert.setContentText("Car Deleted Successfully");
        alert.showAndWait();
    }

    public void EditAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Successful Update");
        alert.setHeaderText("Successful Update");
        alert.setContentText("Car Edited Successfully");
        alert.showAndWait();
    }

    public void UpdateAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update");
        alert.setHeaderText("Update");
        alert.setContentText("Updates available. Refresh page again to get updated results.");
        alert.showAndWait();
    }

    public void FailedBuyAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Input");
        alert.setHeaderText("Invalid Input");
        alert.setContentText("Invalid Registration Number or Quantity");
        alert.showAndWait();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        try {
            s = new Socket("127.0.0.1",337);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ReadThread(s,this);
        LoginPage();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
