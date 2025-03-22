package com.example.star.beans;

public class Star {
    private int id;
    private String name;
    private String img;
    private float star;
    private static int comp = 0; // Initialisation du compteur

    public Star(String name, String img, float star) {
        this.id = ++comp; // Attribution d'un ID unique auto-incrémenté
        this.name = name;
        this.img = img;
        this.star = star;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }
}
