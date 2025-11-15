package ms.thakur.lodhi.repository;

import ms.thakur.lodhi.common.dto.ProfileDto;
import ms.thakur.lodhi.common.entity.Profile;
import ms.thakur.lodhi.common.jpa.JpaProfileRepository;
import ms.thakur.lodhi.utility.SnowflakeIdGenerator;
import ms.thakur.lodhi.utility.result.RepositoryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

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
}
