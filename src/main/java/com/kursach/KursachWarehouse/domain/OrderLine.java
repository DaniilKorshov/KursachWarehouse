package com.kursach.KursachWarehouse.domain;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="orderLine")
public class OrderLine {
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

    private boolean takeStatus=false;
    private boolean putStatus=false;



    public OrderLine() {
    }

}
