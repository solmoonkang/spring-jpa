package jpabasic.springjpa.shop.domain.delivery;

import jpabasic.springjpa.shop.domain.Address;
import jpabasic.springjpa.shop.domain.BaseEntity;
import jpabasic.springjpa.shop.domain.order.Order;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Delivery extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    private DeliveryStatus status;

    @Embedded
    private Address address;
}
