class Solution { 
    public int minOrAfterOperations(int[] nums, int k) {
        int detPrefix = 0;
        int initStart = 0;
        for (int i = 30; i >= 0; --i) {
            initStart |= (1 << i);
            int cur = initStart;
            int mergeCnt = 0;
            for (int num : nums) {
                cur &= num;
                if ((cur | detPrefix) != detPrefix) {
                    ++mergeCnt;
                } else {
                    cur = initStart;
                }
            }
            if (mergeCnt > k) {
                detPrefix |= (1 << i);
            }
        }
        return detPrefix;
    }
}