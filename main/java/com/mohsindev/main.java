package com.mohsindev;

import static com.mohsindev.SubArraySum.maxSubArraySum;

class Main {



    public static void main(String[] args) {

        int[] arr = {1,2,3,-5,4,2,3,-4};

        SubArraySum.MaxSubArrayResult result = maxSubArraySum(arr);
        result.print();
        System.out.println("Max sum of subarray = " + result.max());
    }
}c