package de.bht.azur.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "group_user")
@AllArgsConstructor
@NoArgsConstructor
public class GroupUser extends PanacheEntity {
    @ManyToOne
    @JoinColumn(name = "group_id")
    @Getter
    @Setter
    private Group group;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User user;
    @Getter
    @Setter
    private boolean owner;
    @Getter
    @Setter
    private GroupStatus status;

}
