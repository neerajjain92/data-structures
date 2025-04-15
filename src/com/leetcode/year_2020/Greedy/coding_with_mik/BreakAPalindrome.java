package com.leetcode.year_2020.Greedy.coding_with_mik;

import com.competitive.coding.amazon.Source;
import com.util.LogUtil;

import static java.lang.String.format;

/**
 * https://leetcode.com/problems/break-a-palindrome/
 * 1328. Break a Palindrome
 */
public class BreakAPalindrome {

    /**
     * Question wants us to break a palindrome but make it lexicographically smallest
     * otherwise we could always have changed the first letter if it's of length > 1
     * So in this case what is the lexicographically smallest alphabet i.e 'a'
     * So replace the first non 'A' character with 'a', why not with 'a', dude replacing with same wtf ??
     * <p>
     * Got it but what about [a a a a] in this case everything is 'a' so we jumpt to 'b' but which 'b'
     * it can't be [b a a a] remember lexicographically, [a a a b] is smaller than [b a a a]
     * So we replace the last if we couldn't find any non 'a'
     * <p>
     * Now just last thing, will you run this loop till the end ???? No why ??
     * Because it's bloody palindrome the left is mirror of the right so we just run on the left half
     * this works great for even string
     * <p>
     * [ a  b  c  c  b  a] ===> Even length
     * || ||
     * || ||
     * i ||                 (Step 1)
     * i               (Step 2), we can replace this [a a c c b a]
     * <p>
     * What about odd
     * [a a c a a] ==> can you replace first non 'a' with 'a' ? No because [a a a a a]
     * In odd case the middle element is single it doesn't matter to him whatever you chaneg it to
     * [a a z a a] is still a palindrome and [a a b a a] too.
     * <p>
     * So len(str) == 5, So we should not run till exact half but one less
     * So [a a c a a] ==>> will be [a a c a b] ==> because we couldn't find any non 'a'
     */
    public static String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        char[] chars = palindrome.toCharArray();
        for (int i = 0; i < n / 2; i++) {
            if (chars[i] != 'a') {
                chars[i] = 'a';
                return String.valueOf(chars);
            }
        }

        // you reached here so that's basically just 2 things, either String was of length just 1
        // or the entire string is just filled with 'a' or an odd string like this a a b a a
        // So we just replace the last with 'b'
        chars[n - 1] = 'b';
        return n < 2 ? "" : String.valueOf(chars);
    }

    public static void main(String[] args) {
        LogUtil.logIt(format("lexicographically smallest post breaking palindrome of [%s] is [%s]",
                "abccba", breakPalindrome("abccba")));
        LogUtil.logIt(format("lexicographically smallest post breaking palindrome of [%s] is [%s]",
                "a", breakPalindrome("a")));
        LogUtil.logIt(format("lexicographically smallest post breaking palindrome of [%s] is [%s]",
                "aabaa", breakPalindrome("aabaa")));
        LogUtil.logIt(format("lexicographically smallest post breaking palindrome of [%s] is [%s]",
                "aaaa", breakPalindrome("aaaa")));
    }
}
