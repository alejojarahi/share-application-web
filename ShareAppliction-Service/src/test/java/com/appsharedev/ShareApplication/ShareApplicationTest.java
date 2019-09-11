package com.appsharedev.ShareApplication;

import com.appsharedev.ShareApplication.model.User;
import com.appsharedev.ShareApplication.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShareApplicationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void createUsersTest() {
        User usertest = new User();
        usertest.setPassword(encoder.encode("123"));
        usertest.setDocument(11455);
        usertest.setEmail("pedrito@unal.edu.co");
        usertest.setFirstname("Pedrito");
        usertest.setLastname("Gonzalez");
        User retorno = userRepository.save(usertest);

        //assertTrue(retorno.getPassword().equalsIgnoreCase(usertest.getPassword()));

    }
}
