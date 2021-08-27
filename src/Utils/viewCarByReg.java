package Utils;

import java.io.Serializable;
import java.util.List;

public class viewCarByReg implements Serializable {
    private boolean status;
    List<Car> carList=null;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public List<Car> getCarList() {
        return carList;
    }
}
