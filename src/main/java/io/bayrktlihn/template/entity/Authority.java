package io.bayrktlihn.template.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_authority")
public class Authority extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "authorities")
    private List<Role> roles = new ArrayList<>();

    @ManyToMany(mappedBy = "authorities")
    private List<User> users = new ArrayList<>();
}
