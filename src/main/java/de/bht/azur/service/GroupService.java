package de.bht.azur.service;

import de.bht.azur.model.Group;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class GroupService {
    @Transactional
    public Group findSingleGroup(Long groupId) {
        Group group = Group.findById(groupId);
        if(group == null) {
            throw new NotFoundException();
        }
        return group;
    }

    @Transactional
    public List<Group> getAllGroups() {
        return Group.listAll();
    }

    @Transactional
    public void addNewGroup(Group newGroup) {
        newGroup.persist();
    }

    @Transactional
    public Group updateGroup(Long groupId, Group group) {
        Group existingGroup = findSingleGroup(groupId);
        existingGroup.setName(group.getName());
        existingGroup.persist();
        return existingGroup;
    }

    @Transactional
    public void deleteGroup(Long groupId) {
        Group.deleteById(groupId);
    }
}
