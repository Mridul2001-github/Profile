package ms.thakur.lodhi.service;

import ms.thakur.lodhi.common.web.request.ProfileRequest;
import ms.thakur.lodhi.common.web.response.WebApiResult;
import org.springframework.http.ResponseEntity;

public interface IProfileService {

    ResponseEntity<WebApiResult<String>> addProfile(ProfileRequest request);

}
