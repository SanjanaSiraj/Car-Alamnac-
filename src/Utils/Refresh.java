package Utils;

import java.io.Serializable;
import java.util.List;

public class Refresh implements Serializable {

    private boolean status;
    private boolean buyStatus;
    private boolean deleteStatus;
    private boolean editStatus;
    List<Car> carList;

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

    public void setbuyStatus(boolean editStatus) {
        this.buyStatus = editStatus;
    }

    public boolean isbuyStatus() {
        return buyStatus;
    }

    public void setdeleteStatus(boolean editStatus) {
        this.deleteStatus = editStatus;
    }

    public boolean getdeleteStatus() {
        return deleteStatus;
    }

    public void setEditStatus(boolean editStatus) {
        this.editStatus = editStatus;
    }

    public boolean isEditStatus() {
        return editStatus;
    }
}
