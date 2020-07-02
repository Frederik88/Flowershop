package com.demo.flowershop.dtos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class FlowerDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String type;
    

    public FlowerDto(long id, String name, String type) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
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

    @Override
    public String toString() {
        return "Flower [id=" + id + ", name=" + name + ", type=" + type + "]";
    }

    
    
}