package com.ncedu.eventx.repositories;

import com.ncedu.eventx.model.domain.UserRoleDAO;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<UserRoleDAO, Integer> {
}
