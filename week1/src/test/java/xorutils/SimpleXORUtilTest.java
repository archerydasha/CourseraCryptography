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
        String input = "abcde";
        String expectedOutput = "6162636465";
        String actualOutput = SimpleXORUtil.convertToHex(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testConversionToString() throws DecoderException {
        String input =  "6162636465";
        String expectedOutput = "abcde";
        String actualOutput = SimpleXORUtil.convertToString(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testXorOperation() throws DecoderException {
        byte[] firstArray = {1, 2, 3};
        byte[] secondArray = {4, 5, 6};
        byte[] expectedResult = {5, 7, 5};
        byte[] actualResult = SimpleXORUtil.xor(firstArray, secondArray);
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    public void testStringXorOperation() throws DecoderException {
        String firstHexString = "010203";
        String secondHexString = "040506";
        String expectedResult = "050705";
        String actualResult = SimpleXORUtil.xor(firstHexString, secondHexString);
        assertEquals(expectedResult, actualResult);
    }
}
