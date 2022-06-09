package com.lcl.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeWork02 {

    /**
     * 子域名访问计数
     * @param cpdomains
     * @return
     */
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String cpdomain : cpdomains){
            String[] cpdoainArr = cpdomain.split(" ");
            int count = Integer.parseInt(cpdoainArr[0]);
            String[] tempArr = cpdoainArr[1].split("\\.");
            String key = "";
            for(int i=tempArr.length-1;i>=0;i--){
                key = i == tempArr.length - 1 ? tempArr[i] : tempArr[i] + "." + key;
                if(!map.containsKey(key)){
                    map.put(key, count);
                }else{
                    map.put(key, map.get(key) + count);
                }
            }
        }
        List<String> ans = new ArrayList<String>();
        for(String key : map.keySet()){
            ans.add(map.get(key) + " " + key);
        }
        return ans;
    }


    /**
     * 数组的度
     * @param nums
     * @return
     */
    public int findShortestSubArray(int[] nums) {
        // key为数值，int[]数组长度为3，分别是数字个数、当前数值的第一个值的位置、最后一个值的位置
        Map<Integer, Integer[]> map = new HashMap();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                Integer[] arr = map.get(nums[i]);
                arr[0] = arr[0]+1;
                arr[2] = i;
                map.put(nums[i], arr);
            }else{
                Integer[] arr = new Integer[]{1, i, i};
                map.put(nums[i], arr);
            }
        }
        int ansCount = 0;
        int minLen = 0;
        for(Integer num : map.keySet()){
            Integer[] arr = map.get(num);
            if(arr[0] > ansCount){
                ansCount = arr[0];
                minLen = arr[2] - arr[1] + 1;
            }else if(arr[0] == ansCount && arr[2]-arr[1] < minLen){
                minLen = arr[2] - arr[1] + 1;
            }
        }
        return minLen;

    }

}
