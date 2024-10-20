package com.example.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="`Order`")
public class Order {
    @Id
    private int id;
    private int itemId;
    private String orderDate;
    private int amount;
}
