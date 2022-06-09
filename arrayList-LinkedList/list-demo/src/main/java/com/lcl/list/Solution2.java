package com.lcl.list;

import java.util.*;

public class Solution2 {
    public static void main1(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.printf(""+solution2.trap(new int[]{4,2,0,3,2,5}));
    }

    public int trap(int[] height) {
        return method1(height);
        //return method2(height);
    }


    public int method1(int[] heights){
        Stack<Dec> decStack = new Stack();
        int sum = 0;
        for(int i=0;i<heights.length;i++){
            int height = heights[i];
            int sumWeith = 0;

            while(!decStack.isEmpty() && height >= decStack.peek().height){
                Dec tempDec = decStack.pop();
                if(decStack.isEmpty()){continue;}
                sum += (Math.min(decStack.peek().height, height) - tempDec.height) * tempDec.weith;
                sumWeith += tempDec.weith;
            }
            Dec newDec = new Dec(sumWeith+1, height);
            decStack.push(newDec);
        }
        return sum;
    }

    class Dec{
        public int height;
        public int weith;

        public Dec(int weith, int height){
            this.weith = weith;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        System.out.printf("" + solution2.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> indexDeque = new LinkedList<Integer>();
        int[] ans = new int[nums.length-k+1];

        for(int i=0;i<nums.length;i++){
            while(!indexDeque.isEmpty() && i-k >= indexDeque.peekFirst()){
                indexDeque.pollFirst();
            }
            // 删除
            while(!indexDeque.isEmpty() && nums[i] >= nums[indexDeque.peekLast()]){
                indexDeque.pollLast();
            }
            indexDeque.addLast(i);
            if(i >= k-1){
                ans[i-k+1] = nums[indexDeque.getFirst()];
            }
        }

        for(int i=0;i<ans.length;i++){
            System.out.println(""+ans[i]);
        }
        return ans;
    }
}
