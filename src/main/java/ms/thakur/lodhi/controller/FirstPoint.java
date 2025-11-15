package ms.thakur.lodhi.controller;

import ms.thakur.lodhi.common.web.request.ProfileRequest;
import ms.thakur.lodhi.common.web.response.WebApiResult;
import ms.thakur.lodhi.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FirstPoint {

    @Autowired
    private IProfileService profileService;

    @PostMapping("/addProfile")
    public ResponseEntity<WebApiResult<String>> getFirstPoint(
            @RequestBody ProfileRequest profileRequest,
            @RequestParam(value = "widgetId", required = true) final String widgetId
    ){
        return profileService.addProfile(profileRequest);
    }

}
