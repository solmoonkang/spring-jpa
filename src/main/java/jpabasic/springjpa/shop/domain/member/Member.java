package jpabasic.springjpa.shop.domain.member;

import jpabasic.springjpa.shop.domain.Address;
import jpabasic.springjpa.shop.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    @Embedded
    private Address address;
}
