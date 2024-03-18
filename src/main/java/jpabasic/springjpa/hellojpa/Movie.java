package jpabasic.springjpa.hellojpa;

import javax.persistence.Entity;

@Entity
public class Movie extends ItemInfo {

    private String director;

    private String actor;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
