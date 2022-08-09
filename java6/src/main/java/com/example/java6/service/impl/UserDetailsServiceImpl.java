package com.example.java6.service.impl;

import com.example.java6.model.*;
import com.example.java6.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerRoleRepository customerRoleRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantRoleRepository restaurantRoleRepository;
	
	@Autowired
	private ShipperRepository shipperRepository;
	
	@Autowired
	private ShipperRoleRepository shipperRoleRepository;
	
	
	@Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        log.info("load : "+userName);
        Customer customer = this.customerRepository.findByEmail(userName);
        Restaurant restaurant = this.restaurantRepository.findByEmail(userName);
        Shipper shipper = this.shipperRepository.findByEmail(userName);
        UserDetails userDetails;
        if (customer == null && restaurant == null && shipper == null) {
            log.info("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        } 
        	
        // [ROLE_USER, ROLE_ADMIN,..] 
        List<RestaurantRole> restaurantRoles = this.restaurantRoleRepository.findByRestaurant(restaurant);
        List<CustomerRole> customerRoles = this.customerRoleRepository.findByCustomer(customer);
        List<ShipperRole> shipperRoles = this.shipperRoleRepository.findByShipper(shipper);
        log.info("List CustomerRole:{}  ", customerRoles);
        log.info("List RestaurantRole:{}  ", restaurantRoles);
        log.info("List ShipperRole:{}  ", shipperRoles);
        Set<GrantedAuthority> grantList = new HashSet<GrantedAuthority>();
        if (customerRoles != null) {
            for (CustomerRole customerRole : customerRoles) {
                    // ROLE_USER, ROLE_ADMIN,..
                log.info("có cus");
                GrantedAuthority authority = new SimpleGrantedAuthority(customerRole.getRole().getRoleName());
                grantList.add(authority);
            }
        }
            
        if (restaurantRoles != null) {
           for (RestaurantRole restaurantRole : restaurantRoles) {
                    // ROLE_USER, ROLE_ADMIN,..
               log.info("có restau");
               GrantedAuthority authority = new SimpleGrantedAuthority(restaurantRole.getRole().getRoleName());
               grantList.add(authority);
           }
        }
        
        if (shipperRoles != null) {
            for (ShipperRole shipperRole : shipperRoles) {
                     // ROLE_USER, ROLE_ADMIN,..
                log.info("có shipper");
                GrantedAuthority authority = new SimpleGrantedAuthority(shipperRole.getRole().getRoleName());
                grantList.add(authority);
            }
         }

        log.info("List :{}  ", grantList);
        if(shipper != null) {
        	userDetails = new User(shipper.getEmail(), //
                   shipper.getPassword(), grantList);
        } else if(restaurant != null){
        	userDetails =  new User(restaurant.getEmail(), //
                    restaurant.getPassword(), grantList);
        } else {
        	userDetails = new User(customer.getEmail(), //
                    customer.getPassword(), grantList);
        }


        return userDetails;
	}
}
