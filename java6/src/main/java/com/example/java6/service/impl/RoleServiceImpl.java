package com.example.java6.service.impl;

import com.example.java6.model.Role;
import com.example.java6.repository.RoleRepository;
import com.example.java6.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleRepository roleRepository;
		
	@Override
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}
	
	@Override
	public Role getRoleById(Long id) {
		return roleRepository.findById(id).get();
	}
	
	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}
	
	@Override
	public void deleteRoleById(Long id) {
		roleRepository.deleteById(id);	
	}
	
	@Override
	public Role updateRole(Role role) {
		return roleRepository.save(role);
	}
}
