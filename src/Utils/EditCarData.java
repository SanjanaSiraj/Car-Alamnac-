package Utils;

import java.io.Serializable;
import java.util.List;

public class EditCarData implements Serializable {
    List<Car> carList;

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
