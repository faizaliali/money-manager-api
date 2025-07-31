package in.faizali.moneymanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.faizali.moneymanager.entity.ProfileEntity;

public interface ProfileRepository  extends JpaRepository<ProfileEntity,Long> {
   
    //select * from tbl_profiles where email=?
    Optional<ProfileEntity> findByEmail(String email);

    //select * from tbl_profiles where activation_token=?
    Optional<ProfileEntity> findByActivationToken(String activationToken);
      
    }


