package Dijkstra;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

/////////////////////////////////////////////////////////////////////

/**
 * <pre>
 * Dijkstra’s algorithm has one motivation: to find the shortest paths
 * from a start node to all other nodes on the graph.
 *
 * The cost of a path that connects two nodes is calculated by adding
 * the weights of all the edges that belong to the path.
 *
 * The shortest path is the sequence of nodes, in the order they are
 * visited, which results in the minimum cost to travel between the
 * start and end node.
 *
 * When the algorithm has finished running, it produces a list that
 * holds the following information for each node:
 *  <ol>
 *      <li>The node label</li>
 *      <li>The cost of the shortest path to that node (from the start node)</li>
 *      <li>The label of the previous node in the path</li>
 *  </ol>
 * Using the information in this list you can backtrack through the
 * previous nodes back to the start node. This will give you the
 * shortest path (sequence of visited nodes) from the start node to
 * each node and the cost of each path. This can be seen in the
 * worked example that follows.
 * @since 2022-08-6
 * </pre>
 */
public class Dijkstra< T > {
    private static final Logger log = LoggerFactory.getLogger(Dijkstra.class);

    /**
     * Dijkstra Algorithm.
     */
    public Dijkstra(){}

    /**
     * <h2>Procedure Algorithm Dijkstra’s Algorithm</h2>
     * <hr/>
     * Recursive implementation of Dijkstra Algorithm.
     * Calculates the shortest path from the start node to
     * destination node from a graph/digraph of size n.
     * <hr/>
     * <h3>
     * <strong>Dijkstra( G, d, a, z ):= [a...z]</strong> {shortest path from a to z}
     * </h3>
     * <pre>
     * {<strong>Below:</strong> G has vertices/edges from a to z,
     * a => v<sub>0</sub>,v<sub>1</sub>,...,v<sub>n</sub> => z
     * and lengths w(v<sub>i</sub>, v<sub>j</sub>)
     * where w(v<sub>i</sub>, v<sub>j</sub>) = +Infinity
     * if {v<sub>i</sub>, v<sub>j</sub>} is not an edge in G}
     *
     * Dijkstra(
     *     G := digraph,
     *     d := distance/weight/cost of edges,
     *     a := start node,
     *     z := target/destination node ):= shortest path to z
     *
     *
     * {<strong>Below:</strong> Node labels/weights are now
     * initialized so that the label/weight of the start
     * node/vertex a is 0 and all other node labels/weights
     * are set to  +Infinity, and S is the empty set that
     * represents the set of nodes in the shortest
     * path from a to z. Optionally: You can set a default value
     * of +Infinity to omit this step}
     *
     * if (d == 0) {initial function call should initialize d := 0}
     *     for v<sub>i</sub> in n;
     *        v<sub>i</sub> := +Infinity;   {initialize all nodes to +Infinity}
     *     a := 0;     {initialize the start node distance/weight to 0}
     *
     * L(a) := 0;  {add start node to list unsettled nodes}
     * S := 0;     {initialize list for nodes in shortest path from a to z}
     *
     * {<strong>Below:</strong> while the destination node/vertex z is not part of
     * the shortest path list, pick the node u to a with the minimum distance/weight
     * and add set S }
     *
     * while z ∉ S; {here is where we recursively traverse down the
     *                     tree of nodes backup the stack}
     *    u := a {vertex not in S with L(u) minimal distance
     *        node/vertex}
     *    S := S ∪ {u} {add u to set S}
     *
     *    {<strong>Below:</strong> for all adjacent nodes/vertices not in
     *    shortest path set, compare the weights L(u) + w(u,v) &#60; L(v)}
     *
     *     for all {v} vertices adjacent to u or not in S;
     *         if L(u) + w(u, v) &#60; L(v)
     *         then L(v) := L(u) + w(u, v);
     *
     *         {this adds a vertex to S with minimal label and
     *         updates the labels of vertices not in S}
     *
     *         L := L U {v}    {add minimal node to list L(v)}
     *
     * {<strong>Below:</strong> this should return back up a list of nodes leading
     * to the root; the list represents the shortest path from
     * the start to destination node}
     *
     * return S(z) {S(z) = list of shortest path from a to z}
     *
     * </pre>
     *
     * @param graph the graph or digraph
     * @param distance the distance of the node from origin/start node
     * @param startNode the start node
     * @param destinationNode the destination node
     * @return a sorted {@link List} of {@link Node} representing the
     * shortest path from start node to destination node z.
     * @see Node
     */
    public List<Node<T,T,T>> calculateShortestPath(
            Graph<Node<T,T,T>> graph,
            Distance<T> distance,
            Node<T,T,T> startNode,
            Node<T,T,T> destinationNode)
    {
        log.info("\nInitialization......\nStart Node: {}\n",startNode);

        // create two lists, undiscovered/unsettled nodes and discovered/settled nodes;
        // contains all unexplored nodes
        List<Edge<Distance<T>, Node<T,T,T>>> unsettledNodeEdges = new LinkedList<>();
        // contains the path explored to the destination node
        List<Node<T,T,T>> settledNodes = new LinkedList<>();
        // initialize all nodes distance to +Infinity;
        // and start node distance to 0
        if ( (Integer) distance.getValue() == 0)
        {
            // Initialization of all nodes with distance "infinite";
            graph.getNodes().forEach(
                     gNode -> {
                         Distance<Integer> d = new Distance<>( Integer.MAX_VALUE);
                         gNode.setDistance((Distance<T>) d);
                     }
            );

            // initialization of the starting node with 0
            startNode.setDistance(distance);
        }

        // add the start node to unsettled node list
        unsettledNodeEdges.add(
                new Edge<>(
                        distance,
                        startNode
                )
        );

        // get the node on with the smallest/minimum distance
        Edge<Distance<T>,Node<T,T,T>> nodeEdge = getMinimumDistanceNodeEdge(unsettledNodeEdges);

        // for each neighbor adjacent node of the minimum distance node, where n has not yet been
        // removed from unsettled nodes do;
        for (Edge<Distance<T>, Node<T,T,T>> evaluationNode : nodeEdge.getNode().getEdges())
        {

            // temp container for nodes discovered on the frontier aka settled nodes
            List<Node<T,T,T>> tempSettled = new LinkedList<>();

            // node adjacent to start node
            Node<T,T,T> adjacentNode = evaluationNode.getNode();

            // edge distance = dist[minimum dist node] + dist_between([minimum dist node] , [evaluation node])
            Integer edgeWeight =  (Integer) nodeEdge.getDistance().getValue() + (Integer) evaluationNode.getDistance().getValue();

            // remove minimal node from the undiscovered frontier/unsettled nodes list
            unsettledNodeEdges.remove(nodeEdge);

            log.info("{\n\tCurrent Minimal Node: {} \n\n\tAdjacent Node: {}" +
                            "\n\n\tDistance from: {} -> {} := {} \n\n\tUnsettled Nodes: {} " +
                            "\n\n\tSettled Nodes: {} \n}",
                    nodeEdge,evaluationNode,nodeEdge.getNode().getName(),
                    adjacentNode.getName(),edgeWeight,unsettledNodeEdges,settledNodes);

            // update adjacent node distance, if edgeDistance < adjacentNode distance;
            if (edgeWeight < (Integer) adjacentNode.getDistance().getValue())
            {
                // update adjacentNode distance = edgeDistance;
                adjacentNode.setDistance( (Distance<T>) new Distance<>(edgeWeight));

                log.info("\nUpdated Adjacent Node: {}\n", evaluationNode);

                // add the adjacent node to the undiscovered frontier/unsettled nodes list for later processing
                unsettledNodeEdges.add(
                        new Edge<Distance<T>, Node<T, T, T>>(
                                 new Distance<>((T) edgeWeight),
                                adjacentNode
                        )
                );
                // if destination node not in discovered/settled node initiate recursive call
                if (!settledNodes.contains(destinationNode))
                {
                    // continue as long as we have undiscovered nodes on the frontier
                    // add unexplored node to settledNode list
                    tempSettled.addAll(calculateShortestPath(
                            graph,
                            adjacentNode.getDistance(),
                            adjacentNode,
                            destinationNode)
                    );
                }
            }
            // remove any undesired paths with accumulated distance greater than the
            // accumulated distance of the destination node
            if (tempSettled.contains(destinationNode))
            {
                // add the cleansed temp list to the settled node
                settledNodes.addAll(tempSettled);
            }
        }

        // our look back or back up; add previous node to discovered list of settled nodes;
        settledNodes.addAll(List.of(nodeEdge.getNode()));

        log.info("\nReturned Settled Nodes: {}\n",
                settledNodes);

        return settledNodes;
    }
    /**
     * Retrieve the edge with the minimum distance node.
     * @param nodes the list of node.
     * @return the node {@link Edge}<{@link Distance,Node}>with the lowest weight/cost edge.
     * @see Distance
     * @see Node
     */
    private Edge<Distance<T>, Node<T,T,T>> getMinimumDistanceNodeEdge(
                    List<Edge<Distance<T>, Node<T,T,T>>> nodes){
        //////////////////////////////////////////////////////////////////
        Node<T,T,T> minimumDistanceNode = null;
        Distance<T> minimumDistance = (Distance<T>) new Distance<>(Integer.MAX_VALUE);
        // get the minimal distance node
        for (Edge<Distance<T>, Node<T,T,T>> n : nodes)
        {
            Integer nodeDistance = (Integer) n.getDistance().getValue();
            if( nodeDistance < (Integer) minimumDistance.getValue() )
            {
                minimumDistance = n.getDistance();
                minimumDistanceNode = n.getNode();
            }
        }
        log.info("\nGet Minimum Node: {} \nMinimum Distance: {} \n" +
                        "\nUnsettled Nodes: {} \n",
                minimumDistance, minimumDistanceNode, nodes );

        return new Edge<>(minimumDistance,minimumDistanceNode);
    }

    public static void main(String[] args) {
        try {
            Long startTime = System.currentTimeMillis();
            Node<Object,Object,Object> nodeA = new Node<Object,Object,Object>("A", "AA");
            Node<Object,Object,Object> nodeB = new Node<Object,Object,Object>("B", "BB");
            Node<Object,Object,Object> nodeC = new Node<Object,Object,Object>("C", "CC");
            Node<Object,Object,Object> nodeD = new Node<Object,Object,Object>("D", "DD");
            Node<Object,Object,Object> nodeE = new Node<Object,Object,Object>("E", "EE");
            Node<Object,Object,Object> nodeF = new Node<Object,Object,Object>("F", "FF");
            Node<Object,Object,Object> nodeG = new Node<Object,Object,Object>("G", "GG");
            Node<Object,Object,Object> nodeH = new Node<Object,Object,Object>("H", "HH");
            Node<Object,Object,Object> nodeI = new Node<Object,Object,Object>("I", "II");
            Node<Object,Object,Object> nodeJ = new Node<Object,Object,Object>("J", "JJ");
            Node<Object,Object,Object> nodeK = new Node<Object,Object,Object>("K", "KK");
            Node<Object,Object,Object> nodeL = new Node<Object,Object,Object>("L", "LL");

            nodeA.setCoordinate( new Coordinate<Object,Object>(1,1));
            nodeB.setCoordinate(new Coordinate<Object,Object>(1,2));
            nodeC.setCoordinate(new Coordinate<Object,Object>(1,3));
            nodeD.setCoordinate(new Coordinate<Object,Object>(1,4));
            nodeC.setCoordinate(new Coordinate<Object,Object>(2,1));
            nodeE.setCoordinate(new Coordinate<Object,Object>(2,2));
            nodeF.setCoordinate(new Coordinate<Object,Object>(2,3));
            nodeG.setCoordinate(new Coordinate<Object,Object>(2,4));
            nodeH.setCoordinate(new Coordinate<Object,Object>(3,1));
            nodeI.setCoordinate(new Coordinate<Object,Object>(3,2));
            nodeJ.setCoordinate(new Coordinate<Object,Object>(3,3));
            nodeK.setCoordinate(new Coordinate<Object,Object>(3,4));
            nodeL.setCoordinate(new Coordinate<Object,Object>(4,1));


            nodeA.addAdjacentNode(nodeB, new Distance<Object>(1));
            nodeA.addAdjacentNode(nodeC, new Distance<Object>(1));
            nodeB.addAdjacentNode(nodeD, new Distance<Object>(1));
            nodeB.addAdjacentNode(nodeC, new Distance<Object>(1));
            nodeC.addAdjacentNode(nodeA, new Distance<Object>(1));
            nodeD.addAdjacentNode(nodeA, new Distance<Object>(1));
            nodeD.addAdjacentNode(nodeG, new Distance<Object>(1));
            nodeF.addAdjacentNode(nodeE, new Distance<Object>(1));
            nodeF.addAdjacentNode(nodeI, new Distance<Object>(1));
            nodeG.addAdjacentNode(nodeD, new Distance<Object>(1));
            nodeG.addAdjacentNode(nodeJ, new Distance<Object>(1));
            nodeH.addAdjacentNode(nodeL, new Distance<Object>(1));
            nodeH.addAdjacentNode(nodeK, new Distance<Object>(1));
            nodeI.addAdjacentNode(nodeG, new Distance<Object>(1));
            nodeI.addAdjacentNode(nodeK, new Distance<Object>(1));
            nodeJ.addAdjacentNode(nodeH, new Distance<Object>(2));
            nodeJ.addAdjacentNode(nodeI, new Distance<Object>(4));
            nodeK.addAdjacentNode(nodeH, new Distance<Object>(1));
            nodeK.addAdjacentNode(nodeB, new Distance<Object>(1));
            nodeL.addAdjacentNode(nodeJ, new Distance<Object>(5));
            nodeL.addAdjacentNode(nodeD, new Distance<Object>(5));

            Graph<Node<Object,Object,Object>> graph = new Graph<Node<Object,Object,Object>>();

            graph.addNode(nodeA);
            graph.addNode(nodeB);
            graph.addNode(nodeC);
            graph.addNode(nodeD);
            graph.addNode(nodeE);
            graph.addNode(nodeF);
            graph.addNode(nodeG);
            graph.addNode(nodeH);
            graph.addNode(nodeI);
            graph.addNode(nodeJ);
            graph.addNode(nodeK);
            graph.addNode(nodeL);
            Dijkstra<Object> dijkstra = new Dijkstra<>();
            List<Node<Object,Object,Object>> results0 =  dijkstra
                    .calculateShortestPath(
                             graph,
                            new Distance<Object>(0),
                             nodeA,
                             nodeL);

            nodeA.setShortestPath( results0);
            Long results0EndTime = System.currentTimeMillis() - startTime;
            log.info("\nRecursive Shortest Path 0:{} \nRuntime: {}\n", results0, results0EndTime);
            boolean found = nodeL.equals(nodeA.getShortestPath().get(0));
            log.info("\nNode Found: {}",found);

        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

}
/////////////////////////////////////////////////////////////////////