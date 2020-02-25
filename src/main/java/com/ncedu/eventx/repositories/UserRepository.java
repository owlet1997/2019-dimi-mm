package com.ncedu.eventx.repositories;

import com.ncedu.eventx.model.domain.UserDAO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDAO, Integer> {
}
