package com.oms.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "STORES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    
    @Id
    private String storeId;
    
    private String zipCode;
    private String name;
    private String address;
    private String city;
    private String state;
}
