package ms.thakur.lodhi.common.jpa;

import ms.thakur.lodhi.common.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProfileRepository extends JpaRepository<Profile, Long> {
}
