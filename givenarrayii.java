class Solution {
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        /** Time Complexity: O(nlog(n))*/
        /** Space Complexity: O(n) */
        // robin karp
        List<Integer> indexA = strStr(s, a);
        List<Integer> indexB =  strStr(s, b);
        List<Integer> ans = new ArrayList<>();
        if (indexB.size() == 0) return ans;
        // the lists indexA and indexB are sorted 
        for (int index : indexA) {
            int left = 0, right = indexB.size()-1;
            int n = indexB.size();
            // find the peak element
            while (left <= right) {
                int mid = left + (right - left ) / 2;
                if (mid + 1 < n && Math.abs(index - indexB.get(mid)) > Math.abs(index - indexB.get(mid+1))) {
                    left = mid + 1;
                } else{
                    right = mid - 1;
                }
            }
            if (Math.abs(indexB.get(left) - index) <= k) {
                ans.add(index);
            } 
        }
        return ans;
    }
    // robin karp: using rolling hash
    public List<Integer> strStr(String s, String a) {
        if (a.length() > s.length()) return new ArrayList<>(); 
        List<Integer> ans = new ArrayList<>();
        long target = 0;
        for (int i = 0; i<a.length(); i++) {
            target = (target * 26 + a.charAt(i) - 'a');
        }
        long cur = 0;
        long aL = 1;
        int L = a.length();
        for (int i = 1; i <= L; ++i) aL = (aL * 26) ;
        for (int i = 0; i<L; i++) {
            cur = (cur * 26 + s.charAt(i) - 'a');
        }
        if (cur == target) ans.add(0);
        for (int i = L; i<s.length(); i++) {
                cur = (cur * 26 - aL * (s.charAt(i-L) - 'a'));
                cur = (cur + s.charAt(i) - 'a');
                if (cur == target) {
                    ans.add(i - (a.length() - 1));
                } 
        }
        return ans;
    }
}