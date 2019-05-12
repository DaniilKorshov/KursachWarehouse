package com.kursach.KursachWarehouse.domain;

import com.kursach.KursachWarehouse.domain.enums.TaskStatus;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="WarehouseOrderLine")
public class WarehouseOrderLine {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="warehouseOrder_id")
    private WarehouseOrder warehouseOrder;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="invent_id")
    private Invent invent;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="startCell_id")
    private Cell startLocation;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="finishCell_id")
    private Cell finishLocation;

    private Long qty;

    @ElementCollection(targetClass = TaskStatus.class,fetch = FetchType.LAZY)
    @CollectionTable(name="take_status",joinColumns = @JoinColumn(name="warehouse_order_line_id"))
    @Enumerated(EnumType.STRING)
    private Set<TaskStatus> takeStatus;

    @ElementCollection(targetClass = TaskStatus.class,fetch = FetchType.LAZY)
    @CollectionTable(name="put_status",joinColumns = @JoinColumn(name="warehouse_order_line_id"))
    @Enumerated(EnumType.STRING)
    private Set<TaskStatus> putStatus;

    @ManyToOne(cascade={CascadeType.ALL})
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
    public Set<TaskStatus> getTakeStatus() {
        return takeStatus;
    }
    public void setTakeStatus(Set<TaskStatus> takeStatus) {
        this.takeStatus = takeStatus;
    }
    public Set<TaskStatus> getPutStatus() {
        return putStatus;
    }
    public void setPutStatus(Set<TaskStatus> putStatus) {
        this.putStatus = putStatus;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
