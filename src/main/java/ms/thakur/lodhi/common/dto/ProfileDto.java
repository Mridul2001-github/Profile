package ms.thakur.lodhi.common.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ms.thakur.lodhi.common.web.response.ProfileResponse;
import org.springframework.beans.BeanUtils;

@Data
public class ProfileDto {

    private Long id;

    @NotNull
    @Size(min = 1, max = 100, message = "Invalid Name")
    private  String firstName;
    private String lastName;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "invalid email format")
    private String email;

    @NotNull
    @Pattern(regexp = "^[+]?[0-9]{10,13}$", message = "Invalid Phone Number")
    private String phoneNumber;

    @NotNull
    private String address;

    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String country;

    @NotNull
    @Pattern(regexp = "^[+]?[0-9]{5,7}$")
    private String zipCode;
    @NotNull
    private String jobTitle;
    private String jobDescription;


    public ProfileResponse toResponse(){
        ProfileResponse response = new ProfileResponse();
        BeanUtils.copyProperties(this,response);
        response.setProfileId(this.getId().toString());
        return response;
    }

}
