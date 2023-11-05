package com.flab.investing;

import com.flab.investing.stock.global.config.MongoDbConfig;
import com.flab.investing.stock.global.config.MybatisConfig;
import com.flab.investing.stock.global.config.OpenFeignConfig;
import com.flab.investing.stock.global.config.SpringBatchConfig;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestConstructor;

@SpringBootTest
@SpringBatchTest
@EnableAutoConfiguration
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Import({
        OpenFeignConfig.class,
        SpringBatchConfig.class,
        MybatisConfig.class,
        SpringBatchConfig.class,
        MongoDbConfig.class
})
public abstract class BatchTestSupport {
}
