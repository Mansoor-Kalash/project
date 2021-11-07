package com.dammak.project401.Security;

import com.dammak.project401.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HospitalDetailsServicelmpl implements UserDetailsService {

    @Autowired
    HospitalRepo hospitalRepo;

    @Override
    public UserDetails loadUserByUsername(String usename) throws UsernameNotFoundException {
        return hospitalRepo.findByUserName(usename);
    }
}
