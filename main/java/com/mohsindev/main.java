package com.mohsindev;

import static com.mohsindev.SubArraySum.maxSubArraySum;
import static com.mohsindev.SubArraySum.kadaneSum;

class Main {



    public static void main(String[] args) {

        int[] arr = {3,-4,5,4,-1,7,-8};

        SubArraySum.MaxSubArrayResult result = kadaneSum(arr);
        result.print();
        System.out.println("Max sum of subarray = " + result.max());
    }
}