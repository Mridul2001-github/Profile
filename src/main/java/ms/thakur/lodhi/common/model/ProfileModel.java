package ms.thakur.lodhi.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileModel {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String jobTitle;
    private String jobDescription;
}
