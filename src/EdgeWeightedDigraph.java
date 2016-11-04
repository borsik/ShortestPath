import java.util.LinkedList;
import java.util.Random;

/**
 * Created by olzhas on 03.11.2016.
 */
public class EdgeWeightedDigraph {
    private final int V;
    private int E;
    private LinkedList<DirectedEdge>[] adj;
    private int[] indegree;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        this.adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList();
        }
    }

    public EdgeWeightedDigraph(int V, int E, int W) {
        this(V);
        this.E = E;
        this.indegree = new int[V];
        Random random = new Random();
        for (int i = 0; i < E; i++) {
            int v = random.nextInt(V);
            int w = random.nextInt(V);
            int weight = random.nextInt(2 * W + 1) - W;
            DirectedEdge directedEdge = new DirectedEdge(v, w, weight);
            addEdge(directedEdge);
        }
    }

    public EdgeWeightedDigraph(EdgeWeightedDigraph G) {
        this(G.V());
        this.E = G.getE();

        for (int i = 0; i < G.V(); i++) {
            LinkedList<DirectedEdge> reverse = new LinkedList<>();
            for (DirectedEdge directedEdge : G.getAdj(i)) {
                reverse.addLast(directedEdge);
            }
            for (DirectedEdge directedEdge : reverse) {
                adj[i].add(directedEdge);
            }
        }
    }

    public int V() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void addEdge(DirectedEdge directedEdge) {
        int v = directedEdge.from();
        int w = directedEdge.to();
        validate(v);
        validate(w);
        adj[v].add(directedEdge);
        indegree[w]++;
        E++;
    }

    public void validate(int v) {
        if (v < 0 || v > V - 1) {
            throw new IndexOutOfBoundsException("vertex " + v + "is not between 0 and " + (V - 1));
        }
    }

    public LinkedList<DirectedEdge> getAdj(int v) {
        return adj[v];
    }

    public LinkedList<DirectedEdge> getEdges() {
        LinkedList<DirectedEdge> directedEdges = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            directedEdges.addAll(adj[i]);
        }
        return directedEdges;
    }

    public int[] getIndegree(int v) {
        validate(v);
        return indegree;
    }

    public int getOutdegree(int v) {
        validate(v);
        return adj[v].size();
    }

    public String toString() {
        String s = V + " " + E + "\n";
        for (int i = 0; i < V; i++) {
            for (DirectedEdge directedEdge : adj[i]) {
                s += "[" + directedEdge.toString() + "]" + "\n";
            }
        }
        return s;
    }

}
