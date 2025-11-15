package ms.thakur.lodhi.repository;

import ms.thakur.lodhi.common.dto.ProfileDto;
import ms.thakur.lodhi.utility.result.RepositoryResult;

public interface IProfileRepository {

    public static final String INVALID_DATA = "Invalid Data";
    public static final String FAILURE_DEPENDENCY = "Resource error occured";
    public static final String ADDED_SUCCESSFULLY = "Data added successfully";
    public static final String SUCCESS = "Success";

    RepositoryResult<String> addProfile(ProfileDto profile);

}
