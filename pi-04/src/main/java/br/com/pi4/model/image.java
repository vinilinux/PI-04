package br.com.pi4.model;
public class image {
    private String image_path;
    private String image_default;

    public image(String image_path, String image_default){
        this.image_path = image_path;
        this.image_default = image_default;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getImage_default() {
        return image_default;
    }

    public void setImage_default(String image_default) {
        this.image_default = image_default;
    }
}

