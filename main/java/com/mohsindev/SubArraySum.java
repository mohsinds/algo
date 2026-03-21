package com.mohsindev;

public class SubArraySum {
    public record MaxSubArrayResult (int max, int start, int end){
        public void print(){
            System.out.println("max = " + max + ", start = " + start + ", end = " + end);
        }
    }

    /**
     * Brute force method to find a max sum of subarray
     * @param arr
     * @return MaxSubArrayResult
     */
    public static MaxSubArrayResult maxSubArraySum(int[] arr){
        int max = 0;

        for(int start = 0; start < arr.length; start++) {
            int currentMax = 0;
            for (int end = start; end < arr.length; end++) {
                currentMax += arr[end];
                max = Math.max(currentMax, max);
            }
        }
        return new MaxSubArrayResult(max,-1,-1);
//        return max;
    }

    public static int kadaneSum(int[] arr) {
        int max = 0;

        for(int start = 0; start < arr.length; start++) {
            int currentMax = 0;
            for (int end = start; end < arr.length; end++) {
                currentMax += arr[end];
                max = Math.max(currentMax, max);
            }
        }
        return max;
    }
}
