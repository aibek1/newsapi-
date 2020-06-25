package com.hfad.newsapiisubbota.categories;

public class Categorii {
    private int image;
    private String title;
    private String newsType;

    public Categorii(int image, String title, String newsType) {
        this.image = image;
        this.title = title;
        this.newsType = newsType;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }
}
