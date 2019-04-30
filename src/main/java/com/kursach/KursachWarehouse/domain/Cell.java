package com.kursach.KursachWarehouse.domain;

import com.kursach.KursachWarehouse.domain.enums.CellStatus;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name="warehouse_id")
    private Warehouse warehouse;

    private int zone;
    private int passageway;
    private int stillage;
    private int shelf;

    @ElementCollection(targetClass = CellStatus.class,fetch = FetchType.LAZY)
    @CollectionTable(name="cell_status",joinColumns = @JoinColumn(name="cell_id"))
    @Enumerated(EnumType.STRING)
    private Set<CellStatus> cellStatus;

    public Cell() { }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public Warehouse getWarehouse() {
        return warehouse;
    }
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
    public int getZone() {
        return zone;
    }
    public void setZone(int zone) {
        this.zone = zone;
    }
    public int getPassageway() {
        return passageway;
    }
    public void setPassageway(int passageway) {
        this.passageway = passageway;
    }
    public int getStillage() {
        return stillage;
    }
    public void setStillage(int stillage) {
        this.stillage = stillage;
    }
    public int getShelf() {
        return shelf;
    }
    public void setShelf(int shelf) {
        this.shelf = shelf;
    }
    public Set<CellStatus> getCellStatus() {
        return cellStatus;
    }
    public void setCellStatus(Set<CellStatus> cellStatus) {
        this.cellStatus = cellStatus;
    }
}
