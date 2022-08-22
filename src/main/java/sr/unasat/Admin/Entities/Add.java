package sr.unasat.Admin.Entities;

import javax.persistence.*;

@Entity(name = "add")
@Table(name = "adds")
public class Add {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "add_name")
    private String name;

    public Add(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Add() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
