package com.c2c.learnopedia.model;

/**
 * Created by SERVER on 8/14/2016.
 */
public class PhoneCountryCode {

    public String contryCode;
    public int contryFlag;

    public PhoneCountryCode(String contryCode, int contryFlag) {
        this.contryCode = contryCode;
        this.contryFlag = contryFlag;
    }

    public String getContryCode() {
        return contryCode;
    }

    public void setContryCode(String contryCode) {
        this.contryCode = contryCode;
    }

    public int getContryFlag() {
        return contryFlag;
    }

    public void setContryFlag(int contryFlag) {
        this.contryFlag = contryFlag;
    }


}
