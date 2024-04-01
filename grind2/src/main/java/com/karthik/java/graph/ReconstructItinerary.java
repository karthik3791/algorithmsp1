package com.karthik.java.graph;

import java.util.*;

public class ReconstructItinerary {

    private Map<String, List<String>> graph;
    private Set<String> visited;
    private List<String> res;

    public List<String> findItinerary(List<List<String>> tickets) {
        graph = new HashMap<>();
        for (List<String> edges : tickets)
            graph.computeIfAbsent(edges.get(0), s -> new ArrayList<>()).add(edges.get(1));
        visited = new HashSet<>();
        String u = "JFK";
        List<String> curPath = new ArrayList<>();
        curPath.add(u);
        visited.add(u);
        int index = 0;
        dfs(u, 0, curPath);
        return res;
    }

    /*
     This approach is wrong simply because each vertex is visited only once.
     */
    private void dfs(String u, int index, List<String> curPath) {
        boolean allVisited = true;
        for (String v : graph.getOrDefault(u, new ArrayList<>())) {
            if (!visited.contains(v)) {
                allVisited = false;
                visited.add(v);
                if (res.isEmpty() || (res.size() > index + 1 && res.get(index + 1).compareTo(v) > 0)) {
                    curPath.add(v);
                    dfs(v, index + 1, curPath);
                    curPath.remove(curPath.size() - 1);
                }
                visited.remove(v);
            }
        }
        if (allVisited)
            res = new ArrayList<>(curPath);
    }

    public static void main(String[] args) {
        //List<List<String>> tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]

        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("MUC", "LHR"));
        tickets.add(Arrays.asList("JFK", "MUC"));
        tickets.add(Arrays.asList("SFO", "SJC"));
        tickets.add(Arrays.asList("LHR", "SFO"));
        ReconstructItinerary r = new ReconstructItinerary();
        r.findItinerary(tickets);

    }
}

/*
This is the correct solution
 */
class ReconstructItineraryEuler {

    Map<String, LinkedList<String>> graph = new HashMap<>();
    LinkedList<String> result = null;

    public List<String> findItinerary(List<List<String>> tickets) {
        // Step 1). build the graph first
        for (List<String> ticket : tickets)
            this.graph.computeIfAbsent(ticket.get(0), v -> new LinkedList<>()).add(ticket.get(1));

        // Step 2). order the destinations so that we will always choose the lexographically smaller edge first
        this.graph.forEach((key, value) -> Collections.sort(value));

        this.result = new LinkedList<String>();
        // Step 3). post-order DFS
        this.dfs("JFK");
        return this.result;
    }

    protected void dfs(String origin) {
        // Visit all the outgoing edges first.
        LinkedList<String> destList = this.graph.getOrDefault(origin, new LinkedList<>());
        while (!destList.isEmpty()) {
            /*
             This removes an edge once we traverse through it.
             This way, we ensure that we visit all the edges atleast once and avoid dupes.
            */
            // while we visit the edge, we trim it off from graph.
            String dest = destList.pollFirst();
            dfs(dest);
        }

        /*
         When we have a vertex which no longer has an edge, it means it belongs to the final result.
         We keep adding it to the front so that the intermediary vertices end up in the middle
         */
        this.result.offerFirst(origin);
    }
}