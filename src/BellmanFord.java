import java.util.LinkedList;

/**
 * Created by laptop on 04.11.2016.
 */
public class BellmanFord {
    private DirectedEdge[] edgeTo;
    private int[] distTo;
    private boolean[] onQueue;
    private LinkedList<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public BellmanFord(EdgeWeightedDigraph G, int s) {
        distTo = new int[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQueue = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Integer.MAX_VALUE;
        }
        distTo[s] = 0;

        queue = new LinkedList<>();
        queue.addLast(s);
        onQueue[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.removeFirst();
            onQueue[v] = false;
            relax(G, v);
        }

    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for(DirectedEdge e : G.getAdj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.getWeight()) {
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[v] = e;
                if (!onQueue[w]) {
                    queue.addLast(w);
                    onQueue[w] = true;
                }
            }
            if (cost++ % G.V() == 0) {
                findNegativeCycle();
                if (hasNegativeCycle()) {
                    return;
                }
            }
        }
    }

    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++) {
            if (edgeTo[v] != null) {
                spt.addEdge(edgeTo[v]);
            }
        }
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }


    public int distTo(int v) {
        if (hasNegativeCycle()) {
            throw new UnsupportedOperationException("Negative cost cycle exists!");
        }
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo(v) < Integer.MAX_VALUE;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        LinkedList<DirectedEdge> path = new LinkedList<>();

        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.addLast(e);
        }
        return path;
    }

}
