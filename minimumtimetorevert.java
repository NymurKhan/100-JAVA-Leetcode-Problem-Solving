class Solution {
    public int minimumTimeToInitialState(String word, int k) {
        int count = 0;
        for(int i=k;i<word.length() && !word.startsWith(word.substring(i));i+=k,count++);
        return count+1; 
    }
}