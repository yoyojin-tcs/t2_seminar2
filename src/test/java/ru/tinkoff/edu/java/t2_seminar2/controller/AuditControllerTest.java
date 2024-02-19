package ru.tinkoff.edu.java.t2_seminar2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.tinkoff.edu.java.t2_seminar2.configurations.security.SecurityConfig;
import ru.tinkoff.edu.java.t2_seminar2.service.security.UserCredentialsService;

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

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void whenUserAccessUserAuditSecuredEndpoint_thenForbidden() throws Exception {
        mvc.perform(get("/logs"))
                .andExpect(status().isOk());
    }

}