package ms.thakur.lodhi.repository;

import ms.thakur.lodhi.common.dto.ProfileDto;
import ms.thakur.lodhi.common.entity.Profile;
import ms.thakur.lodhi.common.jpa.JpaProfileRepository;
import ms.thakur.lodhi.utility.SnowflakeIdGenerator;
import ms.thakur.lodhi.utility.result.RepositoryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfileRepository implements IProfileRepository {

    @Autowired
    private JpaProfileRepository repository;

    @Override
    public RepositoryResult<String> addProfile(ProfileDto profile) {
        try{
            SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1);
            profile.setId(idGenerator.nextId());
            var entity = Profile.fromDto(profile);
            repository.save(entity);
        }
        catch (DataIntegrityViolationException e){
            return RepositoryResult.createFailureResult(INVALID_DATA);
        }
        catch (Exception e){
            return RepositoryResult.createFailureResult(FAILURE_DEPENDENCY);
        }
        return RepositoryResult.createSuccessResult(ADDED_SUCCESSFULLY, SUCCESS);
    }

    @Override
    public RepositoryResult<ProfileDto> searchProfileById(String profileId) {
        try{
            Optional<Profile> profile = repository.findById(Long.parseLong(profileId));
            return profile.map(value -> RepositoryResult.createSuccessResult(value.toDto(), SUCCESS)).orElseGet(() -> RepositoryResult.createFailureResult(NO_RECORD_FOUND));
        }
        catch (Exception e){
            return RepositoryResult.createFailureResult(FAILURE_DEPENDENCY);
        }
    }

    @Override
    public RepositoryResult<List<ProfileDto>> searchAllProfiles(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("firstName").ascending());
        try{
            Page<Profile> profileList = repository.findAll(pageable);
            if(profileList.hasContent()){
                List<ProfileDto> dtos = new ArrayList<>();
                profileList.get().forEach(value -> dtos.add(value.toDto()));
                var result = RepositoryResult.createSuccessResult(dtos, SUCCESS);
                result.setTotalPages(profileList.getTotalPages());
                result.setPageNumber(profileList.getNumber());
                result.setPageSize(profileList.getSize());
                result.setTotalItems(profileList.getTotalElements());
                return result;
            }
            return RepositoryResult.createFailureResult(NO_RECORD_FOUND);
        }
        catch (Exception e){
            return RepositoryResult.createFailureResult(FAILURE_DEPENDENCY);
        }
    }
}
