package de.bht.azur.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.arc.All;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "group_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupUser extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;
    @ManyToOne
    @JoinColumn(name = "group_id")
    @Getter
    @Setter
    private Group group;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    @JsonIgnore
    private User user;
    @Getter
    @Setter
    private boolean owner;
    @Getter
    @Setter
    @Enumerated(EnumType.ORDINAL)
    private GroupStatus status;

    public static GroupUser findGroupUser(Long userId, Long groupId) {
        return GroupUser
                .find("user.id = ?1 and group.id = ?2", userId, groupId)
                .firstResult();
    }

    public static List<GroupUser> findByUserId(Long userId) {
        return list("user.id", userId);
    }

    public static void removeGroupForUser(Long userId, Long groupId) {
        GroupUser.delete("user.id = ?1 and group.id = ?2", userId, groupId);
    }
}
