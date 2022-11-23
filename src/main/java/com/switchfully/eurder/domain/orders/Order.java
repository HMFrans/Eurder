package com.switchfully.eurder.domain.orders;

import com.switchfully.eurder.domain.customers.Customer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_group_seq")
    @SequenceGenerator(name = "item_group_seq", sequenceName = "item_group_seq",allocationSize = 1)
    private Integer Id;

    @Column(name = "total_price")
    private BigDecimal totalPrice = BigDecimal.valueOf(0);
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;



    public Order() {
    }

    public Order(Customer customer) {
        this.customer = customer;
    }
    public Integer getId() {
        return Id;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
