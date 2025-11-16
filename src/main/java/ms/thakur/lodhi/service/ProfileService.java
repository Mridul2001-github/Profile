package ms.thakur.lodhi.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import ms.thakur.lodhi.common.dto.ProfileDto;
import ms.thakur.lodhi.common.web.request.ProfileRequest;
import ms.thakur.lodhi.common.web.response.ProfileResponse;
import ms.thakur.lodhi.common.web.response.WebApiResult;
import ms.thakur.lodhi.repository.ProfileRepository;
import ms.thakur.lodhi.utility.exception.InvalidDataException;
import ms.thakur.lodhi.utility.result.RepositoryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProfileService implements IProfileService {

    @Autowired
    private Validator validator;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public ResponseEntity<WebApiResult<String>> addProfile(ProfileRequest request) {
        var dto = request.toDto();
        try{
            validateProfile(dto);
            var repoResult = profileRepository.addProfile(dto);
            if(repoResult.isSuccess()){
                return ResponseEntity.status(HttpStatus.CREATED).body(WebApiResult.createSuccessResult(ProfileRepository.ADDED_SUCCESSFULLY, ProfileRepository.SUCCESS));
            }
            if(repoResult.getMessage().equals(ProfileRepository.INVALID_DATA)){
                throw new InvalidDataException(ProfileRepository.INVALID_DATA);
            }
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(WebApiResult.createFailureResult(ProfileRepository.FAILURE_DEPENDENCY));
        }
        catch (InvalidDataException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(WebApiResult.createFailureResult(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<WebApiResult<ProfileResponse>> searchProfile(String profileId) {
        RepositoryResult<ProfileDto> result =  profileRepository.searchProfileById(profileId);
        if(result.isSuccess()){
            return ResponseEntity.ok(WebApiResult.createSuccessResult(result.getResult().toResponse(), "Successfully searched profile"));
        }
        else if(result.getMessage().equals(ProfileRepository.NO_RECORD_FOUND)){
            return ResponseEntity.ok(WebApiResult.createSuccessResult(null, ProfileRepository.NO_RECORD_FOUND));
        }
        else{
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(WebApiResult.createFailureResult(ProfileRepository.FAILURE_DEPENDENCY));
        }
    }

    @Override
    public ResponseEntity<WebApiResult<List<ProfileResponse>>> getAllProfiles(int pageNumber, int pageSize) {
        RepositoryResult<List<ProfileDto>> result = profileRepository.searchAllProfiles(pageNumber, pageSize);
        if(result.isSuccess()){
            List<ProfileResponse> profileList = new ArrayList<>();
            result.getResult().forEach(value -> profileList.add(value.toResponse()));
            var rs = WebApiResult.createSuccessResult(profileList, ProfileRepository.SUCCESS);
            rs.setPageSize(result.getPageSize());
            rs.setPageNumber(result.getPageNumber());
            rs.setTotalPages(result.getTotalPages());
            rs.setTotalItems(String.valueOf(result.getTotalItems()));
            return ResponseEntity.ok(rs);
        }
        else if(result.getMessage().equals(ProfileRepository.NO_RECORD_FOUND)){
            return ResponseEntity.ok(WebApiResult.createSuccessResult(null, ProfileRepository.NO_RECORD_FOUND));
        }
        else{
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(WebApiResult.createFailureResult(ProfileRepository.FAILURE_DEPENDENCY));
        }
    }


    private void validateProfile(ProfileDto profile) throws InvalidDataException {
        Set<ConstraintViolation<ProfileDto>> violations = validator.validate(profile);
        if (!violations.isEmpty()) {
            StringBuilder message = new StringBuilder();
            for(ConstraintViolation<ProfileDto> violation : violations) {
                message.append(violation.getMessage() + "\n");
            }
            throw new InvalidDataException(message.toString());
        }
    }
}
