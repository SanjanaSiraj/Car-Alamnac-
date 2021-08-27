package Utils;

import java.io.Serializable;

public class ShowCarByMakeAndModel implements Serializable {
    String make;
    String model;

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
