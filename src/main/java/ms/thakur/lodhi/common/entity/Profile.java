package ms.thakur.lodhi.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import ms.thakur.lodhi.common.dto.ProfileDto;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

@Entity
@Table(name = "userprofile")
@Data
public class Profile {

    @Column(name = "id")
    @Id
    private long id;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "zipcode")
    private String zipCode;
    @Column(name = "jobtitle")
    private String jobTitle;
    @Column(name = "jobdescription")
    private String jobDescription;

    @Column(name = "createdon")
    private Timestamp createdOn;
    @Column(name = "createdby")
    private String createdBy;

    @Column(name = "lastupdatedon")
    private Timestamp updatedOn;
    @Column(name = "lastupdatedby")
    private String updatedBy;

    @Column(name = "deletedon")
    private Timestamp deletedOn;
    @Column(name = "deletedby")
    private String deletedBy;



    public static Profile fromDto(ProfileDto profileDto) {
        Profile profile = new Profile();
        BeanUtils.copyProperties(profileDto, profile);
        return profile;
    }

    public ProfileDto toDto() {
        ProfileDto profileDto = new ProfileDto();
        BeanUtils.copyProperties(this, profileDto);
        return profileDto;
    }

}
