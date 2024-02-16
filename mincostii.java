class Solution {

    private class BIT {
        int n;
        long[] arr;

        public BIT(int n) {
            this.n = n;
            arr = new long[n];
        }

        public void update(int index, int val) {
            for(int i = index; i < n; i += (-i & i)) {
                arr[i] += val;
            }
        }

        public long query(int index) {
            long sum = 0;
            for(int i = index; i > 0; i -= (-i & i)) {
                sum += arr[i];
            }
            return sum;
        }

        // Get BIT index of the first K elements. Same as lower_bound on order_multiset or bisect_left on SortedList
        // This can only be used if BIT is updated with 1 on insert and -1 on delete.
        public int getKth(int k) {
            int i = 0;
            for (int p = Integer.highestOneBit(n); p > 0; p >>= 1) {
                if (i + p < n && arr[i + p] <= k) {
                    i += p;
                    k -= arr[i];
                }
            }
            return i;
        }
    }

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;

        // Generate mapping between input array and BIT indexes
        List<Pair<Integer, Integer>> elements = new ArrayList<>();
        for(int i = 1; i < n; i++) {
            elements.add(new Pair<>(nums[i], i));
        }
        Collections.sort(elements, (a, b) -> Integer.compare(a.getKey(), b.getKey()));
        Map<Pair<Integer, Integer>, Integer> bitIndexes = new HashMap<>();
        for(int i = 0; i < n - 1; i++) {
            bitIndexes.put(elements.get(i), i + 1);
        }

        // Window traversal
        BIT kSmallestBIT = new BIT(n);
        BIT prefixSumBIT = new BIT(n);
        long sum = (long) 1e15;
        k--;
        dist++;
        for(int i = 1; i < n; i++) { 
            int insertBitIndex = bitIndexes.get(new Pair<>(nums[i], i));
            prefixSumBIT.update(insertBitIndex, nums[i]);
            kSmallestBIT.update(insertBitIndex, 1);

            if(i >= k) {
                sum = Math.min(sum, prefixSumBIT.query(kSmallestBIT.getKth(k)));
            }

            if(i >= dist) {
                int removeBitIndex = bitIndexes.get(new Pair(nums[i - dist + 1], i - dist + 1));
                prefixSumBIT.update(removeBitIndex, -nums[i - dist + 1]);
                kSmallestBIT.update(removeBitIndex, -1);
            }
        }

        return sum + nums[0];
    }
}