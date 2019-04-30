package com.kursach.KursachWarehouse.domain;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="warehouseOrder")
public class WarehouseOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String type;
    private String description;

    public WarehouseOrder() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
