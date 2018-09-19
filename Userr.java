package com.example.jp.myapplication;

public class Userr {
    String u_name;
    String u_email;
    String u_password;
    public Userr()
    {

    }

    public Userr(String u_name, String u_email, String u_password) {
        this.u_name = u_name;
        this.u_email = u_email;
        this.u_password = u_password;
    }

    public String getU_name() {
        return u_name;
    }

    public String getU_email() {
        return u_email;
    }

    public String getU_password() {
        return u_password;
    }
}

