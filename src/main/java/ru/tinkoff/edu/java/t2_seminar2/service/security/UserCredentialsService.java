package ru.tinkoff.edu.java.t2_seminar2.service.security;

import jakarta.annotation.PostConstruct;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

@Service
public class UserCredentialsService implements UserDetailsService {

    public static final String ADMIN = "admin";
    private final Map<String, UserCredentials> userCredentialsMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        String encryptedPassword = "$2a$12$Rxc3os6AnPNt/fgDtqzC8.DYie60NQAAv85L0ZzAyJIX.qmpKu13K"; //BCrypt Hash of "password"
        userCredentialsMap.put("user", new UserCredentials("user", encryptedPassword));
        userCredentialsMap.put("admin", new UserCredentials(ADMIN, encryptedPassword));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentials credentials = userCredentialsMap.get(username);
        if (credentials == null)
            throw new UsernameNotFoundException(format("User with username '%s' not found!", username));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (ADMIN.equals(username)) authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(credentials.username(), credentials.encryptedPassword(), authorities);
    }
}
