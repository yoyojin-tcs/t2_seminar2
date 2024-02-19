package ru.tinkoff.edu.java.t2_seminar2.service.security;

import jakarta.annotation.PostConstruct;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

@Service
public class UserCredentialsService implements UserDetailsService {

    private final Map<String, UserCredentials> userCredentialsMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        String encryptedPassword = "$2a$12$Rxc3os6AnPNt/fgDtqzC8.DYie60NQAAv85L0ZzAyJIX.qmpKu13K"; //BCrypt Hash of "password"
        userCredentialsMap.put("user", new UserCredentials("user", encryptedPassword));
        userCredentialsMap.put("admin", new UserCredentials("admin", encryptedPassword));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentials credentials = userCredentialsMap.get(username);
        if (credentials == null)
            throw new UsernameNotFoundException(format("User with username '%s' not found!", username));
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("user"));
        return new User(credentials.username(), credentials.encryptedPassword(), authorities);
    }
}
