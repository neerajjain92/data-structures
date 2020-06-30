package com.leetcode.year_2020.june_challenge.week4;

import java.util.*;

/**
 * @author neeraj on 28/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ReconstructItinerary {

    public static void main(String[] args) {
        List<List<String>> tickets = getTickets(new String[][]{{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL", "SFO"}});
        findItinerary(tickets);

        tickets = getTickets(new String[][]{{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}});
        findItinerary(tickets);

        tickets = getTickets(new String[][]{{"JFK", "KUL"}, {"JFK", "NRT"}, {"NRT", "JFK"}});
        findItinerary(tickets);

        tickets = getTickets(new String[][]{{"EZE", "AXA"}, {"TIA", "ANU"}, {"ANU", "JFK"}, {"JFK", "ANU"},
                {"ANU", "EZE"}, {"TIA", "ANU"}, {"AXA", "TIA"}, {"TIA", "JFK"}, {"ANU", "TIA"}, {"JFK", "TIA"}});
        findItinerary(tickets);
    }

    public static List<List<String>> getTickets(String[][] ticket) {
        List<List<String>> tickets = new ArrayList<>();
        for (String[] tic : ticket) {
            tickets.add(Arrays.asList(tic[0], tic[1]));
        }
        return tickets;
    }

    static LinkedList<String> path;

    public static List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> ticketsMap = new HashMap<>();
        for (List<String> ticket : tickets) {
            ticketsMap.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            ticketsMap.get(ticket.get(0)).add(ticket.get(1));
        }
        path = new LinkedList<>();
        doDFS("JFK", ticketsMap);
        return path;
    }

    private static void doDFS(String departure, Map<String, PriorityQueue<String>> flights) {
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty()) {
            doDFS(arrivals.poll(), flights);
        }
        path.addFirst(departure);
    }
}
