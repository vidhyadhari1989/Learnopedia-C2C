package com.c2c.learnopedia.model;


import android.graphics.Bitmap;

public class Courses {
    private String CourseName;
    private String CourseID;
    private String CourseDesc;
    private String CourseCode;
    private String Image;
    private String MapCode;
    private String VideoLink;
    private String Status;
    private String Message;

    public Courses(Bitmap bitmap, String s) {

    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }

    public String getCourseDesc() {
        return CourseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        CourseDesc = courseDesc;
    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String courseID) {
        CourseID = courseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMapCode() {
        return MapCode;
    }

    public void setMapCode(String mapCode) {
        MapCode = mapCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getVideoLink() {
        return VideoLink;
    }

    public void setVideoLink(String videoLink) {
        VideoLink = videoLink;
    }


}
