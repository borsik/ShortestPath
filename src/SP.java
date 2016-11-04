import java.util.LinkedList;

/**
 * Created by laptop on 04.11.2016.
 */
public class SP {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public SP(EdgeWeightedDigraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        bfs(G, s);
    }

    private void bfs(EdgeWeightedDigraph G, int s) {
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < G.V(); i++) {
            distTo[i] = INFINITY;
        }
        distTo[s] = 0;
        marked[s] = true;
        queue.addLast(s);
        while (!queue.isEmpty()) {
            int v = queue.removeFirst();
            for (DirectedEdge directedEdge : G.getAdj(v)) {
                int w = directedEdge.to();
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + directedEdge.getWeight();
                    marked[w] = true;
                    queue.addLast(w);
                }

            }
        }
    }

    public int distTo(int v) {
        return distTo[v];
    }


    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        LinkedList<Integer> path = new LinkedList<>();
        if (!hasPathTo(v)) {
            return null;
        }
        int x;
        for (x = v; distTo[v] != 0; x = edgeTo[v]) {
            path.addLast(x);
        }
        path.addLast(x);
        return path;
    }
}
