package com.I2R.DemoServer.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Customer_Order")
@Data
public class OrderData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "xml_key")
    private String key;

    @Column(name = "xml_value",columnDefinition = "TEXT")
    private String value;
}
