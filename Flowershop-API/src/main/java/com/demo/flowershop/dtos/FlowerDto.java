package com.demo.flowershop.dtos;



public class FlowerDto {

    private long id;
    private String name;
    private String type;
    private Byte[] img;
    

    public FlowerDto(long id, String name, String type, Byte[] img) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.img = img;
    }
    
    public FlowerDto() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Byte[] getImg() {
        return img;
    }

    public void setImg(Byte[] img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Flower [id=" + id + ", name=" + name + ", type=" + type + "]";
    }

    
    
}