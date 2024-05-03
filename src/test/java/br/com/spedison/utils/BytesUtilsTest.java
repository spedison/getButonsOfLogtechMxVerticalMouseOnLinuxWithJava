package br.com.spedison.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BytesUtilsTest {

    @Test
    void bytesToInt() {

        byte[] bytes = new byte[]{0x01, 0x0A, 0, 0, 1, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int valDir = BytesUtils.bytesToInt(bytes, 0,  false);
        int valRev = BytesUtils.bytesToInt(bytes, 1,  false);
        Assertions.assertEquals(0x010A0000, valDir);
        Assertions.assertEquals(0x0A000001, valRev);

    }

    @Test
    void bytesToShort() {
    }

    @Test
    void testBytesToInt() {
    }

    @Test
    void bytesToLong() {
    }

    @Test
    void bytesToBigInt() {
    }

    @Test
    void testBytesToBigInt() {
    }

    @Test
    void testBytesToLong() {
    }

    @Test
    void bytesToHex() {
    }

    @Test
    void testBytesToHex() {
    }

    @Test
    void reverseBytes() {
    }

    @Test
    void changeBytes() {
    }
}