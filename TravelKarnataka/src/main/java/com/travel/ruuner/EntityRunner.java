package com.travel.ruuner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.travel.entity.RegisterEntity;
import com.travel.service.RegisterService;

@Component
public class EntityRunner implements CommandLineRunner {

    @Autowired
    private RegisterService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        try {
            String email = "travels@gmail.com";
            if (!service.checkUser(email)) {
                RegisterEntity admin = new RegisterEntity();
                admin.setRole("admin");
                admin.setUserEmail(email);
                admin.setUserName("Admin");
                admin.setUserPhone("8956958642");
                admin.setUserPassword(passwordEncoder.encode("Travles@9753"));
                service.saveUser(admin);
                System.out.println("Admin user created successfully.");
            } else {
                System.out.println("Admin user already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
