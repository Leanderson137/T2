package algs4;

public class BipartiteX {

    private static final boolean WHITE = false;

    private final boolean[] color;
    private final boolean[] marked;

    private boolean isBipartite;

    public BipartiteX(Graph graph) {

        isBipartite = true;

        color = new boolean[graph.V()];
        marked = new boolean[graph.V()];

        for (
                int v = 0;
                v < graph.V() && isBipartite;
                v++
        ) {

            if (!marked[v]) {
                bfs(graph, v);
            }
        }
    }

    private void bfs(Graph graph, int source) {

        Queue<Integer> queue = new Queue<>();

        color[source] = WHITE;
        marked[source] = true;

        queue.enqueue(source);

        while (!queue.isEmpty() && isBipartite) {

            int v = queue.dequeue();

            for (int w : graph.adj(v)) {

                if (!marked[w]) {

                    marked[w] = true;

                    color[w] = !color[v];

                    queue.enqueue(w);

                } else if (color[w] == color[v]) {

                    isBipartite = false;

                    return;
                }
            }
        }
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public boolean color(int v) {

        validateVertex(v);

        if (!isBipartite) {
            throw new IllegalStateException(
                    "O grafo não é bipartido."
            );
        }

        return color[v];
    }

    private void validateVertex(int v) {

        if (v < 0 || v >= marked.length) {

            throw new IllegalArgumentException(
                    "Vértice " + v +
                    " fora do intervalo 0.." +
                    (marked.length - 1)
            );
        }
    }
}
