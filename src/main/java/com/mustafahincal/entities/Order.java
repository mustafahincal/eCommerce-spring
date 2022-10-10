package com.mustafahincal.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @Column(name = "id")
    private int id;

    @JoinColumn(name = "product_id", nullable = false)
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

}
