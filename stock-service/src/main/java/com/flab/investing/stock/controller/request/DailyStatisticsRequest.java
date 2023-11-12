package com.flab.investing.stock.controller.request;

import jakarta.validation.constraints.NotBlank;

public record DailyStatisticsRequest(
        @NotBlank(message = "빈 값은 허용하지 않습니다.")
        String searchDate,

        @NotBlank(message = "빈 값은 허용하지 않습니다.")
        Long stockId
) { }