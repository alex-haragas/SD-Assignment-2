package com.example.sa2.Service;

import com.example.sa2.model.Admin;
import com.example.sa2.repository.AdminRepository;
import com.example.sa2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;


    @Transactional
    public boolean addAdmin(Admin a){
        if(adminRepository.findByUsername(a.getUsername())!=null){
            return false;
        }
        else{
            adminRepository.save(a);
            return true;
        }
    }

    @Transactional
    public Admin  findAdmin(String name){return adminRepository.findByUsername(name);}
}
