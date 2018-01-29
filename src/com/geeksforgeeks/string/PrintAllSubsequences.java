package com.geeksforgeeks.string;

public class PrintAllSubsequences {

    public static void main(String[] args) {
        combinations("","ABC");
    }

    public static void combinations(String suffix, String prefix) {
        if(prefix.length() <0) {
            return;
        }
        System.out.println(suffix);
        for(int i=0;i<prefix.length();i++) {
            combinations(suffix + prefix.charAt(i), prefix.substring(i+1, prefix.length()));
        }
    }
}
