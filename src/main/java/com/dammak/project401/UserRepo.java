package com.dammak.project401;



import com.dammak.project401.models.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<AppUser,Long> {
    AppUser findByUserName(String username);
}