package otp;

import java.util.Set;

/**
 * Created by Dasha on 22.08.15.
 */
public class TwoPossibleValuesSet {

        private Set<Byte> possibleValuesSet1;
        private Set<Byte> possibleValuesSet2;

        public TwoPossibleValuesSet(Set<Byte> possibleValuesSet1, Set<Byte> possibleValuesSet2) {
            this.possibleValuesSet1 = possibleValuesSet1;
            this.possibleValuesSet2 = possibleValuesSet2;
        }

        public Set<Byte> getPossibleValuesSet1() {
            return possibleValuesSet1;
        }

        public Set<Byte> getPossibleValuesSet2() {
            return possibleValuesSet2;
     }
}
