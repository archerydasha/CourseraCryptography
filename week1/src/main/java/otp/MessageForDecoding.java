package otp;

import com.google.common.collect.Sets;
import xorutils.SimpleXORUtil;

import java.util.*;

/**
 * Created by Dasha on 22.08.15.
 */
public class MessageForDecoding {
    private String ciphertext;
    private List<Set<Byte>> possibleLetterValues;
    private boolean fullyDecoded;
    private String key;

    public MessageForDecoding(String encodedMessage) {
        this.ciphertext = encodedMessage;
        int initialCapacity = this.ciphertext.length() / 2;
        possibleLetterValues = new ArrayList<Set<Byte>>(initialCapacity);
        for (int i = 0; i < initialCapacity; i++) {
            Set<Byte> initialSet = new HashSet<Byte>();
            for (byte b = SimpleBreakingUtil.STARTING_BYTE; b <= SimpleBreakingUtil.ENDING_BYTE; b++) {
                initialSet.add(b);
            }
            possibleLetterValues.add(initialSet);
        }
    }

    public void filtedPossibleValuesForLetter(Set<Byte> values, int i) {
        Set<Byte> currentValue = possibleLetterValues.get(i);
        Set<Byte> newValue = Sets.intersection(currentValue, values);
        possibleLetterValues.set(i, newValue);
    }

    public void filtedPossibleValuesForLetter(Byte value, int i) {
        Set<Byte> otherValue = new HashSet<Byte>();
        otherValue.add(value);
        filtedPossibleValuesForLetter(otherValue, i);
    }


    public String getCiphertext() {
        return ciphertext;
    }

    public boolean isFullyDecoded() {
        boolean fullyDecoded = true;
        for (Set<Byte> letterValue : possibleLetterValues) {
            if (letterValue.size() > 1) {
                fullyDecoded = false;
                break;
            }
        }
        return fullyDecoded;
    }

    public String getKey() {
        if (!isFullyDecoded()) return null;
        byte[] bytes = new byte[possibleLetterValues.size()];
        for (int i = 0; i < possibleLetterValues.size(); i++) {
            for (Byte b : possibleLetterValues.get(i)) {
                bytes[i] = b;
            }
        }
        return new String(bytes);
    }

    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if(!(other instanceof MessageForDecoding)) return false;
        MessageForDecoding otherMessage = (MessageForDecoding) other;
        return Objects.equals(otherMessage.getCiphertext(), this.ciphertext);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(ciphertext);
    }
}