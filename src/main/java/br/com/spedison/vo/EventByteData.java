package br.com.spedison.vo;

import br.com.spedison.utils.BytesUtils;

public class EventByteData {

    final static int START_TIME_SEC = 0;
    final static int TIME_LEN = 8;
    final static int START_TIME_MCS = TIME_LEN;
    final static int START_TYPE = START_TIME_MCS + TIME_LEN;

    final static int TYPE_LEN = 2;
    final static int START_CODE = START_TYPE + TYPE_LEN;

    final static int CODE_LEN = 2;
    final static int START_VALUE = START_CODE + CODE_LEN;

    final static int VALUE_LEN = 4;

    final BytesUtils bytesUtils = new BytesUtils();

    final byte[] arrayOfBytesEvent;

    double time;
    long timeSeconds;
    long timeMicroSeconds;
    int value;
    int type;
    int code;

    public EventByteData() {
        this.arrayOfBytesEvent = new byte[24];
    }

    public EventByteData(byte[] arrayOfBytesEvent) {
        if (arrayOfBytesEvent == null || arrayOfBytesEvent.length != 24) {
            throw new RuntimeException("Problemas ao ler o array de bytes");
        }
        this.arrayOfBytesEvent = arrayOfBytesEvent;
    }

    public byte[] getBuffer() {
        return this.arrayOfBytesEvent;
    }

    public void loadFromBytes(byte[] arrayOfBytesEvent) {

        if (arrayOfBytesEvent == null || arrayOfBytesEvent.length != 24) {
            throw new RuntimeException("Problemas ao ler o array de bytes");
        }
        
        System.arraycopy(arrayOfBytesEvent, 0, this.arrayOfBytesEvent, 0, 24);
        timeSeconds = bytesUtils.bytesToLong(arrayOfBytesEvent, START_TIME_SEC, true);
        timeMicroSeconds = bytesUtils.bytesToLong(arrayOfBytesEvent, START_TIME_MCS, true);
        time = getTimeSeconds() + (getTimeMicroSeconds() * 1E-6);
        code = bytesUtils.bytesToShort(arrayOfBytesEvent, START_CODE, true);
        type = bytesUtils.bytesToShort(arrayOfBytesEvent, START_TYPE, true);
        value = bytesUtils.bytesToInt(arrayOfBytesEvent, START_VALUE, true);
    }

    public Double getTime() {
        return time;
    }

    private Long getTimeSeconds() {
        return timeSeconds;
    }

    private Long getTimeMicroSeconds() {
        return timeMicroSeconds;
    }

    public int getCode() {
        return code;
    }

    public int getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}