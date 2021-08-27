package Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeleteCarData implements Serializable {
    List<Car> carList = new ArrayList();
    boolean Status;

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public boolean isStatus() {
        return Status;
    }
}
