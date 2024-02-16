class Solution {
    public boolean canSortArray(int[] nums) {
        if(nums.length==1)
        {
            return true;
        }
        int ind=0;
        while(ind<nums.length)
        {
            if(ind < nums.length-1 && nums[ind]>nums[ind+1])
            {
                boolean val=check(nums[ind],nums[ind+1]);
                if(val)
                {
                    int dup=nums[ind];
                    nums[ind]=nums[ind+1];
                    nums[ind+1]=dup;
                }
                else
                {
                    return false;
                }
                val=check1(nums);
                if(val)
                {
                    return true;
                }
                ind=0;
            }
            else
            {
                ind++;
            }
        }
        return check1(nums);
    }
    private boolean check(int a, int b)
    {
        int count=0,count1=0;
        while(a>0)
        {
            if((a&1)==1)
            {
                count++;
            }
            a>>=1;
        }
        while(b>0)
        {
            if((b&1)==1)
            {
                count1++;
            }
            b>>=1;
        }
        return count==count1;
    }
    private boolean check1(int nums[])
    {
        for(int i=0;i<nums.length-1;i++)
        {
            if(nums[i]>nums[i+1])
            {
                return false;
            }
        }
        return true;
    }
}