package com.karthik.java.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateDivision {
    class Pair<K, V> {
        private K k;
        private V v;

        public Pair(K k1, V v1) {
            k = k1;
            v = v1;
        }

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }
    }

    /*
     Key is the nodeId
     Value is a Pair where Pair.Key is the parent Node of this node and Double is quotient when "this node/parentNode"
     */
    Map<String, Pair<String, Double>> root;

    /*
     Standard Quick Find By path Compression.
     Given a node x, it finds its parent by compressing paths.
     Note the Return type is a Pair<String,Double> where String is the parent node of x and value will be the quotient when x is divided by the parent
     In other words, x = parent * value
     */
    private Pair<String, Double> find(String x) {
        if (!root.containsKey(x))
            root.put(x, new Pair<String, Double>(x, 1.0));
        Pair<String, Double> rootX = root.get(x);
        if (!rootX.getKey().equals(x)) {
            /*
               We need an intermediary variable parentX because, we are now going to multiply the value of rootX with value of ParentX
               Value essentially indicates the quotient when x is divided by its parent
             */
            Pair<String, Double> parentX = find(rootX.getKey());
            root.put(x, new Pair<>(parentX.getKey(), rootX.getValue() * parentX.getValue()));
        }
        return root.get(x);
    }

    /*
      We will not do Union By Rank here
     */
    private void union(String x, String y, double value) {
        Pair<String, Double> rootX = find(x);
        Pair<String, Double> rootY = find(y);
        if (!rootX.getKey().equals(rootY.getKey())) {
            /*
              Always set rootX to be child of rootY
              However, since we are now modifying the root for "rootX" (and not X), when we multiple "rootY" * value, we must divide that by "rootX.value"
             */
            root.put(rootX.getKey(), new Pair<>(rootY.getKey(), (rootY.getValue() * value) / rootX.getValue()));
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        root = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            union(equation.get(0), equation.get(1), values[i]);
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            if (!root.containsKey(query.get(0)) || !root.containsKey(query.get(1)))
                res[i] = -1.0;
            else {
                Pair<String, Double> rootX = find(query.get(0));
                Pair<String, Double> rootY = find(query.get(1));
                /*
                  They are not part of the same connected component. This means we cannot write one variable in form of other therefore, no solution is possible.
                 */
                if (!rootX.getKey().equals(rootY.getKey()))
                    res[i] = -1.0;
                else {
                    res[i] = rootX.getValue() / rootY.getValue();
                }
            }
        }
        return res;
    }

}
