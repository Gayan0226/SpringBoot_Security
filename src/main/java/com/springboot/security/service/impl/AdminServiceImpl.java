package com.springboot.security.service.impl;

import com.springboot.security.dto.AdminRequestDto;
import com.springboot.security.model.Admin;
import com.springboot.security.repo.AdminRepository;
import com.springboot.security.service.AdminService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service

public class AdminServiceImpl implements AdminService {
    private final AdminRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public AdminServiceImpl(AdminRepository repository) {
        this.repository = repository;
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
}
