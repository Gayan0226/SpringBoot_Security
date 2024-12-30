package com.springboot.security.service.impl;

import com.springboot.security.configuration.AdminPrinciple;
import com.springboot.security.model.Admin;
import com.springboot.security.repo.AdminRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsService implements UserDetailsService {
    private final AdminRepository repository;

    public AdminDetailsService(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Admin admin= repository.findAdminByUserName(username);
       if(admin==null){
           try {
               throw new ChangeSetPersister.NotFoundException();
           } catch (ChangeSetPersister.NotFoundException e) {
               throw new RuntimeException(e);
           }
       }
        return new AdminPrinciple(admin);
    }
}
