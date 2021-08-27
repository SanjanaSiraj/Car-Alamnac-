package Utils;

import java.io.Serializable;

public class viewAllCarsData implements Serializable {
    private boolean status;
    String previous;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getPrevious() {
        return previous;
    }
}
