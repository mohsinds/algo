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
        int maxStart = 0, maxEnd = 0;
        for(int start = 0; start < arr.length; start++) {
            int currentMax = 0;
            for (int end = start; end < arr.length; end++) {
                currentMax += arr[end];
//                max = Math.max(currentMax, max);
                if(currentMax > max){
                    maxStart = start; maxEnd = end;
                    max = currentMax;
                }
            }
        }
        return new MaxSubArrayResult(max,maxStart, maxEnd);
//        return max;
    }

    /**
     * Kadane's Algorithm
     * @param arr
     * @return
     */
    public static MaxSubArrayResult kadaneSum(int[] arr) {

        int currentSum = 0;
        int maxSum = 0;
        int maxStart = 0, maxEnd = 0;
        for(int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
//            maxSum = Math.max(currentSum, maxSum);
            if(currentSum > maxSum) {
                maxSum = currentSum;
                maxEnd = i;
            }

            if(currentSum < 0) {
                currentSum = 0;
                maxStart = (i + 1) % arr.length;
            }

        }
        return new MaxSubArrayResult(maxSum,maxStart,maxEnd);
    }
}
