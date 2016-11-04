import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by olzhas on 03.11.2016.
 */
public class Graph {
    private final int V;
    private int E;
    private HashMap<Integer, LinkedList<Edge>> adj;
    private int[] indegree;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        this.adj = new HashMap();
        for (int i = 0; i < V; i++) {
            adj.put(i, new LinkedList());
        }
    }

    public Graph(int V, int E, int W) {
        this(V);
        this.E = E;
        this.indegree = new int[V];
        Random random = new Random();
        for (int i = 0; i < E; i++) {
            int v = random.nextInt(V);
            int w = random.nextInt(V);
            int weight = random.nextInt(2 * W + 1) - W;
            Edge edge = new Edge(v, w, weight);
            addEdge(edge);
        }
    }
    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public void addEdge(Edge edge) {
        int v = edge.from();
        int w = edge.to();
        validate(v);
        validate(w);
        adj.get(v).add(edge);
        indegree[w]++;
        E++;
    }

    public void validate(int v) {
        if (v < 0 || v > V - 1) {
            throw new IndexOutOfBoundsException("vertex " + v + "is not between 0 and " + (V - 1));
        }
    }

    public LinkedList<Edge> getAdj(int v) {
        return adj.get(v);
    }

    public LinkedList<Edge> getEdges() {
        LinkedList<Edge> edges = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            edges.addAll(adj.get(i));
        }
        return edges;
    }

    public int[] getIndegree(int v) {
        validate(v);
        return indegree;
    }

    public int getOutdegree(int v) {
        validate(v);
        return adj.get(v).size();
    }

    public String toString() {
        String s = V + " " + E + "\n";
        for (int i = 0; i < V; i++) {
            for (Edge edge : adj.get(i)) {
                s += "[" + edge.toString() + "]" + "\n";
            }
        }
        return s;
    }

}
