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
        //we are finding minimum length of message
        int messageSize = 5000;
        //ToDo - clean up dirty code here
        for (MessageForDecoding messageForDecoding : encodedMessages) {
            if (messageForDecoding.getCiphertext().length() / 2 < messageSize) {
                messageSize = messageForDecoding.getCiphertext().length() / 2;
            }
        }
        for (int letterNumber = 0; letterNumber < messageSize; letterNumber++) {
            //iterate through all possible letter values for 1st message

            for (byte candidate = SimpleBreakingUtil.STARTING_BYTE; candidate < SimpleBreakingUtil.ENDING_BYTE; candidate++) {
                byte[] letterArray = new byte[encodedMessages.size()];
                letterArray[0] = candidate;
                byte valueForFirstXor = (byte) (encodedMessages.get(1).getCiphertextBytes()[letterNumber] ^ encodedMessages.get(0).getCiphertextBytes()[letterNumber]);
                letterArray[1] = (byte) (candidate ^ valueForFirstXor);
                int messageNumber = 2;
                for (; messageNumber < encodedMessages.size(); messageNumber++) {
                    byte[] values = new byte[messageNumber];
                    int knownValueIndex = 0;
                    for (knownValueIndex = 0; knownValueIndex < messageNumber; knownValueIndex++) {
                        byte valueForXor = (byte) (encodedMessages.get(knownValueIndex).getCiphertextBytes()[letterNumber] ^ encodedMessages.get(messageNumber).getCiphertextBytes()[letterNumber]);
                        values[knownValueIndex] = (byte) (letterArray[knownValueIndex] ^ valueForXor);
                        if (knownValueIndex > 0 && values[knownValueIndex] != values[knownValueIndex - 1]) {
                            break;
                        }
                    }
                    if (knownValueIndex == messageNumber) {
                        letterArray[messageNumber] = values[0];
                    } else {
                        break;
                    }
                }
                if (messageNumber == encodedMessages.size()) {
                    for (int i = 0; i < letterArray.length; i++) {
                        encodedMessages.get(i).getDecodedLetters()[letterNumber] = letterArray[i];
                    }
                }
            }
            //let's assume that we managed to decode all letters here
            //ToDO - add check for this

        }
        return encodedMessages.get(0).getKey();
    }

    public String decodeLastMessage(List<String> messages, String messageToDecode) throws DecoderException {
        String key = findKey(messages);
        String decodedMessage = SimpleXORUtil.xor(key, messageToDecode);
        return decodedMessage;
    }
}
