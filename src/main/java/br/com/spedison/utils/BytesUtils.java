package br.com.spedison.utils;

import br.com.spedison.exceptions.OutOfRangeArrayReadException;
import org.apache.commons.lang3.ArrayUtils;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.StringJoiner;

public class BytesUtils {

    public BytesUtils() {
    }

    private ByteBuffer getByteBuffer(byte[] bytes, int start, int len, boolean rev) {

        byte[] localBuffer = new byte[len];
        int end = start + localBuffer.length;

        if (end > bytes.length)
            throw new OutOfRangeArrayReadException("bytes", bytes.length, end);

        System.arraycopy(bytes, start, localBuffer, 0, len);

        if (rev)
            reverseBytes(localBuffer);

        return ByteBuffer.wrap(localBuffer, 0, len);
    }

    public int bytesToInt(byte[] bytes, boolean reverse) {
        return bytesToInt(bytes, 0, reverse);
    }

    public short bytesToShort(byte[] bytes, int start, boolean reverse) {
        return getByteBuffer(bytes, start, 2, reverse).getShort();
    }

    public int bytesToInt(byte[] bytes, int start, boolean reverse) {
        return getByteBuffer(bytes, start, 4, reverse).getInt();
    }

    public long bytesToLong(byte[] bytes, boolean reverse) {
        return bytesToLong(bytes, 0, reverse);
    }

    public BigInteger bytesToBigInt(byte[] bytes, boolean reverse) {
        return bytesToBigInt(bytes, 0, bytes.length, reverse);
    }

    public BigInteger bytesToBigInt(byte[] bytes, int start, int end, boolean reverse) {

        byte[] toCont;
        boolean backRev = true;

        if (start == 0 && (end - start) == bytes.length) {
            toCont = bytes;
            backRev = false;
        } else {
            toCont = new byte[end - start];
            System.arraycopy(bytes, start, toCont, 0, toCont.length);
        }

        if (reverse)
            reverseBytes(toCont);

        BigInteger ret = new BigInteger(toCont);

        if (reverse && backRev)
            reverseBytes(toCont);

        return ret;
    }

    public long bytesToLong(byte[] bytes, int start, boolean reverse) {
        return getByteBuffer(bytes, start, 8, reverse).getLong();
    }

    public String bytesToHex(byte[] bytes) {
        return bytesToHex(bytes, 0, bytes.length);
    }

    public String bytesToHex(byte[] bytes, int start, int end) {
        StringJoiner joiner = new StringJoiner("::");
        for (int i = start; (i < bytes.length && i < end); i++) {
            joiner.add(String.format("%02X", bytes[i]));
        }
        return joiner.toString();
    }

    public void reverseBytes(byte[] bytes) {
        ArrayUtils.reverse(bytes);
    }

    public void changeBytes(byte[] bytes, int pos1, int pos2) {
        byte tmp = bytes[pos1];
        bytes[pos1] = bytes[pos2];
        bytes[pos2] = tmp;
    }
}