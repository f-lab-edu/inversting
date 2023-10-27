package com.flab.investing.stock.batch.infrastructure.kis;

import com.flab.investing.stock.batch.infrastructure.kis.dto.KisPriceHeaderRequest;
import com.flab.investing.stock.batch.infrastructure.kis.dto.KisPriceResponse;
import com.flab.investing.stock.batch.infrastructure.kis.dto.KisTokenRequest;
import com.flab.investing.stock.batch.infrastructure.kis.dto.KisTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "kisToken", url= "${kis.url}")
public interface KisTokenClient {

    @PostMapping("/oauth2/tokenP")
    KisTokenResponse getToken(@RequestBody KisTokenRequest request);

    @ResponseBody
    @GetMapping(value = "/uapi/domestic-stock/v1/quotations/inquire-price")
    KisPriceResponse getPrice(@RequestHeader("appsecret") String appsecret,
                              @RequestHeader("appkey") String appkey,
                              @RequestHeader("authorization") String authorization,
                              @RequestHeader("tr_id") String trId,
                              @RequestParam("FID_COND_MRKT_DIV_CODE") String marketCode,
                              @RequestParam("FID_INPUT_ISCD") String code);

}
