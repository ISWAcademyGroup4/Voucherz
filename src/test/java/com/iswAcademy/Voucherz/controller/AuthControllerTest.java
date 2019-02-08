package com.iswAcademy.Voucherz.controller;

import com.iswAcademy.Voucherz.domain.RoleName;
import com.iswAcademy.Voucherz.domain.User;
import com.iswAcademy.Voucherz.service.IUserService;
import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private IUserService userService;

    @Test
    public void login() {
    }

    @Test
    public void signup() {
    }

    @Test
    public void adminsignup() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void findUser() {
    }

    @Test
    public void findAll() throws Exception {
//        User user = new User
        User user = new User();
        user.setFirstName("Chinedu");
        user.setLastName("Mefendja");
        user.setEmail("mefendja.chinedu");
        user.setPassword("password");
        user.setPhoneNumber("80994463545");
        user.setCompanySize(200);
        user.setActive(false);
        user.setDateCreated(LocalDateTime.MAX);
        user.setRole(RoleName.ROLE_USER.toString());

        User user2 = new User();
        user.setFirstName("Chinedu");
        user.setLastName("Mefendja");
        user.setEmail("mefendja.chinedu");
        user.setPassword("password");
        user.setPhoneNumber("80994463545");
        user.setCompanySize(200);
        user.setActive(false);
        user.setDateCreated(LocalDateTime.MAX);
        user.setRole(RoleName.ROLE_USER.toString());

//        when(userService.findAll("name")).thenReturn(Arrays.asList(user,user2));
    }

    @Test
    public void activate() {
    }
}