package com.leetcode.year_2020.graph.disjoint_set_union_find;

import java.util.*;

/**
 * @author neeraj on 30/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("GrazieInspection")
public class AccountsMerge {

    public static void main(String[] args) {
        solveIt(new String[][]{
                {"John", "johnsmith@mail.com", "john00@mail.com"},
                {"John", "johnnybravo@mail.com"},
                {"John", "johnsmith@mail.com", "john_newyork@mail.com"},
                {"Mary", "mary@mail.com"}
        });
        solveIt(new String[][]{
                {"David", "0", "1"},
                {"David", "3", "4"},
                {"David", "4", "5"},
                {"David", "2", "3"},
                {"David", "1", "2"}
        });

        solveIt(new String[][]{
                {"John", "j1@com", "j2@com", "j3@com"},
                {"John", "j4@com"},
                {"Raj", "r1@com", "r2@com"},
                {"John", "j1@com", "j5@com"},
                {"Raj", "r2@com", "r3@com"},
                {"Marry", "m1@com"},
        });
    }

    public static void solveIt(String[][] accounts) {
        List<List<String>> accountsList = new ArrayList<>();
        for (String[] account : accounts) {
            accountsList.add(Arrays.asList(account));
        }
        System.out.println(accountsMergeUsingDFS(accountsList));
//        System.out.println(accountsMerge(accountsList));
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        final Map<String, String> primaryEmailMap = new HashMap<>(); // Also known as leader
        final Map<String, String> usernameMap = new HashMap<>();
        final Map<String, TreeSet<String>> mergedAccount = new HashMap<>();

        // Initially every email's primary email is themself.
        // and all emails in the row should point to their respective username[i.e 0th entry].
        for (List<String> account : accounts) {
            final String username = account.get(0);

            for (int i = 1; i < account.size(); i++) {
                primaryEmailMap.put(account.get(i), account.get(i));
                usernameMap.put(account.get(i), username);
            }
        }

        // Now all emails in the account should mark first email as their primaryEmail
        for (List<String> account : accounts) {
            final String primaryEmail = findPrimaryEmail(account.get(1), primaryEmailMap);

            for (int i = 2; i < account.size(); i++) {
                primaryEmailMap.put(findPrimaryEmail(account.get(i), primaryEmailMap), primaryEmail);
            }
        }

        // Now let's do our final merging of account(i.e Union)
        for (List<String> account : accounts) {
            final String primaryEmail = findPrimaryEmail(account.get(1), primaryEmailMap);

            mergedAccount.putIfAbsent(primaryEmail, new TreeSet<>());

            for (int i = 1; i < account.size(); i++) {
                mergedAccount.get(primaryEmail).add(account.get(i));
            }
        }

        final List<List<String>> mergedAccountWithUsername = new ArrayList<>();
        // Finally we have all the merged account and we just have to put respective username in front of them
        for (String primaryEmail : mergedAccount.keySet()) {
            final List<String> account = new ArrayList<>(mergedAccount.get(primaryEmail));
            account.add(0, usernameMap.get(primaryEmail)); // Get username of primary email
            mergedAccountWithUsername.add(account);
        }
        return mergedAccountWithUsername;
    }

    public static String findPrimaryEmail(final String email, final Map<String, String> primaryEmailMap) {
        final String primaryEmail = primaryEmailMap.get(email);
        if (!primaryEmail.equals(email)) {
            primaryEmailMap.put(email, findPrimaryEmail(primaryEmail, primaryEmailMap));
        }
        return primaryEmailMap.get(email);
    }


    public static List<List<String>> accountsMergeUsingDFS(List<List<String>> accounts) {
        // Here we need 2 things
        // 1st edge between every two adjacent emailNodes.
        /*
         * Then we will do the DFS and visits all nodes
         * All related accounts will be visited in each iteration of DFS.
         */
        Map<String, List<String>> adjacentList = new HashMap<>();
        Map<String, String> emailIdUserNameMap = new HashMap<>();

        for (List<String> account : accounts) {
            String accountName = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                adjacentList.putIfAbsent(account.get(i), new ArrayList<>()); // For Each Vertex(emailId) their adjacent list is initialized.
                emailIdUserNameMap.put(account.get(i), accountName);

                // Now we will add undirected edge between adjacent email id's of the account
                // Such That
                // Account 1:  E11 E12 E13     ---->   E11----E12----E13
                // Account 2:  E21 E22 E21     ---->   E21----E22----E21
                // Note edge is bidirectional for undirected graph.

                if (i == 1)
                    continue; // Since on 0th(i-1) index Account Name is there and we can't add it to the adjacent list.
                adjacentList.get(account.get(i - 1)).add(account.get(i));
                adjacentList.get(account.get(i)).add(account.get(i - 1));
            }
        }

        List<List<String>> mergedAccounts = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        for (String email : emailIdUserNameMap.keySet()) {
            List<String> emailIdsInThisAccount = new ArrayList<>();
            if (seen.add(email)) { // If email already not visited.
                doDFS(adjacentList, email, seen, emailIdsInThisAccount);
                Collections.sort(emailIdsInThisAccount);
                emailIdsInThisAccount.add(0, emailIdUserNameMap.get(email));
                mergedAccounts.add(emailIdsInThisAccount);
            }
        }
        return mergedAccounts;
    }

    private static void doDFS(Map<String, List<String>> adjacentList, String email, Set<String> seen, List<String> emailIdsInThisAccount) {
        emailIdsInThisAccount.add(email);
        for (String adjacentEmail : adjacentList.get(email)) {
            if (seen.add(adjacentEmail)) { // On proceed if not already visited.
                doDFS(adjacentList, adjacentEmail, seen, emailIdsInThisAccount);
            }
        }
    }
}
