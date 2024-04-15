package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.entities.concretes.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);

    User add(User user);
}
