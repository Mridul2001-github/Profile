package ms.thakur.lodhi.common.web.request;

import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.Data;
import ms.thakur.lodhi.common.dto.ProfileDto;
import ms.thakur.lodhi.common.model.ProfileModel;
import org.springframework.beans.BeanUtils;

@Data
public class ProfileRequest extends ProfileModel {


    public ProfileDto toDto(){
        ProfileDto profileDto = new ProfileDto();
        BeanUtils.copyProperties(this, profileDto);
        return profileDto;
    }

}
