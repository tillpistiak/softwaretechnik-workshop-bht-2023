package de.bht.azur.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
public class Group extends PanacheEntity {
    @OneToMany(mappedBy = "group")
    @Getter
    @Setter
    private List<GroupUser> users = new ArrayList<>();
    @Getter
    @Setter
    private String name;
}
