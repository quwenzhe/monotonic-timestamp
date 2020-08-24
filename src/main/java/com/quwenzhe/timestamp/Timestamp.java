package com.quwenzhe.timestamp;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static com.quwenzhe.timestamp.Constant.DEFAULT_PRECISION;

/**
 * @Description monotonically increasing timestamp
 * @Author quwenzhe
 * @Date 2020/8/17 9:45 AM
 */
public class Timestamp {

    private final static Timestamp instance = new Timestamp();

    private long lastTimestamp = 0;

    private int count = 1;

    private double lastAdjustTimestamp = 0;

    private DecimalFormat decimalFormat = new DecimalFormat(DEFAULT_PRECISION);

    /**
     * Returns NOT an accurate representation of the current time.
     * Since java only measures time as ms,if you call 'System.currentTimeMillis()' twice quickly,
     * it's possible to get two identical time stamps.
     * This function guarantees UNIQUE but maybe INACCURATE result on each call.
     */
    public double timestamp() {
        long time = System.currentTimeMillis();

        double adjustTimestamp;
        if (lastTimestamp == time) {
            do {
                adjustTimestamp = new BigDecimal(time).add(new BigDecimal(decimalFormat.format((float) (count++) / (count + 999)))).doubleValue();
            } while (DoubleComparer.considerEqual(adjustTimestamp, lastAdjustTimestamp));
            lastAdjustTimestamp = adjustTimestamp;
        }
        // if last time was different reset timer back to '1'
        else {
            count = 1;
            adjustTimestamp = time;
        }

        lastAdjustTimestamp = adjustTimestamp;
        lastTimestamp = time;
        return adjustTimestamp;
    }

    public static double unique() {
        return instance.timestamp();
    }
}
