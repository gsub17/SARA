package com.example.shubhajitghosh.sara.Modules;

import java.io.Serializable;

public class MyNode {
    String username;
    String userphonr;
    String usermain;
    String servicename;
    String servicemail;
    String servicephone;
    String serviceaddress;
    String category;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    String rating;

   /* public MyNode( String username,String userphonr,String usermain,String servicename,String servicemail,String servicephone, String serviceaddress,String category,String date,int flag){

        this.username=username;
        this.userphonr=userphonr;
        this.usermain=usermain;
        this.serviceaddress=serviceaddress;
        this.servicemail=servicemail;
        this.servicename=servicename;
        this.servicephone=servicephone;
        this.category=category;
        this.date=date;
        this.flag=flag;
    }*/

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;

    public String getServiceaddress() {
        return serviceaddress;
    }

    public void setServiceaddress(String serviceaddress) {
        this.serviceaddress = serviceaddress;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    int flag;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphonr() {
        return userphonr;
    }

    public void setUserphonr(String userphonr) {
        this.userphonr = userphonr;
    }

    public String getUsermain() {
        return usermain;
    }

    public void setUsermain(String usermain) {
        this.usermain = usermain;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getServicemail() {
        return servicemail;
    }

    public void setServicemail(String servicemail) {
        this.servicemail = servicemail;
    }

    public String getServicephone() {
        return servicephone;
    }

    public void setServicephone(String servicephone) {
        this.servicephone = servicephone;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
