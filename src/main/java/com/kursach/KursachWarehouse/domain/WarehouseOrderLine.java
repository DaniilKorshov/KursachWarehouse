package com.kursach.KursachWarehouse.domain;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="WarehouseOrderLine")
public class WarehouseOrderLine {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="warehouseOrder_id")
    private WarehouseOrder warehouseOrder;

    @ManyToOne
    @JoinColumn(name="invent_id")
    private Invent invent;

    @ManyToOne
    @JoinColumn(name="startCell_id")
    private Cell startLocation;

    @ManyToOne
    @JoinColumn(name="finishCell_id")
    private Cell finishLocation;

    private Long qty;

    private boolean takeStatus;
    private boolean putStatus;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;



    public WarehouseOrderLine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WarehouseOrder getWarehouseOrder() {
        return warehouseOrder;
    }

    public void setWarehouseOrder(WarehouseOrder warehouseOrder) {
        this.warehouseOrder = warehouseOrder;
    }

    public Invent getInvent() {
        return invent;
    }

    public void setInvent(Invent invent) {
        this.invent = invent;
    }

    public Cell getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Cell startLocation) {
        this.startLocation = startLocation;
    }

    public Cell getFinishLocation() {
        return finishLocation;
    }

    public void setFinishLocation(Cell finishLocation) {
        this.finishLocation = finishLocation;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public boolean isTakeStatus() {
        return takeStatus;
    }

    public void setTakeStatus(boolean takeStatus) {
        this.takeStatus = takeStatus;
    }

    public boolean isPutStatus() {
        return putStatus;
    }

    public void setPutStatus(boolean putStatus) {
        this.putStatus = putStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
