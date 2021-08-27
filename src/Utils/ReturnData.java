package Utils;

import java.io.Serializable;

public class ReturnData implements Serializable {
    private boolean status;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }
}
