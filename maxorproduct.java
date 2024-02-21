import java.math.BigInteger;
class Solution {
    public int maximumXorProduct(long a, long b, int n) {
        BigInteger cand = BigInteger.ZERO;
        BigInteger A = BigInteger.valueOf(a);
        BigInteger B = BigInteger.valueOf(b);
        BigInteger ans = A.multiply(B);
        for(int i=n-1; i>=0; i--) {
            BigInteger temp = cand.setBit(i);
            BigInteger tempAns = A.xor(temp).multiply(B.xor(temp));
            if(tempAns.compareTo(ans) > 0) {
                ans = tempAns;
                cand = temp;
            }
        }
        return Integer.parseInt(ans.mod(BigInteger.valueOf(1000000007)).toString());
    }
}