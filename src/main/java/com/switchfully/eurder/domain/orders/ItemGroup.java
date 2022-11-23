package com.switchfully.eurder.domain.orders;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "item_group")
public class ItemGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_group_seq")
    @SequenceGenerator(name = "item_group_seq", sequenceName = "item_group_seq",allocationSize = 1)
    private Integer id;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "amount_ordered")
    private Integer amountOrdered;
    @Column(name = "shipping_date")
    private LocalDate shippingDate;
    @Column(name = "item_group_price")
    private BigDecimal itemGroupPrice;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public ItemGroup() {
    }

    public ItemGroup(String itemName, int amountOrdered, LocalDate shippingDate, BigDecimal itemGroupPrice, Order order) {
        this.itemName = itemName;
        this.amountOrdered = amountOrdered;
        this.shippingDate = shippingDate;
        this.itemGroupPrice = itemGroupPrice;
        this.order = order;
    }

    public BigDecimal getItemGroupPrice() {
        return itemGroupPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public Integer getAmountOrdered() {
        return amountOrdered;
    }
}
