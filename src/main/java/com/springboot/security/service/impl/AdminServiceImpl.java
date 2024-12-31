package com.springboot.security.service.impl;

import com.springboot.security.dto.AdminRequestDto;
import com.springboot.security.model.Admin;
import com.springboot.security.repo.AdminRepository;
import com.springboot.security.service.AdminService;
import com.springboot.security.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service

public class AdminServiceImpl implements AdminService {
    private final AdminRepository repository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
// Jwt Service For Get The Generate Valid Token To Send
    private final JwtService jwtService;
    public AdminServiceImpl(AdminRepository repository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public Admin saveAdmin(AdminRequestDto adminRequestDto) {
        return repository.save(new Admin(adminRequestDto.getUser(), encoder.encode(adminRequestDto.getPassword())));
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> all = repository.findAll();
        return (!all.isEmpty()) ? all : Collections.emptyList();
    }

    @Override
    public String isVerify(AdminRequestDto adminRequestDto) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                adminRequestDto.getUser(), adminRequestDto.getPassword()
                        ));
        if(authentication.isAuthenticated()){
            return  jwtService.generateToken(adminRequestDto.getUser());
        }
        return "Error Not Valid !";
    }
}
