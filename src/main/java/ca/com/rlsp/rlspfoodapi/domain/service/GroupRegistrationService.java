package ca.com.rlsp.rlspfoodapi.domain.service;

import ca.com.rlsp.rlspfoodapi.domain.exception.CityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityIsForeignKeyException;
import ca.com.rlsp.rlspfoodapi.domain.exception.EntityNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.exception.GroupNotFoundException;
import ca.com.rlsp.rlspfoodapi.domain.model.City;
import ca.com.rlsp.rlspfoodapi.domain.model.Group;
import ca.com.rlsp.rlspfoodapi.domain.repository.GroupRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupRegistrationService {

    private static final String MSG_CODE_IS_BEING_USED_AS_SECONDARY_KEY  = "Group name having code %d cannot be removed, because that is being used as secondary key";
    private GroupRepository groupRepository;

    public GroupRegistrationService(final GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Transactional
    public Group save(Group grupo) {
        return groupRepository.save(grupo);
    }


    @Transactional
    public void delete(Long groupId){
        try {

            groupRepository.deleteById(groupId);
            groupRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new GroupNotFoundException(groupId);
        } catch (DataIntegrityViolationException e){
            throw new EntityIsForeignKeyException(String.format(MSG_CODE_IS_BEING_USED_AS_SECONDARY_KEY, groupId));
        }

    }

    public Group FindOrFail(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
    }

}
