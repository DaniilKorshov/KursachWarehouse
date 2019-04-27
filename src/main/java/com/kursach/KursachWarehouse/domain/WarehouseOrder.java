package com.kursach.KursachWarehouse.domain;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="warehouseorder")
public class WarehouseOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String type;
    private String description;

    public WarehouseOrder() {
    }


}
