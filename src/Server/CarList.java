package Server;

import Utils.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarList {
    private static final String FILE_NAME = "cars.txt";
    List<Car> carList;
    BufferedReader br;
    BufferedWriter bw;

    static CarList obj = new CarList();

    private CarList(){
        carList = new ArrayList();
        fileToArray();
    }

    public static CarList getInstance(){
        //System.out.println(this.carList.get(0).getRegistrationNumber());
        return obj;
    }

    public void fileToArray(){
        try {
            //carList = new ArrayList();
            String line;
            br = new BufferedReader(new FileReader(FILE_NAME));
            while ( ( line = br.readLine() ) != null ) {
                String values[] = line.split(",");
                if (values.length<9) {
                    br.close();
                    return;
                }
                String s[] = new String[3];
                for ( int i = 0; i <= 2; i++ ) {
                    s[i] = values[i + 2];
                }
                int year = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[7]);
                int quantity = Integer.parseInt(values[8]);
                Car c = new Car(values[0], year, s, values[5], values[6], price,quantity);
                carList.add(c);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean searchByRegNum(Car car){
        for ( Car c: carList ){
            if ( c.getRegistrationNumber().equalsIgnoreCase(car.getRegistrationNumber()) ){
                return true;
            }
        }
        return false;
    }

    public void arrayToFile(){
        try{
            bw = new BufferedWriter(new FileWriter(FILE_NAME));
            for ( Car c:carList ){
                bw.write(c.getRegistrationNumber());
                bw.write(",");
                bw.write(Integer.toString(c.getYearMade()));
                bw.write(",");
                for( int i = 0; i < 3; i++ ){
                    if(c.getColours().length-1<i) bw.write("");
                    else bw.write(c.getColours()[i]);
                    bw.write(",");
                }
                bw.write(c.getCarMake());
                bw.write(",");
                bw.write(c.getCarModel());
                bw.write(",");
                bw.write(Integer.toString(c.getPrice()));
                bw.write(",");
                bw.write(Integer.toString(c.getQuantity()));
                bw.write("\n");
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Car returnByRegNum(String string) {
        for ( Car c: carList ){
            if ( c.getRegistrationNumber().equalsIgnoreCase(string) ){
                return c;
            }
        }
        return null;
    }

    public List returnByMakeAndModel(String s1, String s2){
        List<Car> cl = new ArrayList();
        for (Car c: carList){
            if (c.getCarMake().equalsIgnoreCase(s1)){
                if(s2.equalsIgnoreCase("any")||c.getCarModel().equalsIgnoreCase(s2)){
                    cl.add(c);
                }
            }
        }
        return cl;
    }

    public boolean isMakePresent(String s1){
        for (Car c: carList){
            if(c.getCarMake().equalsIgnoreCase(s1)) return true;
        }
        return false;
    }
}
