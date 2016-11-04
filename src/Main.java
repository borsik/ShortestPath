/**
 * Created by olzhas on 04.11.2016.
 */
public class Main {

    public static void main(String [] args) {
        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(10, 20, 10);
        System.out.println(edgeWeightedDigraph.toString());
        BellmanFord bellmanFord = new BellmanFord(edgeWeightedDigraph, 0);

    }
}
