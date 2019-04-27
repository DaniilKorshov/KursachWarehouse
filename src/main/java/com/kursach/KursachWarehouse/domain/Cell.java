package com.kursach.KursachWarehouse.domain;

import javax.persistence.*;

@Entity
@Table(name="cell")
public class Cell {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private double length;
    private double width;
    private double height;
    private double weight;

    private int warehouseId;
    private int zone;
    private int passageway;
    private int stillage;
    private int shelf;

    public Cell() { }

}
