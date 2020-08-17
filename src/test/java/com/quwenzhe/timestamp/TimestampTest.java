package com.quwenzhe.timestamp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Description 时间戳单元测试
 * @Author quwenzhe
 * @Date 2020/8/17 10:38 AM
 */
@Slf4j
public class TimestampTest {

    @Test
    public void timestampTest() {
        int length = 100_000_000;
        long prev = 0;

        while (length-- > 0) {
            long time = Timestamp.unique();
            if (prev >= time) {
                log.error("collision timestamp,prev:{},time:{}", prev, time);
            }
            Assert.assertTrue(prev < time);
            prev = time;
        }
    }
}
