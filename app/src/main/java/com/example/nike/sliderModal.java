package com.example.nike;

public class sliderModal {
    String image,name,about,price;

    public sliderModal() {
    }

    public sliderModal(String image, String name, String about, String price) {
        this.image = image;
        this.name = name;
        this.about = about;
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
