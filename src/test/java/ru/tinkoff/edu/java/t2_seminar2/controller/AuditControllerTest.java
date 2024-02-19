package ru.tinkoff.edu.java.t2_seminar2.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.tinkoff.edu.java.t2_seminar2.configurations.security.SecurityConfig;
import ru.tinkoff.edu.java.t2_seminar2.dto.UserList;
import ru.tinkoff.edu.java.t2_seminar2.service.UserService;
import ru.tinkoff.edu.java.t2_seminar2.service.security.UserCredentialsService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuditController.class)
@Import(SecurityConfig.class)
class AuditControllerTest {

    @MockBean
    UserCredentialsService userCredentialsService;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        when(userCredentialsService.loadUserByUsername(anyString())).thenAnswer(invocationOnMock -> {
            String username = invocationOnMock.getArgument(0);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            if (username.equals("admin")) {
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
            }
            return new User(username, "pass", authorities);
        });
    }

    @Test
    @WithMockUser(username = "admin")
    public void whenUserAccessUserAuditSecuredEndpoint_thenForbidden() throws Exception {
        mvc.perform(get("/logs"))
                .andExpect(status().isForbidden());
    }

}