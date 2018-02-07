package com.example.user.android_assignment_11_3;

import android.graphics.Bitmap;

/**
 * Created by User on 06-02-2018.
 */

public class Employee {
    //creating the class as Employee
    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    private Bitmap bmp;
    private String name;
    private int age;
    //constructor
    public Employee(Bitmap b, String n, int k) {
        bmp = b;
        name = n;
        age = k;
    }
    //generating getters and setters
    public Bitmap getBitmap() {
        return bmp;
    }
    //getting the bitmap
    public String getName() {
        return name;
    }
    //getting the name
    public int getAge() {
        return age;
    }
    //getting the age
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setBitmap(){
        this.bmp=bmp;
    }

}
