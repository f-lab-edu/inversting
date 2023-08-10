package com.flab.investing.stock.batch.common;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties("kis")
public class KisAccessProperties {

    private String url;
    private String appKey;
    private String secretKey;

}
