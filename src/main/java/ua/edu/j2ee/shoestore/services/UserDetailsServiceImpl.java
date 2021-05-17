package ua.edu.j2ee.shoestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.edu.j2ee.shoestore.dao.UserDao;
import ua.edu.j2ee.shoestore.model.User;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with email: " + email);
        }

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword().toLowerCase(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(user.getRole()));
    }

    private static List<GrantedAuthority> getAuthorities (String role) {
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        return Collections.singletonList(authority);
    }


}
