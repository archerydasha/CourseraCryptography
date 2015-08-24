package otp;

import java.util.*;

/**
 * Created by Dasha on 22.08.15.
 */
public class SimpleBreakingUtil {
    public static final byte STARTING_BYTE = 0x20;
    public static final int ENDING_BYTE = 0x7E;
    private Map<Byte, Set<Byte>> hashedValues = new HashMap<Byte, Set<Byte>>();

    public Byte[][] findBytesThatCouldBeXoredTo(byte xor) {
        List<Byte> values1 = new ArrayList<Byte>();
        List<Byte> values2 = new ArrayList<Byte>();
        if (hashedValues.get(xor) == null) {
            Set<Byte> values = new HashSet<Byte>();
            for (byte byte1 = STARTING_BYTE; byte1 < ENDING_BYTE; byte1++) {
                for (byte byte2 = STARTING_BYTE; byte2 < ENDING_BYTE; byte2++) {
                    if ((byte1 ^ byte2) == xor) {
                        values1.add(byte1);
                        values2.add(byte2);
                    }
                }
            }
        }
        Byte[][] result = new Byte[2][];
        result[1] = values1.toArray(new Byte[0]);
        result[2] = values2.toArray(new Byte[0]);
        return result;
    }
}
