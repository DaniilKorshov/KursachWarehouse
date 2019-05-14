package com.kursach.KursachWarehouse.domain;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="InventSum")
public class InventSum {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;


    @ManyToOne
    @JoinColumn(name="invent_id")
    private Invent invent;

    @ManyToOne
    @JoinColumn(name="cell_id")
    private Cell cell;

    private Long qty;

    public InventSum() { }

    public InventSum(Invent invent, Cell cell, Long qty) {
        this.invent = invent;
        this.cell = cell;
        this.qty = qty;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Invent getInvent() {
        return invent;
    }
    public void setInvent(Invent invent) {
        this.invent = invent;
    }
    public Cell getCell() {
        return cell;
    }
    public void setCell(Cell cell) {
        this.cell = cell;
    }
    public Long getQty() {
        return qty;
    }
    public void setQty(Long qty) {
        this.qty = qty;
    }
}
