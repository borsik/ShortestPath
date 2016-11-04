/**
 * Created by laptop on 03.11.2016.
 */
public class Edge {
    private final int v;
    private final int w;
    private final int weight;


    public Edge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }


    public String toString() {
        return v + " -> " + w + ", " + weight;
    }
}
