package com.kursach.KursachWarehouse.domain;

import com.kursach.KursachWarehouse.domain.enums.ItemType;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="invent")
public class Invent {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;
    private String unit;

    @ElementCollection(targetClass = ItemType.class,fetch = FetchType.LAZY)
    @CollectionTable(name="item_type",joinColumns = @JoinColumn(name="invent_id"))
    @Enumerated(EnumType.STRING)
    private Set<ItemType> itemType;

    @ManyToOne
    @JoinColumn(name="dimgroup_id")
    private Dimgroup dimgroup;

    public Invent() { }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public Set<ItemType> getItemType() {
        return itemType;
    }
    public void setItemType(Set<ItemType> itemType) {
        this.itemType = itemType;
    }
    public Dimgroup getDimgroup() {
        return dimgroup;
    }
    public void setDimgroup(Dimgroup dimgroup) {
        this.dimgroup = dimgroup;
    }
}
