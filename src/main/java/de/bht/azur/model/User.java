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

@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User extends PanacheEntity {
    @OneToMany(mappedBy = "user")
    private List<AppointmentUser> appointments = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<GroupUser> groups = new ArrayList<>();
    @Getter
    @Setter
    private String givenName;
    @Getter
    @Setter
    private String familyName;
    @Getter
    @Setter
    private String email;

}
