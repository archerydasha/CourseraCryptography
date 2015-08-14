package xorutils;

/**
 * Created by Dasha on 14.08.15.
 */
public class SimpleXORUtil {
     public static byte[] convertToHex(String value){
         return value.getBytes();
     }

    public static String convertToString(byte[] bytes){
        return new String(bytes);
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


