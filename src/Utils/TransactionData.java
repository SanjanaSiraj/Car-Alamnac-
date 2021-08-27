package Utils;

import java.io.Serializable;

public class TransactionData implements Serializable {
    boolean Status;
    String regNum;
    String quantity;

    public String getRegNum() {
        return regNum;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public boolean isStatus() {
        return Status;
    }
}
