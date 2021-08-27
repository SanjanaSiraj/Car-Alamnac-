package Server;


import Utils.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ReadThreadServer implements Runnable {
    private String str;
    private Socket s;
    List<Socket> sockets;
    Thread t;
    ReadThreadServer(Socket s, List<Socket> socketList){
        sockets=socketList;
        this.s = s;
        t = new Thread(this);
        t.start();
    }

    @Override
    synchronized public void run() {
        Object obj = null;
        try{
        while (true) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(s.getInputStream());
                obj = ois.readUnshared();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (obj != null){
                if (obj instanceof LoginData){
                    LoginData ld = (LoginData) obj;
                    UserList ul = new UserList();
                    ld = ul.checkLogin(ld);
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeUnshared(ld);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (obj instanceof LogOutData){
                    LogOutData lod = (LogOutData) obj;
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeUnshared(lod);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (obj instanceof viewAllCarsData){
                    viewAllCarsData vacd = (viewAllCarsData) obj;
                    str = vacd.getPrevious();
                    CarList cl = CarList.getInstance();
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeUnshared(cl.carList);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (obj instanceof ReturnData){
                    if(str.equalsIgnoreCase("AddCar")){
                        AddCarData acd = new AddCarData();
                        try {
                            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                            oos.writeUnshared(acd);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        UserList ul = new UserList();
                        LoginData ld =new LoginData();
                        ld.setStatus(true);
                        if (str.equalsIgnoreCase("ViewerHome")) ld.setUsername("viewer");
                        else ld.setUsername(" ");
                        try {
                            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                            oos.writeUnshared(ld);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (obj instanceof AddCarData){
                    AddCarData acd = (AddCarData) obj;
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeUnshared(acd);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (obj instanceof DeleteCarData){
                    DeleteCarData dcd = (DeleteCarData) obj;
                    CarList cl = CarList.getInstance();
                    dcd.setCarList(cl.carList);
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeUnshared(dcd);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(obj instanceof EditCarData){
                    EditCarData ecd = (EditCarData) obj;
                    CarList cl = CarList.getInstance();
                    ecd.setCarList(cl.carList);
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeUnshared(ecd);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (obj instanceof DeleteCarFromDatabase){
                    DeleteCarFromDatabase dcfd = (DeleteCarFromDatabase) obj;
                    CarList cl = CarList.getInstance();
                    Car car = cl.returnByRegNum(dcfd.getRegNum());
                    if(car!=null){
                        cl.carList.remove(car);
                        cl.arrayToFile();
                        dcfd.setStatus(true);
                    }
                    else dcfd.setStatus(false);
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeUnshared(dcfd);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (obj instanceof ReturnDataManufacturer) {
                    ReturnDataManufacturer rdm = (ReturnDataManufacturer) obj;
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeUnshared(rdm);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (obj instanceof ReturnDataViewer){
                    ReturnDataViewer rdv = (ReturnDataViewer) obj;
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeUnshared(rdv);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (obj instanceof AddCarToDataBaseData){
                    AddCarToDataBaseData acdb = (AddCarToDataBaseData) obj;
                    CarList cl = CarList.getInstance();
                    if(!cl.searchByRegNum(acdb.getC())){
                        cl.carList.add(acdb.getC());
                        cl.arrayToFile();
                        acdb.setStatus(true);
                    }
                    else acdb.setStatus(false);
                        try {
                            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                            oos.writeUnshared(acdb);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
                if (obj instanceof Refresh){
                    CarList cl = CarList.getInstance();
                    Refresh refresh = (Refresh) obj;
                    refresh.setCarList(cl.carList);
                    for (Socket socket : sockets){
                        try {
                            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                            oos.writeUnshared(refresh);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if(obj instanceof viewCarByReg){
                    viewCarByReg vcbr = (viewCarByReg) obj;
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeUnshared(vcbr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (obj instanceof ShowCarByReg){
                    ShowCarByReg scbr = (ShowCarByReg)obj;
                    CarList cl = CarList.getInstance();
                    Car c = cl.returnByRegNum(scbr.getS());
                    if(c!=null){
                        List<Car> carList = new ArrayList();
                        carList.add(c);
                        viewCarByReg vcbr = new viewCarByReg();
                        vcbr.setCarList(carList);
                        try {
                            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                            oos.writeUnshared(vcbr);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        viewCarByReg vcbr = new viewCarByReg();
                        vcbr.setCarList(null);
                        try {
                            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                            oos.writeUnshared(vcbr);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if(obj instanceof viewCarByMakeAndModel){
                    viewCarByMakeAndModel vcbmm = (viewCarByMakeAndModel) obj;
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeUnshared(vcbmm);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (obj instanceof ShowCarByMakeAndModel){
                    ShowCarByMakeAndModel scbmm = (ShowCarByMakeAndModel) obj;
                    CarList cl = CarList.getInstance();
                    if(cl.isMakePresent(scbmm.getMake())){
                        List<Car> carList = new ArrayList();
                        carList = cl.returnByMakeAndModel(scbmm.getMake(),scbmm.getModel());
                        viewCarByMakeAndModel vcbmm = new viewCarByMakeAndModel();
                        vcbmm.setCarList(carList);
                        try {
                            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                            oos.writeUnshared(vcbmm);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        viewCarByMakeAndModel vcbmm = new viewCarByMakeAndModel();
                        vcbmm.setCarList(null);
                        try {
                            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                            oos.writeUnshared(vcbmm);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if(obj instanceof BuyCarData){
                    BuyCarData bcd = (BuyCarData) obj;
                    CarList cl = CarList.getInstance();
                    bcd.setCarList(cl.carList);
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeUnshared(bcd);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(obj instanceof TransactionData) {
                    TransactionData td = (TransactionData) obj;
                    CarList cl = CarList.getInstance();
                    Car car = cl.returnByRegNum(td.getRegNum());
                    if (car != null) {
                        int i = car.getQuantity() - Integer.parseInt(td.getQuantity());
                        if (i >= 0) {
                            cl.carList.remove(car);
                            car.setQuantity(i);
                            cl.carList.add(car);
                            cl.arrayToFile();
                            td.setStatus(true);
                        }
                    }
                    else td.setStatus(false);
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeUnshared(td);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(obj instanceof EditCarInDatabase){
                    EditCarInDatabase ecid = (EditCarInDatabase) obj;
                    CarList cl = CarList.getInstance();
                    Car car = cl.returnByRegNum(ecid.getRegNum());
                        if(car!=null){
                            cl.carList.remove(car);
                            if(ecid.getProperty().equalsIgnoreCase("Year Made")){
                                car.setYearMade(Integer.parseInt(ecid.getNewValue()));
                            }
                            else if(ecid.getProperty().equalsIgnoreCase("Colors")){
                                String[] str = ecid.getNewValue().split(",");
                                car.setColours(str);
                            }
                            else if(ecid.getProperty().equalsIgnoreCase("Car Make")){
                                car.setCarMake(ecid.getNewValue());
                            }
                            else if(ecid.getProperty().equalsIgnoreCase("Car Model")){
                                car.setCarModel(ecid.getNewValue());
                            }
                            else if (ecid.getProperty().equalsIgnoreCase("Price")){
                                car.setPrice(Integer.parseInt(ecid.getNewValue()));
                            }
                            else if (ecid.getProperty().equalsIgnoreCase("Quantity")){
                                car.setQuantity(Integer.parseInt(ecid.getNewValue()));
                            }
                            cl.carList.add(car);
                            cl.arrayToFile();
                            ecid.setStatus(true);
                        }
                        else ecid.setStatus(false);
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        oos.writeUnshared(ecid);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }finally {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

