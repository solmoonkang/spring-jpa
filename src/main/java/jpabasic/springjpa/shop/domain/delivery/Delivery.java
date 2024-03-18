package jpabasic.springjpa.shop.domain.delivery;

import jpabasic.springjpa.shop.domain.BaseEntity;
import jpabasic.springjpa.shop.domain.order.Order;

import javax.persistence.*;

@Entity
public class Delivery extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    private String city;

    private String zipcode;

    private DeliveryStatus status;
}
