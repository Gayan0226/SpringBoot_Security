package com.springboot.security.controller;

import com.springboot.security.dto.AdminRequestDto;
import com.springboot.security.model.Admin;
import com.springboot.security.service.AdminService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {
    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Admin> savedAdmin(@RequestBody AdminRequestDto adminRequestDto){
        return new ResponseEntity<Admin>(service.saveAdmin(adminRequestDto), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminRequestDto adminRequestDto){
        return new ResponseEntity<String>(service.isVerify(adminRequestDto), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmin(){
        return new ResponseEntity<List<Admin>>(service.getAll(), HttpStatus.OK);
    }
}
