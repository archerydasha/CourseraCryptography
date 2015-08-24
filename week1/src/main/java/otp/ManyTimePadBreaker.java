package otp;

import org.apache.commons.codec.DecoderException;
import xorutils.SimpleXORUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dasha on 22.08.15.
 */
public class ManyTimePadBreaker {
    private SimpleBreakingUtil breakingUtil = new SimpleBreakingUtil();

    public String findKey(List<String> messages) throws DecoderException {
        List<MessageForDecoding> encodedMessages = new ArrayList<MessageForDecoding>(messages.size());
        for (String message : messages) {
            encodedMessages.add(new MessageForDecoding(message));
        }
        filterMessages(encodedMessages, 0, 0, 0, 0);

        for (MessageForDecoding messageForDecoding : encodedMessages) {
            if (messageForDecoding.isFullyDecoded()) {
                String key = messageForDecoding.getKey();
                return key;
            }
        }
        return null;
    }

    private void filterMessages(List<MessageForDecoding> encodedMessages, int firstIndex, int secondIndex, int letterNumber, int optionNumber) throws DecoderException {
        for (int i = firstIndex; i < encodedMessages.size(); i++) {
            for (int j = secondIndex; j < encodedMessages.size(); j++) {
                MessageForDecoding message1 = encodedMessages.get(i);
                MessageForDecoding message2 = encodedMessages.get(j);
                if (!message1.equals(message2)) {
                    byte[] bytes1 = SimpleXORUtil.getBytesFromHexString(message1.getCiphertext());
                    byte[] bytes2 = SimpleXORUtil.getBytesFromHexString(message2.getCiphertext());
                    int length = Math.min(bytes1.length, bytes2.length);
                    for (int l = 0; l < length; l++) {
                        byte xor = (byte) (bytes1[i] ^ bytes2[i]);
                        Byte[][] possibleValues = breakingUtil.findBytesThatCouldBeXoredTo(xor);
                        Byte[] possibleValues1 = possibleValues[0];
                        Byte[] possibleValues2 = possibleValues[1];
                        for (int n = 0; n < possibleValues1.length; n++) {
                            message1.filtedPossibleValuesForLetter(possibleValues1[n], l);
                            message2.filtedPossibleValuesForLetter(possibleValues2[n], l);
                            filterMessages(encodedMessages, i, j+1, l+1, n);
                        }
                    }
                }
            }
        }
    }

    public String decodeLastMessage(List<String> messages, String messageToDecode) throws DecoderException {
        String key = findKey(messages);
        String decodedMessage = SimpleXORUtil.xor(key, messageToDecode);
        return decodedMessage;
    }
}
