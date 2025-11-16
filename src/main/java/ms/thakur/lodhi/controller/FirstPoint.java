package ms.thakur.lodhi.controller;

import ms.thakur.lodhi.common.web.request.ProfileRequest;
import ms.thakur.lodhi.common.web.response.ProfileResponse;
import ms.thakur.lodhi.common.web.response.WebApiResult;
import ms.thakur.lodhi.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FirstPoint {

    @Autowired
    private IProfileService profileService;

    @PostMapping("/addProfile")
    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<WebApiResult<String>> getFirstPoint(
            @RequestBody ProfileRequest profileRequest,
            @RequestParam(value = "widgetId", required = true) final String widgetId
    ){
        return profileService.addProfile(profileRequest);
    }


    @GetMapping("/searchProfile")
    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<WebApiResult<ProfileResponse>> getFirstPoint(
            @RequestParam(value = "profileId", required = true) final String profileId
    ){
        if(profileId == null || profileId.isEmpty() || !profileId.matches("^[0-9]+$")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(WebApiResult.createFailureResult("Invalid Input!"));
        }
        return profileService.searchProfile(profileId);
    }

    @GetMapping("/searchAllProfiles")
    public ResponseEntity<WebApiResult<List<ProfileResponse>>> getAllProfiles(
            @RequestParam(value = "pageNumber", required = true) final int pageNumber,
            @RequestParam(value = "pageSize", required = true) final int pageSize
    ){
        if(pageNumber <= 0 || pageSize <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(WebApiResult.createFailureResult("Invalid Input!"));
        }
        return profileService.getAllProfiles(pageNumber, pageSize);
    }


}
