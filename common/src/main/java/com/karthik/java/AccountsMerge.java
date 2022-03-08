package com.karthik.java;

import java.util.*;

public class AccountsMerge {

    private Map<String, List<String>> emailGraph = new HashMap<>();
    private HashSet<String> visited = new HashSet<>();

    private List<String> dfs(String g) {
        List<String> res = new ArrayList<>();
        res.add(g);
        for (String w : emailGraph.get(g)) {
            if (!visited.contains(w)) {
                visited.add(w);
                res.addAll(dfs(w));
            }
        }
        return res;
    }

    private void buildGraph(List<List<String>> accounts) {
        for (List<String> account : accounts) {
            String g = account.get(1);
            if (!emailGraph.containsKey(g))
                emailGraph.put(g, new ArrayList<>());
            for (int i = 2; i < account.size(); i++) {
                String other = account.get(i);
                emailGraph.get(g).add(other);
                if (!emailGraph.containsKey(other))
                    emailGraph.put(other, new ArrayList<>());
                emailGraph.get(other).add(g);
            }
        }

    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        buildGraph(accounts);
        List<List<String>> res = new ArrayList<>();
        for (List<String> account : accounts) {
            List<String> accountRes = new ArrayList<>();
            String accountName = account.get(0);
            String accountFirstEmail = account.get(1);
            if (!visited.contains(accountFirstEmail)) {
                visited.add(accountFirstEmail);
                accountRes.add(accountName);
                List<String> emails = dfs(accountFirstEmail);
                Collections.sort(emails);
                accountRes.addAll(emails);
                res.add(accountRes);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));

        System.out.println(new AccountsMerge().accountsMerge(accounts));
    }
}