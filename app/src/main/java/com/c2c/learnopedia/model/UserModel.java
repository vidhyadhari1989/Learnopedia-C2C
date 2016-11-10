package com.c2c.learnopedia.model;

/**
 * Created by mprak on 09-10-2016.
 */

public class UserModel {

    public String FirstName;
    public String LastName;
    public String Gender;
    public String Email;
    public String SubjectCode;

    private String userReg;

    public UserModel(String firstName, String lastName,  String email, String mobileNo,String gender, String subjectCode) {
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        Email = email;
        SubjectCode = subjectCode;
        MobileNo = mobileNo;
    }

    private String MobileNo;
    private String OTP;

    public UserModel(String mobileNo, String OTP) {
        this.OTP = OTP;
        MobileNo = mobileNo;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }


    private String uid;

    private String responseId;

    private String responseText;

    public String getUserReg() {
        return userReg;
    }

    public void setUserReg(String userReg) {
        this.userReg = userReg;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }


    @Override
    public String toString() {
        return "ClassPojo [userReg = " + userReg + ", uid = " + uid + ", responseId = " + responseId + ", responseText = " + responseText + "]";
    }
}
