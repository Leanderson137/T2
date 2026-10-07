import algs4.BipartiteX;
import algs4.Graph;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws Exception {

        FastScanner in = new FastScanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        // O CSES numera os alunos de 1 até n.
        // Internamente, os vértices são numerados de 0 até n - 1.
        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;

            // A amizade é não direcionada.
            graph.addEdge(a, b);
        }

        BipartiteX bipartite = new BipartiteX(graph);

        // Se o grafo não for bipartido, não existe divisão válida.
        if (!bipartite.isBipartite()) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        StringBuilder answer = new StringBuilder(n * 2);

        for (int v = 0; v < n; v++) {

            // false -> equipe 1
            // true  -> equipe 2
            int team = bipartite.color(v) ? 2 : 1;

            answer.append(team);

            if (v < n - 1) {
                answer.append(' ');
            }
        }

        System.out.println(answer);
    }

    /**
     * Leitor rápido para entradas grandes.
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
