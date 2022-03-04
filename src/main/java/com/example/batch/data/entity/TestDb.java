package com.example.batch.data.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author kunanan.t
 */

@Entity
@Data
public class TestDb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String one;
    private Integer two;
    private String three;

    public TestDb(){

    }
}
