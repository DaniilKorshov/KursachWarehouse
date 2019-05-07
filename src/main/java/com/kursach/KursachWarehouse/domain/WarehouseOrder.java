package com.kursach.KursachWarehouse.domain;

import com.kursach.KursachWarehouse.domain.enums.WarehouseOrderType;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="warehouseOrder")
public class WarehouseOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ElementCollection(targetClass = WarehouseOrderType.class,fetch = FetchType.LAZY)
    @CollectionTable(name="warehouse_order_type",joinColumns = @JoinColumn(name="warehouse_id"))
    @Enumerated(EnumType.STRING)
    private Set<WarehouseOrderType> type;
    private String description;

    public WarehouseOrder() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Set<WarehouseOrderType> getType() {
        return type;
    }
    public void setType(Set<WarehouseOrderType> type) {
        this.type = type;
    }
}
