package ms.thakur.lodhi.repository;

import ms.thakur.lodhi.common.dto.ProfileDto;
import ms.thakur.lodhi.utility.result.RepositoryResult;

import java.util.List;

public interface IProfileRepository {

    String INVALID_DATA = "Invalid Data";
    String FAILURE_DEPENDENCY = "Resource error occured";
    String ADDED_SUCCESSFULLY = "Data added successfully";
    String SUCCESS = "Success";
    String NO_RECORD_FOUND = "No record found";

    RepositoryResult<String> addProfile(ProfileDto profile);

    RepositoryResult<ProfileDto> searchProfileById(String profileId);

    RepositoryResult<List<ProfileDto>> searchAllProfiles(int pageNumber, int pageSize);

}
