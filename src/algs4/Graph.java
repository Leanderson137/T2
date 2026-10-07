package algs4;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private final int V;
    private int E;

    private final List<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public Graph(int V) {

        if (V < 0) {
            throw new IllegalArgumentException(
                    "Número de vértices negativo."
            );
        }

        this.V = V;
        this.E = 0;

        adj = (List<Integer>[]) new List[V];

        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {

        validateVertex(v);
        validateVertex(w);

        adj[v].add(w);
        adj[w].add(v);

        E++;
    }

    public Iterable<Integer> adj(int v) {

        validateVertex(v);

        return adj[v];
    }

    private void validateVertex(int v) {

        if (v < 0 || v >= V) {
            throw new IllegalArgumentException(
                    "Vértice " + v +
                    " fora do intervalo 0.." + (V - 1)
            );
        }
    }
}
