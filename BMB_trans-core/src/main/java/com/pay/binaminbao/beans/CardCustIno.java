package com.pay.binaminbao.beans;

public class CardCustIno {
    
    private String pin;
    private String expired;

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "CardCustIno{" +
                "pin='" + pin + '\'' +
                ", expired='" + expired + '\'' +
                '}';
    }
}
