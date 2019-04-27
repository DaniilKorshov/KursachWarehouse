package com.kursach.KursachWarehouse.domain;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="line")
public class Line {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long itemId;

    private Long startLocation;
    private Long finishLocation;

    private Long qty;

    public Line() {
    }

}
