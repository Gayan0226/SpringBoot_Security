package com.springboot.security.service;

import com.springboot.security.dto.AdminRequestDto;
import com.springboot.security.model.Admin;

import java.util.List;

public interface AdminService {
    Admin saveAdmin(AdminRequestDto adminRequestDto);

    List<Admin> getAll();

    String isVerify(AdminRequestDto adminRequestDto);
}
