/**
 * Created by laptop on 03.11.2016.
 */
public class DirectedEdge {
    private final int v;
    private final int w;
    private final int weight;


    public DirectedEdge(int v, int w, int weight) {
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

    public int getWeight() {
        return weight;
    }


    public String toString() {
        return v + " -> " + w + ", " + weight;
    }
}
