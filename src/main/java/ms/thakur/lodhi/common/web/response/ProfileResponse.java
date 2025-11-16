package ms.thakur.lodhi.common.web.response;

import lombok.Data;
import ms.thakur.lodhi.common.model.ProfileModel;

@Data
public class ProfileResponse extends ProfileModel {
    private String profileId;
}
