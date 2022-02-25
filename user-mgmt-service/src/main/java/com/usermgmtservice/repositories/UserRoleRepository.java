package com.usermgmtservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermgmtservice.entities.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	public UserRole findUserRoleByRoleNameLike(String roleName);
}
