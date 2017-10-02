package com.example.ravi.yogafitness.Model;

/**
 * Created by Ravi on 03-10-2017.
 */

public class Exercise {
    private int imageId;
    private String name;
    private String description;

    public Exercise(int imageId, String name, String description) {
        this.imageId = imageId;
        this.name = name;
        this.description = description;
    }

    public Exercise(){

    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
