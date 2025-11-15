package ms.thakur.lodhi.utility;


public class SnowflakeIdGenerator {

    private final long nodeId;
    private final long epoch = 1672531200000L; // Custom epoch (e.g., Jan 1, 2023)

    private long sequence = 0L;
    private long lastTimestamp = -1L;

    private final static long NODE_ID_BITS = 10L;
    private final static long MAX_NODE_ID = (1L << NODE_ID_BITS) - 1;

    private final static long SEQUENCE_BITS = 12L;
    private final static long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;

    private final static long NODE_ID_SHIFT = SEQUENCE_BITS;
    private final static long TIMESTAMP_SHIFT = SEQUENCE_BITS + NODE_ID_BITS;

    public SnowflakeIdGenerator(long nodeId) {
        if (nodeId < 0 || nodeId > MAX_NODE_ID) {
            throw new IllegalArgumentException(String.format("NodeId must be between 0 and %d", MAX_NODE_ID));
        }
        this.nodeId = nodeId;
    }

    public synchronized long nextId() {
        long currentTimestamp = System.currentTimeMillis();

        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards. Refusing to generate id");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                // Sequence overflow, wait till next millisecond
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        return ((currentTimestamp - epoch) << TIMESTAMP_SHIFT) | (nodeId << NODE_ID_SHIFT) | sequence;
    }

    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp <= lastTimestamp) {
            currentTimestamp = System.currentTimeMillis();
        }
        return currentTimestamp;
    }
}
