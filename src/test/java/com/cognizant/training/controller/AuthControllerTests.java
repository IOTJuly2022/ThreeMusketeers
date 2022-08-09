package com.cognizant.training.controller;

import com.cognizant.training.TestUtils;
import com.cognizant.training.model.User;
import com.cognizant.training.repository.UserRepository;
import com.cognizant.training.request.LoginRequest;
import com.cognizant.training.response.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTests {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private final PodamFactory factory = new PodamFactoryImpl();

    private User user;
    private String plainUserPassword;

    @BeforeEach
    public void setupEach() {
        userRepository.deleteAll();
        userRepository.flush();
        user = factory.manufacturePojo(User.class);
        user.setEmail(TestUtils.CreateRandomEmail());
        plainUserPassword = TestUtils.CreateRandomPassword(30, false);
        user.setPassword(plainUserPassword);
        userRepository.saveAndFlush(user);
    }

    @Test
    public void userLogin_RequiresBody() {
        ResponseEntity<LoginResponse> resp = restTemplate.postForEntity(
                "/v1/login",
                null,
                LoginResponse.class
        );

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void userLogin_RequiresCorrectEmail() {
        LoginRequest request = new LoginRequest();
        request.setEmail(user.getEmail() + "a");
        request.setPassword(user.getPassword());

        ResponseEntity<LoginResponse> resp = restTemplate.postForEntity(
                "/v1/login",
                request,
                LoginResponse.class
        );

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void userLogin_RequiresCorrectPassword() {
        LoginRequest request = new LoginRequest();
        request.setEmail(user.getEmail());
        request.setPassword(user.getPassword() + "a");

        ResponseEntity<LoginResponse> resp = restTemplate.postForEntity(
                "/v1/login",
                request,
                LoginResponse.class
        );

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void userLogin_GeneratesToken() {
        LoginRequest request = new LoginRequest();
        request.setEmail(user.getEmail());
        request.setPassword(plainUserPassword);

        user.setPassword(plainUserPassword);
        userRepository.saveAndFlush(user);

        ResponseEntity<LoginResponse> resp = restTemplate.postForEntity(
                "/v1/login",
                request,
                LoginResponse.class
        );

        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().getEmail()).isEqualTo(user.getEmail());
        assertThat(resp.getBody().getFirstName()).isEqualTo(user.getFirstName());
        assertThat(resp.getBody().getLastName()).isEqualTo(user.getLastName());
        assertThat(resp.getBody().getToken()).isNotNull();
    }
}
