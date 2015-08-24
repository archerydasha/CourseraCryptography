package xorutils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * Created by Dasha on 14.08.15.
 */
public class SimpleXORUtil {
     public static String convertToHex(String value){
         return Hex.encodeHexString(value.getBytes());
     }

    public static String convertToString(String hexValue) throws DecoderException {
        return new String(getBytesFromHexString(hexValue));
    }

    public static byte[] getBytesFromHexString(String hexValue) throws DecoderException {
        return Hex.decodeHex(hexValue.toCharArray());
    }

    public static String xor(String firstHexString, String secondHexString) throws DecoderException {
        byte[] firstArray = getBytesFromHexString(firstHexString);
        byte[] secondArray = getBytesFromHexString(secondHexString);
        return Hex.encodeHexString(xor(firstArray, secondArray));
    }

    public static byte[] xor(byte[] firstArray, byte secondArray[]){
        assert firstArray.length == secondArray.length;
        int size = firstArray.length;
        byte[] result = new byte[size];
        for(int i = 0; i< size; i++){
            result[i] = (byte) (firstArray[i] ^ secondArray[i]);
        }
        return result;
    }
}


