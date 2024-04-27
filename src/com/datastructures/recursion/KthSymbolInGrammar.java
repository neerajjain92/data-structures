package com.datastructures.recursion;

/**
 * https://leetcode.com/problems/k-th-symbol-in-grammar/description/
 */
public class KthSymbolInGrammar {

    public static void main(String[] args) {
        System.out.println(kthGrammar(1, 1));
        System.out.println(kthGrammar(2, 1));
        System.out.println(kthGrammar(2, 2));
        System.out.println(kthGrammar(30, 434991989));
    }

    private static int kthGrammar(int n, int k) {
        if (n == 1 && k == 1) {
            return 0;
        }

        // Since nth Row is like this
        // 0
        // 0 MID 1
        // 01 MID 10
        // 0110 MID 1001

        // Check nth Row with the previous row
        // before mid it's exactly same as the previous row
        // after mid it's the complement of previous row 1==> 0 and 0 ==> 1
        int mid = ((int) (Math.pow(2, n - 1))) / 2;
        int answer;
        if (k <= mid) {
            return kthGrammar(n - 1, k);
        } else {
            answer = kthGrammar(n - 1, k - mid);
            return answer == 0 ? 1 : 0;
        }
    }
}
