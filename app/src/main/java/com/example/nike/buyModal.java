package com.example.nike;

public class buyModal {
    String name,image,price,size,user_name,user_email,user_contact,user_password,user_address,user_city,user_pincode;

    public buyModal() {
    }

    public buyModal(String name, String image, String price,String size, String user_name, String user_email, String user_contact, String user_password, String user_address, String user_city, String user_pincode) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.size=size;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_contact = user_contact;
        this.user_password = user_password;
        this.user_address = user_address;
        this.user_city = user_city;
        this.user_pincode = user_pincode;

    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_contact() {
        return user_contact;
    }

    public void setUser_contact(String user_contact) {
        this.user_contact = user_contact;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
        this.user_city = user_city;
    }

    public String getUser_pincode() {
        return user_pincode;
    }

    public void setUser_pincode(String user_pincode) {
        this.user_pincode = user_pincode;
    }
}
