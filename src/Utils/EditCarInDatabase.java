package Utils;

import java.io.Serializable;

public class EditCarInDatabase implements Serializable {
    boolean Status;
    String regNum;
    String property;
    String newValue;

    public String getRegNum() {
        return regNum;
    }

    public String getNewValue() {
        return newValue;
    }

    public String getProperty() {
        return property;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public boolean isStatus() {
        return Status;
    }
}
