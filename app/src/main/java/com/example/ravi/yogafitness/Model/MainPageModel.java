package com.example.ravi.yogafitness.Model;

/**
 * Created by Ravi on 01-10-2017.
 */

public class MainPageModel {
    private int image_id;
    private String name;

    public MainPageModel(){

    }

    public MainPageModel(int image_id, String name) {
        this.image_id = image_id;
        this.name = name;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
