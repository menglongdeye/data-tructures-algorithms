package com.lcl.homework;

public class StairsStairs {
    public int climbStairs(int n) {
        int s1 = 1, s2 = 2, ans=3;
        if(n<3){
            return n;
        }
        for(int i=3;i<=n;i++){
            ans = s1+s2;
            s1 = s2;
            s2 = ans;
        }
        return ans;
    }
}
