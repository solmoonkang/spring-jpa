package jpabasic.springjpa.shop.domain.order;

import jpabasic.springjpa.shop.domain.delivery.Delivery;
import jpabasic.springjpa.shop.domain.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
}
