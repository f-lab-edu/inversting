package com.flab.investing.stock.integration.application;

import com.flab.investing.BatchTestSupport;
import com.flab.investing.stock.batch.application.KisService;
import com.flab.investing.stock.batch.domain.Stock;
import com.flab.investing.stock.batch.domain.StockPrice;
import com.flab.investing.stock.batch.domain.Token;
import com.flab.investing.stock.batch.infrastructure.kis.KisTokenRepository;
import com.flab.investing.stock.batch.infrastructure.kis.dto.KisPriceDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class KisServiceTest extends BatchTestSupport {


    private final KisService kisService;

    @MockBean
    private KisTokenRepository kisTokenRepository;

    private KisPriceDetail kisPriceDetail;
    private Token mockToken;

    public KisServiceTest(final KisService kisService) {
        this.kisService = kisService;
    }

    @BeforeEach
    void setUp() {
        kisPriceDetail = new KisPriceDetail("55",
                "40.00",
                "KOSPI200",
                "운수.창고",
                "N",
                "N",
                "N",
                "Y",
                "40",
                "N",
                "76100",
                "700",
                "2",
                "0.93",
                "3188276500",
                "42010",
                "48.22",
                "75100",
                "76400",
                "75000",
                "98000",
                "52800",
                "75400",
                "75893.06",
                "14.56",
                "2130",
                "1523",
                "78333",
                "76866",
                "74733",
                "73266",
                "71133",
                "77600",
                "74000",
                "1141",
                "22600",
                "5000",
                "57300",
                "100",
                "1",
                "22812344",
                "17360",
                "9.56",
                "0.45",
                "12",
                "0.18",
                "7959.00",
                "168852.00",
                "97500",
                "20221229",
                "-21.95",
                "69000",
                "20230706",
                "10.29",
                "95100",
                "-19.98",
                "20230102",
                "69000",
                "10.29",
                "20230706",
                "97500",
                "-21.95",
                "20221229",
                "69000",
                "10.29",
                "20230706",
                "0.23",
                "Y",
                "000120",
                "5,000",
                "1,140 억",
                "3321616",
                "N",
                "N",
                "3582",
                "N",
                "00",
                "N",
                "N");
        mockToken = new Token("test Token");
    }

    @Test
    @DisplayName("토큰을 가져옵니다.")
    void token() {
        given(kisTokenRepository.findToken()).willReturn(mockToken);

        Token token = kisService.getToken();

        assertThat(token).isEqualTo(mockToken);
    }

    @Test
    @DisplayName("주식 실시간 가격 가져온다.")
    void getPrice() {
        given(kisTokenRepository.findPrice(any(), any())).willReturn(kisPriceDetail);

        StockPrice price = kisService.getPrice(mockToken, new Stock(0L, "", "A000120", "", "", "", BigDecimal.ZERO));

        assertThat(price).isEqualTo(new StockPrice(
                0L,
                "A000120",
                "76100",
                "55",
                "2",
                "3188276500",
                "42010",
                "76400",
                "75000",
                "98000",
                "52800"
        ));
    }
}
