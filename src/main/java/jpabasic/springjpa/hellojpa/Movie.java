package jpabasic.springjpa.hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter
public class Movie extends ItemInfo {

    private String director;

    private String actor;
}
