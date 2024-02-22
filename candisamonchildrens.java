class Solution {
    public int distributeCandies(int n, int limit) {
        // call recursive function with parameters and return it's result
        return dynamicDestribution(n, 3, limit);
    }
    // declare recursive function that takes, candies, childrens and limit
    public int dynamicDestribution(int candies, int children, int limit) {
        // check for recursion base case and check if candies are 0 then return 1 else 0
        if(children == 0) return candies == 0 ? 1 : 0;
        // declare count
        int count = 0;
        // take range as minimum between candies and limit and increment by 1m you can skip incrementing and include right boundary of range in for loop
        int range = Math.min(candies, limit) + 1;
        // loop thruought range and on each range call recurive function with candies - current range and decrement children by 1, limit not changed
        for(int i = 0; i < range; i++) count += dynamicDestribution(candies - i, children - 1, limit);
        // return count
        return count;
    }
}