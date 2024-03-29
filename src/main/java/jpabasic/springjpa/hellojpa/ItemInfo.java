package jpabasic.springjpa.hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class ItemInfo {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;
}
