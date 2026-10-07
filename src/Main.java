import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        FastScanner in = new FastScanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        // O CSES usa alunos de 1 até n.
        // Internamente usamos vertices de 0 até n - 1.
        Graph graph = new Graph(n);

        // Leitura das amizades.
        for (int i = 0; i < m; i++) {

            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;

            // A amizade e nao direcionada.
            graph.addEdge(a, b);
        }

        // Verifica se o grafo pode ser dividido em duas partes.
        BipartiteX bipartite = new BipartiteX(graph);

        // Se nao for bipartido, nao existe resposta.
        if (!bipartite.isBipartite()) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        // Cada cor sera convertida para uma equipe:
        // false -> equipe 1
        // true  -> equipe 2
        StringBuilder answer = new StringBuilder(n * 2);

        for (int v = 0; v < n; v++) {

            int team = bipartite.color(v) ? 2 : 1;

            answer.append(team);

            if (v < n - 1) {
                answer.append(' ');
            }
        }

        System.out.println(answer);
    }

    /**
     * Leitor rapido para entradas grandes.
     */
    private static class FastScanner {

        private final InputStream in;

        private final byte[] buffer = new byte[1 << 16];

        private int pointer = 0;
        private int length = 0;

        FastScanner(InputStream input) {
            this.in = new BufferedInputStream(input);
        }

        private int read() throws IOException {

            if (pointer >= length) {

                length = in.read(buffer);
                pointer = 0;

                if (length == -1) {
                    return -1;
                }
            }

            return buffer[pointer++];
        }

        int nextInt() throws IOException {

            int c;

            do {
                c = read();
            } while (c <= ' ' && c != -1);

            if (c == -1) {
                throw new IOException("Fim inesperado da entrada.");
            }

            int sign = 1;

            if (c == '-') {
                sign = -1;
                c = read();
            }

            int value = 0;

            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }

            return value * sign;
        }
    }
}


/**
 * Representacao de um grafo nao direcionado
 * utilizando listas de adjacencia.
 *
 * Adaptacao da ideia da classe Graph do algs4.
 */
class Graph {

    private final int V;
    private int E;

    private final List<Integer>[] adj;

    @SuppressWarnings("unchecked")
    Graph(int V) {

        if (V < 0) {
            throw new IllegalArgumentException(
                    "Numero de vertices negativo."
            );
        }

        this.V = V;
        this.E = 0;

        adj = (List<Integer>[]) new List[V];

        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    int V() {
        return V;
    }

    int E() {
        return E;
    }

    /**
     * Adiciona uma aresta nao direcionada v-w.
     */
    void addEdge(int v, int w) {

        validateVertex(v);
        validateVertex(w);

        adj[v].add(w);
        adj[w].add(v);

        E++;
    }

    /**
     * Retorna os vizinhos de v.
     */
    Iterable<Integer> adj(int v) {

        validateVertex(v);

        return adj[v];
    }

    private void validateVertex(int v) {

        if (v < 0 || v >= V) {

            throw new IllegalArgumentException(
                    "Vertice " + v +
                            " fora do intervalo 0.." + (V - 1)
            );
        }
    }
}


/**
 * Fila FIFO utilizada pela BFS.
 *
 * Adaptacao da ideia da classe Queue do algs4.
 */
class Queue<Item> {

    private Node<Item> first;
    private Node<Item> last;

    private int n;

    private static class Node<Item> {

        private Item item;
        private Node<Item> next;
    }

    boolean isEmpty() {
        return n == 0;
    }

    /**
     * Insere no final da fila.
     */
    void enqueue(Item item) {

        Node<Item> oldLast = last;

        last = new Node<>();

        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }

        n++;
    }

    /**
     * Remove do inicio da fila.
     */
    Item dequeue() {

        if (isEmpty()) {
            throw new IllegalStateException("Fila vazia.");
        }

        Item item = first.item;

        first = first.next;

        n--;

        if (isEmpty()) {
            last = null;
        }

        return item;
    }
}


/**
 * Verifica se um grafo nao direcionado e bipartido
 * utilizando BFS.
 *
 * Adaptacao da implementacao BipartiteX do algs4.
 *
 * O algoritmo atribui uma de duas cores para cada vertice.
 * Vertices adjacentes devem possuir cores diferentes.
 */
class BipartiteX {

    private static final boolean WHITE = false;

    private final boolean[] color;
    private final boolean[] marked;

    private boolean isBipartite;

    BipartiteX(Graph graph) {

        isBipartite = true;

        color = new boolean[graph.V()];
        marked = new boolean[graph.V()];

        /*
         * O grafo pode ter varios componentes desconectados.
         * Por isso, iniciamos uma BFS para cada vertice ainda
         * nao visitado.
         */
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

    /**
     * Realiza uma BFS a partir do vertice source.
     */
    private void bfs(Graph graph, int source) {

        Queue<Integer> queue = new Queue<>();

        // Primeiro vertice recebe a primeira cor.
        color[source] = WHITE;

        marked[source] = true;

        queue.enqueue(source);

        while (!queue.isEmpty() && isBipartite) {

            int v = queue.dequeue();

            for (int w : graph.adj(v)) {

                /*
                 * Se o vizinho ainda nao foi visitado,
                 * recebe a cor oposta.
                 */
                if (!marked[w]) {

                    marked[w] = true;

                    color[w] = !color[v];

                    queue.enqueue(w);
                }

                /*
                 * Se dois vertices adjacentes possuirem
                 * a mesma cor, o grafo nao e bipartido.
                 */
                else if (color[w] == color[v]) {

                    isBipartite = false;

                    return;
                }
            }
        }
    }

    /**
     * Retorna true se o grafo for bipartido.
     */
    boolean isBipartite() {
        return isBipartite;
    }

    /**
     * Retorna a cor do vertice.
     *
     * false -> primeira equipe
     * true  -> segunda equipe
     */
    boolean color(int v) {

        validateVertex(v);

        if (!isBipartite) {

            throw new IllegalStateException(
                    "O grafo nao e bipartido."
            );
        }

        return color[v];
    }

    private void validateVertex(int v) {

        if (v < 0 || v >= marked.length) {

            throw new IllegalArgumentException(
                    "Vertice " + v +
                            " fora do intervalo 0.." +
                            (marked.length - 1)
            );
        }
    }
}
