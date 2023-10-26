package com.flab.investing.stock.integration.application;

import com.flab.investing.BatchTestSupport;
import com.flab.investing.stock.batch.application.KrxService;
import com.flab.investing.stock.batch.infrastructure.krx.KrxInfoRepository;
import com.flab.investing.stock.batch.infrastructure.krx.dto.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class KrxServiceTest extends BatchTestSupport {

    private final KrxService krxService;

    @MockBean
    private  KrxInfoRepository krxInfoRepository;

    private ApiResponse response;

    public KrxServiceTest(final KrxService krxService) {
        this.krxService = krxService;
    }

    @BeforeEach
    void setUp() {
        ApiResponse.Header header = new ApiResponse.Header("00", "NORMAL SERVICE");
        ApiResponse.Body body = new ApiResponse.Body(1, 1, 2309895,
                new ApiResponse.Items(
                        Collections.singletonList(
                                new ApiResponse.Item("20231024"
                                        , "A000120"
                                        , "KR7000120006"
                                        , "KOSPI"
                                        , "CJ대한통운"
                                        , "1101110006167"
                                        , "씨제이대한통운(주)"))
                ));
        response = new ApiResponse(new ApiResponse.Response(header, body));
    }

    @Test
    @DisplayName("한국거래소 주식 정보 받기")
    void findStocks() {
        given(krxService.send()).willReturn(response);

        ApiResponse response = krxService.send();

        ApiResponse.Item item = response.response().body().items().item().get(0);

        assertThat(item).isEqualTo(new ApiResponse.Item(
                "20231024"
                , "A000120"
                , "KR7000120006"
                , "KOSPI"
                , "CJ대한통운"
                , "1101110006167"
                , "씨제이대한통운(주)"));
    }

}
