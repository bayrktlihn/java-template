package io.bayrktlihn.template.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_role")
public class Role extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "role_authority", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorities = new ArrayList<>();

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();
}
