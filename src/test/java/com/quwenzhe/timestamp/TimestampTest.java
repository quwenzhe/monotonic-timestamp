package com.quwenzhe.timestamp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;

import static com.quwenzhe.timestamp.Constant.DEFAULT_PRECISION;

/**
 * @Description 时间戳单元测试
 * @Author quwenzhe
 * @Date 2020/8/17 10:38 AM
 */
@Slf4j
public class TimestampTest {

    @Test
    public void timestampTest() {
        int length = 1_000_000;
        double prev = 0;
        DecimalFormat decimalFormat = new DecimalFormat(DEFAULT_PRECISION);

        while (length-- > 0) {
            double time = Timestamp.unique();
            if (prev >= time) {
                log.error("collision timestamp,prev:{},time:{}", decimalFormat.format(prev), decimalFormat.format(time));
            }
            Assert.assertTrue(prev < time);
            prev = time;
        }
    }
}
