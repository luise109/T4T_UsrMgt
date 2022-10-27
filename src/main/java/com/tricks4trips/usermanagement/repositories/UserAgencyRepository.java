

package com.tricks4trips.usermanagement.repositories;

import com.tricks4trips.usermanagement.entities.UserAgency;
import org.springframework.data.repository.CrudRepository;

public interface UserAgencyRepository extends CrudRepository<UserAgency, Long> {
    UserAgency findByCredential_Id(Long id);
}