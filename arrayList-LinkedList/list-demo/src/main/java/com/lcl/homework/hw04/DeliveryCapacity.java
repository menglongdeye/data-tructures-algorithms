package com.lcl.homework.hw04;

/**
 * 在 D 天内送达包裹的能力
 */
public class DeliveryCapacity {
    public int shipWithinDays(int[] weights, int days) {
        int l = 0, r = 0;
        for(int weight : weights){
            l = Math.max(l, weight);
            r += weight;
        }
        while(l<r){
            int mid = (l+r)/2;
            if(validate(weights, days, mid)){
                r = mid;
            }else{
                l = mid+1;
            }
        }
        return r;
    }

    private boolean validate(int[] weights, int days, int testWeight){
        int countDay = 1;
        int oneDayWeught = 0;
        for(int weight : weights){
            if(oneDayWeught + weight <= testWeight){
                oneDayWeught += weight;
            }else{
                countDay++;
                oneDayWeught = weight;
            }
        }
        return countDay <= days;
    }
}
