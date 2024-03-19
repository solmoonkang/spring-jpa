package springjpa.shop.domain.delivery;

import jakarta.persistence.*;
import springjpa.shop.domain.Address;
import springjpa.shop.domain.BaseEntity;
import springjpa.shop.domain.order.Order;

import static jakarta.persistence.FetchType.LAZY;

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
