package xorutils;

import org.apache.commons.codec.DecoderException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Dasha on 16.08.15.
 */
public class CuteLittleCipherBreakerTest {
    @Test
    public void easyTest() throws DecoderException {
        String oldMessage = "abc";
        String oldCipher = "646566";
        String newMessage = "ghk";
        String expectedResult = "626f6e";
        String actualResult = CuteLittleCipherBreaker.findOTPCiphertext(oldMessage, oldCipher, newMessage);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void problemSet() throws DecoderException {
        String oldMessage = "attack at dawn";
        String oldCipher = "09e1c5f70a65ac519458e7e53f36";
        String newMessage = "attack at dusk";
        String expectedResult = "626f6e";
        String actualResult = CuteLittleCipherBreaker.findOTPCiphertext(oldMessage, oldCipher, newMessage);
        assertEquals(expectedResult, actualResult);
    }
}
