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

    /**
     * Adds a new admin, checks if one with the same username already exists
     * @param a the admin you want to add
     * @return if the admin was added
     */
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

    /**
     * Find a client by name
     * @param name the name of the client
     * @return the client found (null if nothing)
     */
    @Transactional
    public Admin  findAdmin(String name){return adminRepository.findByUsername(name);}

    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
}
