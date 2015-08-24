package otp;

import com.google.common.collect.Sets;
import org.apache.commons.codec.DecoderException;
import sun.net.www.content.image.x_xpixmap;
import xorutils.SimpleXORUtil;

import java.util.*;

/**
 * Created by Dasha on 22.08.15.
 */
public class MessageForDecoding {
    private String ciphertext;
    private byte[] ciphertextBytes;
    private byte[] decodedLetters;

    public MessageForDecoding(String encodedMessage) throws DecoderException {
        this.ciphertext = encodedMessage;
        int initialCapacity = this.ciphertext.length() / 2;
        decodedLetters = new byte[initialCapacity];
        ciphertextBytes = SimpleXORUtil.getBytesFromHexString(ciphertext);
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public byte[] getCiphertextBytes() {
        return ciphertextBytes;
    }

    public byte[] getDecodedLetters() {
        return decodedLetters;
    }

    public String getMessage() {
        return new String(decodedLetters);
    }

    public String getKey() {
        byte[] keybytes = new byte[decodedLetters.length];
        for(int i = 0; i<decodedLetters.length; i++){
            keybytes[i] = (byte) (ciphertextBytes[i] ^ decodedLetters[i]);
        }
        return new String(keybytes);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof MessageForDecoding)) return false;
        MessageForDecoding otherMessage = (MessageForDecoding) other;
        return Objects.equals(otherMessage.getCiphertext(), this.ciphertext);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ciphertext);
    }
}