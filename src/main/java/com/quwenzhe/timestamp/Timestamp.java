package com.quwenzhe.timestamp;

/**
 * @Description monotonically increasing timestamp
 * @Author quwenzhe
 * @Date 2020/8/17 9:45 AM
 */
public class Timestamp {

    private final static Timestamp instance = new Timestamp();

    private int count = 1;

    private long lastTimestamp = 0;

    /**
     * Returns NOT an accurate representation of the current time.
     * Since java only measures time as ms,if you call 'System.currentTimeMillis()' twice quickly,
     * it's possible to get two identical time stamps.
     * This function guarantees UNIQUE but maybe INACCURATE result on each call.
     */
    public long timestamp() {
        // one milli second produce over 1_048_576 will occur error
        long time = System.currentTimeMillis() << 20;

        long adjustTimestamp;
        if (lastTimestamp == time) {
            adjustTimestamp = time + (++count);
        }
        // if last time was different reset timer back to '1'
        else {
            count = 1;
            adjustTimestamp = time;
        }

        lastTimestamp = time;
        return adjustTimestamp;
    }

    public static long unique() {
        return instance.timestamp();
    }
}
