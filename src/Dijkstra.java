import java.util.Enumeration;
import java.util.Hashtable;

/*****************************************************************************************************************
 * Let the node at which we are starting be called the initial node. Let the distance of node Y be the
 * distance from the initial node to Y. Dijkstra's algorithm will assign some initial distance values
 * and will try to improve them step by step.
 *      1. Mark all nodes unvisited = . Create a set of all the unvisited nodes called the unvisited set.
 *
 *      2. Assign to every node a tentative distance value: set it to zero for our initial node and to
 *          infinity for all other nodes. Set the initial node as current.[14]
 *
 *      3. For the current node, consider all of its unvisited neighbours and calculate their tentative
 *          distances through the current node. Compare the newly calculated tentative distance to the current
 *          assigned value and assign the smaller one. For example, if the current node A is marked with a
 *          distance of 6, and the edge connecting it with a neighbour B has length 2, then the distance to B
 *          through A will be 6 + 2 = 8. If B was previously marked with a distance greater than 8 then change
 *          it to 8. Otherwise, the current value will be kept.
 *
 *      4. When we are done considering all of the unvisited neighbours of the current node, mark the
 *          current node as visited and remove it from the unvisited set. A visited node will never be
 *          checked again.
 *
 *      5. If the destination node has been marked visited (when planning a route between two specific nodes)
 *          or if the smallest tentative distance among the nodes in the unvisited set is infinity (when
 *          planning a complete traversal; occurs when there is no connection between the initial node and
 *          remaining unvisited nodes), then stop. The algorithm has finished.
 *
 *      6. Otherwise, select the unvisited node that is marked with the smallest tentative distance, set
 *          it as the new "current node", and go back to step 3.
 *
 *      #  When planning a route, it is actually not necessary to wait until the destination node is "visited"
 *          as above: the algorithm can stop once the destination node has the smallest tentative distance
 *          among all "unvisited" nodes (and thus could be selected as the next "current").
 ****************************************************************************************************************/
public class Dijkstra{

    private static final Integer INF = 99999;
    private Integer[][] graph = null;
    private Integer V = null;
    private Integer[][] settledNodes = null;
    private Integer[][] unSettleNodes = null;
    private Hashtable<String, Integer>  visited = null;
    private Hashtable<String, Integer[]> unvisited = null;
    // Default Constructor
    public Dijkstra(){}

    /**
     * Initializes our class attributes
     * @param graph
     */
    public Dijkstra(Integer[][] graph){
        V = graph.length;
        this.graph = graph;
        unvisited = setUnvisitedNodes(graph);
        printHash_1D(unvisited);
    }
    /**
     * Print hashtable {String, Integer[]}
     * @param hash  Param must be of Hashtable<String, Integer[]>
     */
    public void printHash_1D(Hashtable<String, Integer[]> hash){
        String key = null;
        Enumeration ky = hash.keys();
        while (ky.hasMoreElements())
        {
            key = ky.nextElement().toString();
            System.out.println(key + "  " +printNode(hash.get(key)));
        }
    }
    /**
     * prints the edges of a given node
     * @param node
     */
    public String printNode(Integer[] node)
    {
        int V = node.length;
        int j = 0;
        String edges = "";
        edges += String.format("\n");

        for (int i = 0; i < V; i++) {
            edges += String.format("____%1d___", i+1);
        }
        edges += "\n";
        for ( j = 0; j < V; j++)
        {
            edges += String.format("| %-6d",node[j]);
        }
        edges += String.format("|\n");
        for (int i = 0; i < V; i++) {
            edges += String.format("────────");
        }
        edges += String.format("\n\n");
        return edges;
    }
    /**
     * captures all unsettled nodes
     * @param graph
     * @return
     */
    public Hashtable<String,Integer[]> setUnvisitedNodes(Integer[][] graph)
    {
        int V = graph.length;
        Hashtable<String,Integer[]> unVisited = new Hashtable<>();
        Integer[] node = null;
        for (int i = 0; i < graph.length; i++)
        {
            node = new Integer[V];
            for (int j = 0; j < graph.length; j++) {
                node[j] = graph[i][j];
            }
            unVisited.put(String.valueOf(i+1),node);
        }
        return unVisited;
    }
    public static void main(String[] args) {

                            // A , B , C , D
                            // 1 , 2 , 3 , 4
        Integer[][] graph = {{INF, 1, 7, 5},     // 1
                            {3, INF, INF, 3},    // 2
                            {7, INF, INF, 2},    // 3
                            {5, 3, 2, INF}};     // 4

        Dijkstra dj = new Dijkstra(graph);

    }

    }