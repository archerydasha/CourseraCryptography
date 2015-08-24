package otp;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dasha on 22.08.15.
 */
public class SimpleBreakingUtilTest {
    @Ignore
    @Test
    public void testXor(){
        byte xor = 0x41;
        Set<Byte> possibleValues = new SimpleBreakingUtil().findBytesThatCouldBeXoredTo(xor);
        Byte[] expectedResultArray = new Byte[]{0x20,0x61};
        Set<Byte> expectedResult = new HashSet<Byte>(Arrays.asList(expectedResultArray)) ;
        assert(expectedResult.size() == possibleValues.size() && expectedResult.containsAll(possibleValues));
    }
}
