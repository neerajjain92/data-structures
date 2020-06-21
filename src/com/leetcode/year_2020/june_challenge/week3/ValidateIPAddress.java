package com.leetcode.year_2020.june_challenge.week3;

/**
 * @author neeraj on 17/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ValidateIPAddress {

    public static void main(String[] args) {
        System.out.println(validIPAddress("0.0.0.-0"));
        System.out.println(validIPAddress("172.16.254.1"));
        System.out.println(validIPAddress("2001:0db8:85a3::8A2E:0370:7334"));
        System.out.println(validIPAddress("172.16.254.01"));
        System.out.println(validIPAddress("2001:0db8:85a3:0000:0000:8a2e:0370:7334"));
        System.out.println(validIPAddress("2001:0db8:85a3::8A2E:0370:7334"));
        System.out.println(validIPAddress("02001:0db8:85a3:0000:0000:8a2e:0370:7334"));
    }

    public static String validIPAddress(String IP) {
        String IPV6 = "IPv6";
        String IPV4 = "IPv4";
        String NEITHER = "Neither";
        int count = 0;
        if (IP.indexOf(":") == -1) { // Check only for IPV4
            String parts[] = IP.split("\\.");
            for (String part : parts) {
                ++count;
                if (count > 4 || part.isEmpty() ||
                        (part.length() > 1 && part.charAt(0) == '0') || part.length() > 3) {
                    return NEITHER;
                }

                // Now simply check each part of string
                for(char c: part.toCharArray()) {
                    if(c < '0' || c > '9') return NEITHER;
                }
                int val = Integer.parseInt(part);
                if (val < 0 || val > 255) return NEITHER;
            }
            return count == 4 && IP.charAt(IP.length() - 1) != '.' ? IPV4 : NEITHER;
        } else { // Check only for IPV6
            String parts[] = IP.split(":");
            for (String part : parts) {
                count++;
                if (count > 8 || part.isEmpty() || part.length() > 4) {
                    return NEITHER;
                }

                for (char c : part.toCharArray()) {
                    if (!(c >= '0' && c <= '9') && !(c >= 'a' && c <= 'f') && !(c >= 'A' && c <= 'F')) { // Negation check
                        // Hexa-Decimal and Digits check
                        return NEITHER;
                    }
                }
            }
            return count == 8 && IP.charAt(IP.length() - 1) != ':' ? IPV6 : NEITHER;
        }
    }
}
