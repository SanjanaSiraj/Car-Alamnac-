package Utils;

import java.io.Serializable;

public class DeleteCarFromDatabase implements Serializable {
    String regNum;
    boolean Status;

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public boolean isStatus() {
        return Status;
    }
}
