package springjpa.shop.domain.member;

import jakarta.persistence.*;
import springjpa.shop.domain.Address;
import springjpa.shop.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;


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
