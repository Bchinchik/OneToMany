package main.models;

/**
 * Created by Bogdan on 30.10.2016.
 */
public class resultAccount {
    private String currName;
    private long summAmount;

    public resultAccount(String currName, long summAmount) {
        this.currName = currName;
        this.summAmount = summAmount;
    }

    public String getCurrName() {
        return currName;
    }

    public long getSummAmount() {
        return summAmount;
    }

    public void setCurrName(String currName) {
        this.currName = currName;
    }

    public void setSummAmount(long summAmount) {
        this.summAmount = summAmount;
    }
}
