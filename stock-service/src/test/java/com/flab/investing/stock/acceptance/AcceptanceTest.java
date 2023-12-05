package com.flab.investing.stock.acceptance;

import com.flab.investing.stock.repository.StockIntradayRepository;
import com.flab.investing.util.DataCleanUp;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @LocalServerPort
    int port;

    @Autowired
    private DataCleanUp dataCleanUp;

    @Autowired
    private StockIntradayRepository stockIntradayRepository;

    public void setUp() {
        RestAssured.port = port;
        dataCleanUp.execute();
        stockIntradayRepository.deleteAll();
    }
}
