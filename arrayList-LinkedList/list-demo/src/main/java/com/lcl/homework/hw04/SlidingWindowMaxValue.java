package com.lcl.homework.hw04;

import java.util.PriorityQueue;

public class SlidingWindowMaxValue {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 0：下标 1：数值
        PriorityQueue<int[]> q = new PriorityQueue<int[]>(nums.length, (a, b) -> b[1]-a[1]);
        int[] ans = new int[nums.length-k+1];
        for(int i=0;i<nums.length;i++){
            q.add(new int[]{i, nums[i]});
            if(i >= k-1){
                while(q.peek()[0] < i-k+1){
                    q.poll();
                }
                ans[i-k+1] = q.peek()[1];
            }
        }
        return ans;
    }
}
