class Solution {
    boolean isPalindromic(long x){
        String str=Long.toString(x);
        int p1=0,p2=str.length()-1;
        while(p1<p2){
            if(str.charAt(p1)!=str.charAt(p2)){
                  return false;
            }
            p1++;
            p2--;
        }
        return true;
    }
    
    public long minimumCost(int[] nums) {
        int i,n=nums.length;
        long median;
        Arrays.sort(nums);
        
        if(n%2!=0){
            median=1L*nums[n/2];
        }
        else{
            median=((nums[n/2]+nums[(n/2 -1)])/2)*1L;
        }
        
        long cost1=0,cost2=0;
        long p1=median,p2=median;
    
        while(!isPalindromic(p1)){
            p1--;
        }
        
        while(!isPalindromic(p2)){
            p2++;
        }
        
        //System.out.println(median+" "+p1+" "+p2);
        
        for(i=0;i<n;i++){
            cost1+=1L*Math.abs(nums[i]-p1);
            cost2+=1L*Math.abs(nums[i]-p2);
        }
        return Math.min(cost1,cost2);
    }
}