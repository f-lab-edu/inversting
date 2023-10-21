package com.flab.investing.user.acceptance;

import com.flab.investing.util.DataCleanUp;
import com.flab.investing.util.RedisDataCleanUp;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    private DataCleanUp dataCleanUp;
    @Autowired
    private RedisDataCleanUp redisDataCleanUp;

    public void setUp() {
        RestAssured.port = port;
        dataCleanUp.execute();
        redisDataCleanUp.execute();
    }
}
