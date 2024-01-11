package de.bht.azur.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
public class User extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Getter
    @Setter
    @JsonIgnore
    private List<AppointmentUser> appointments = new ArrayList<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Getter
    @Setter
    @JsonIgnore
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
