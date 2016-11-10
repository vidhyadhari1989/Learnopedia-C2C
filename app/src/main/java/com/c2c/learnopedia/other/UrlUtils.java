package com.c2c.learnopedia.other;

/**
 * Created by acer on 05-11-2016.
 */

public class UrlUtils {
    private static final String BASE_URL = "http://click2clinicapp.azurewebsites.net/";
    public static final String User_registration = BASE_URL + "SaveUserRegistration";
    public static final String Verify_user_mobile=BASE_URL+"OTP/GetOTP/";
    public static final String GetVerify_mobile=BASE_URL+"VerifyOTP/";
    public static final String Get_All_Subjects=BASE_URL+"GetSubjectsList";
}
