package xorutils;

import org.apache.commons.codec.DecoderException;

/**
 * Created by Dasha on 14.08.15.
 */
public class CuteLittleCipherBreaker {
    public static String findOTPCiphertext(String oldMessage, String oldCipher, String newMessage) throws DecoderException {
        String oldMessageInHex = SimpleXORUtil.convertToHex(oldMessage);
        String key = SimpleXORUtil.xor(oldMessageInHex, oldCipher);
        String newMessageInHex = SimpleXORUtil.convertToHex(newMessage);
        String newCipher = SimpleXORUtil.xor(key, newMessageInHex);
        return newCipher;
    }
}
