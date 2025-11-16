package ms.thakur.lodhi.service;

import ms.thakur.lodhi.common.web.request.ProfileRequest;
import ms.thakur.lodhi.common.web.response.ProfileResponse;
import ms.thakur.lodhi.common.web.response.WebApiResult;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProfileService {

    ResponseEntity<WebApiResult<String>> addProfile(ProfileRequest request);

    ResponseEntity<WebApiResult<ProfileResponse>> searchProfile(String profileId);

    ResponseEntity<WebApiResult<List<ProfileResponse>>> getAllProfiles(int pageNumber, int pageSize);
}
