package com.flab.investing;

import com.flab.investing.stock.global.config.MongoDbConfig;
import com.flab.investing.stock.global.config.MybatisConfig;
import com.flab.investing.stock.global.config.OpenFeignConfig;
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
        MybatisConfig.class,
        MongoDbConfig.class
})
public abstract class BatchTestSupport {
}
