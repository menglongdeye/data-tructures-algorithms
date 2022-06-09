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

}
