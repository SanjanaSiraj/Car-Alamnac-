package Utils;

import java.io.Serializable;

public class AddCarToDataBaseData implements Serializable {
    private Car c;
    private boolean Status;

    public Car getC() {
        return c;
    }

    public void setC(Car c) {
        this.c = c;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
