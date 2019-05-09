package com.kursach.KursachWarehouse.domain;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="dimgroup")
public class Dimgroup {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private double width;
    private double length;

    private double weight;

    private String color;
    private String config;

    public Dimgroup() { }

    public Dimgroup(double width, double length, double weight, String color, String config) {
        this.width = width;
        this.length = length;
        this.weight = weight;
        this.color = color;
        this.config = config;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getConfig() {
        return config;
    }
    public void setConfig(String config) {
        this.config = config;
    }
}
