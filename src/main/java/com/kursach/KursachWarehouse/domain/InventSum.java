package com.kursach.KursachWarehouse.domain;

import javax.persistence.*;

public class InventSum {

    @ManyToOne
    @JoinColumn(name="invent_id")
    private Invent invent;

    @ManyToOne
    @JoinColumn(name="cell_id")
    private Cell cell;

    private Long qty;

    public InventSum() { }

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
