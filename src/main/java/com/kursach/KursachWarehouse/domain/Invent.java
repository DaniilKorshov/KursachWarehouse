package com.kursach.KursachWarehouse.domain;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="invent")
public class Invent {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;
    private String unit;

    private String dimgroupId;
    private String itemType;
}
