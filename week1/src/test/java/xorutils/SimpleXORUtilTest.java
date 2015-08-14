package xorutils;

import org.apache.commons.codec.DecoderException;
import org.junit.Test;

import static org.junit.Assert.*;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;

/**
 * Created by Dasha on 14.08.15.
 */
public class SimpleXORUtilTest {
    @Test
    public void testConversionToBytes() {
        String input = "abcdefghijklmnopqrstuvwxyz ";
        byte[] expectedOutput = {97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 32};
        byte[] actualOutput = SimpleXORUtil.convertToHex(input);
        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testConversionToString() {
        byte[] input = {97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 32};
        String expectedOutput = "abcdefghijklmnopqrstuvwxyz ";
        String actualOutput = SimpleXORUtil.convertToString(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testXorOperation() throws DecoderException {
        byte[] firstArray = {1, 2, 3};
        byte[] secondArray = {4, 5, 6};
        byte[] expectedResult = {5, 7, 5};
        byte[] actualResult = SimpleXORUtil.xor(firstArray, secondArray);
        System.out.println(Hex.encodeHexString(actualResult));
        byte[] result = Hex.decodeHex("e16475".toCharArray());
        System.out.println(new String(result));
        assertArrayEquals(expectedResult, actualResult);
    }
}
