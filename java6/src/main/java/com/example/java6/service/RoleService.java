package com.example.java6.service;

import com.example.java6.model.Role;

import java.util.List;

public interface RoleService {

	Role updateRole(Role role);

	void deleteRoleById(Long id);

	Role saveRole(Role role);

	Role getRoleById(Long id);

	List<Role> getAllRoles();

}
