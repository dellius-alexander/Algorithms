package Dijkstra;

import Node.*;
import Node.Graph.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/////////////////////////////////////////////////////////////////////

/**
 * <pre>
 * Dijkstra.Dijkstra’s algorithm has one motivation: to find the shortest paths
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
public class  Dijkstra extends DijkstraInterface<Object,Object,Object> {
    private static final Logger log = LoggerFactory.getLogger(Dijkstra.class);

    ///////////////////////////////////////////////////////////////////////
    /**
     * This evaluation function provides a means by which to find the edge with the
     * minimum distance node.
     * @param nodes the list of node.
     * @return the node {@link Edge}<{@link Distance , Node}>with the lowest weight/cost edge.
     * @see Distance
     * @see Node
     */
    public Edge<Distance<Object>,Node<Object,Object,Object>> getMinimumDistanceNodeEdge(
            List<Edge<Distance<Object>,Node<Object,Object,Object>>> nodes
    ){
        //////////////////////////////////////////////////////////////////
        Node<Object,Object,Object> minimumDistanceNode = null;
        Distance<Object> minimumDistance = new Distance<>((Object) Integer.MAX_VALUE);
        // get the minimal distance node
        for (Edge<Distance<Object>, Node<Object,Object,Object>> n : nodes)
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

    /**
     * You must evaluate the parent and child node and determine if a goal
     * state has been reached and return the results of this analysis. This
     * allows for flexibility and customization based on the node contents.
     *
     * @param parentNodeEdge parent node
     * @param childNodeEdge  child node
     * @return true if goal state reached, false otherwise
     */
    @Override
    public boolean evaluateGoal(
            Edge<Distance<Object>, Node<Object,Object,Object>> parentNodeEdge,
            Edge<Distance<Object>, Node<Object,Object,Object>> childNodeEdge)
    {
        // node adjacent to start node
        Node<Object,Object,Object> adjacentNode = childNodeEdge.getNode();
        // edge distance = dist[minimum dist node] + dist_between([minimum dist node] , [evaluation node])
        Integer edgeWeight = (Integer) parentNodeEdge.getDistance().getValue() + (Integer) childNodeEdge.getDistance().getValue();
        log.info("{\n\tParent Node: {} \n\n\tChild Node: {}" +
                        "\n\n\tDistance from: {} -> {} := {} \n}",
                parentNodeEdge,childNodeEdge,parentNodeEdge.getNode().getName(),
                adjacentNode.getName(),edgeWeight );
        // update adjacent node distance, if edgeDistance < adjacentNode distance;
        if (edgeWeight < (Integer)  adjacentNode.getDistance().getValue())
        {
            // update adjacentNode distance = edgeDistance;
            adjacentNode.setDistance(new Distance<>((Object) edgeWeight));
            log.info("\nUpdated Adjacent Node: {}\n", childNodeEdge);
            return true;
        }
        return false;
    }

    /**
     * This function allows you to initialize your evaluation parameter to fit
     * the problem domain. The start node on the other hand must be settled and
     * omitted from the initialization process.
     *
     * @param graph the graph
     * @return the initialized graph
     */
    @Override
    public Graph<Node<Object,Object,Object>> initialize(
            Graph<Node<Object,Object,Object>> graph,
            Node<Object,Object,Object> startNode,
            Distance<Object> distance) {
        if ((Integer) distance.getValue() ==  0)
        {
            for (Node<Object,Object,Object> node : graph.getNodes())
            {
                if (node.equals(startNode)) {
                    node.setDistance(new Distance<>((Object) 0));
                } else {
                    node.setDistance(new Distance<>((Object)Integer.MAX_VALUE));
                }
            }
        }
        return graph;
    }
    
    
    //
//    /**
//     * <h2>Procedure Algorithm Dijkstra.Dijkstra’s Algorithm</h2>
//     * <hr/>
//     * Recursive implementation of Dijkstra.Dijkstra Algorithm.
//     * Calculates the shortest path from the start node to
//     * destination node from a graph/digraph of size n.
//     * <hr/>
//     * <h3>
//     * <strong>Dijkstra.Dijkstra( G, d, a, z ):= [a...z]</strong> {shortest path from a to z}
//     * </h3>
//     * <pre>
//     * {<strong>Below:</strong> G has vertices/edges from a to z,
//     * a => v<sub>0</sub>,v<sub>1</sub>,...,v<sub>n</sub> => z
//     * and lengths w(v<sub>i</sub>, v<sub>j</sub>)
//     * where w(v<sub>i</sub>, v<sub>j</sub>) = +Infinity
//     * if {v<sub>i</sub>, v<sub>j</sub>} is not an edge in G}
//     *
//     * Dijkstra.Dijkstra(
//     *     G := digraph,
//     *     d := distance/weight/cost of edges,
//     *     a := start node,
//     *     z := target/destination node ):= shortest path to z
//     *
//     *
//     * {<strong>Below:</strong> Node labels/weights are now
//     * initialized so that the label/weight of the start
//     * node/vertex a is 0 and all other node labels/weights
//     * are set to  +Infinity, and S is the empty set that
//     * represents the set of nodes in the shortest
//     * path from a to z. Optionally: You can set a default value
//     * of +Infinity to omit this step}
//     *
//     * if (d == 0) {initial function call should initialize d := 0}
//     *     for v<sub>i</sub> in n;
//     *        v<sub>i</sub> := +Infinity;   {initialize all nodes to +Infinity}
//     *     a := 0;     {initialize the start node distance/weight to 0}
//     *
//     * L(a) := 0;  {add start node to list unsettled nodes}
//     * S := 0;     {initialize list for nodes in shortest path from a to z}
//     *
//     * {<strong>Below:</strong> while the destination node/vertex z is not part of
//     * the shortest path list, pick the node u to a with the minimum distance/weight
//     * and add set S }
//     *
//     * while z ∉ S; {here is where we recursively traverse down the
//     *                     tree of nodes backup the stack}
//     *    u := a {vertex not in S with L(u) minimal distance
//     *        node/vertex}
//     *    S := S ∪ {u} {add u to set S}
//     *
//     *    {<strong>Below:</strong> for all adjacent nodes/vertices not in
//     *    shortest path set, compare the weights L(u) + w(u,v) &#60; L(v)}
//     *
//     *     for all {v} vertices adjacent to u or not in S;
//     *         if L(u) + w(u, v) &#60; L(v)
//     *         then L(v) := L(u) + w(u, v);
//     *
//     *         {this adds a vertex to S with minimal label and
//     *         updates the labels of vertices not in S}
//     *
//     *         L := L U {v}    {add minimal node to list L(v)}
//     *
//     * {<strong>Below:</strong> this should return back up a list of nodes leading
//     * to the root; the list represents the shortest path from
//     * the start to destination node}
//     *
//     * return S(z) {S(z) = list of shortest path from a to z}
//     *
//     * </pre>
//     *
//     * @param graph the graph or digraph
//     * @param distance the distance of the node from origin/start node
//     * @param startNode the start node
//     * @param destinationNode the destination node
//     * @return a sorted {@link List} of {@link Node} representing the
//     * shortest path from start node to destination node z.
//     * @see Node
//     */
//    public List<Node<Object,Object,Integer>> calculateShortestPath(
//            Graph<Node<Object,Object,Integer>> graph,
//            Distance<Object> distance,
//            Node<Object,Object,Integer> startNode,
//            Node<Object,Object,Integer> destinationNode)
//    {
//        log.info("\nInitialization......\nStart Node: {}\n",startNode);
//
//        // create two lists, undiscovered/unsettled nodes and discovered/settled nodes;
//        // contains all unexplored nodes
//        List<Edge<Distance<Object>, Node<Object,Object,Integer>>> frontier = new LinkedList<>();
//        // contains the path explored to the destination node
//        List<Node<Object,Object,Integer>> shortestPath = new LinkedList<>();
//        // initialize all nodes distance to +Infinity;
//        // and start node distance to 0
//        if ( distance.getValue() == 0)
//        {
//            // Initialization of all nodes with distance "infinite";
//            graph.getNodes().forEach(
//                     gNode -> gNode.setDistance(new Distance<>( Integer.MAX_VALUE))
//            );
//
//            // initialization of the starting node with 0
//            startNode.setDistance(distance);
//        }
//
//        // add the start node to unsettled node list
//        frontier.add(
//                new Edge<>(
//                        distance,
//                        startNode
//                )
//        );
//
//        // get the node on with the smallest/minimum distance
//        Edge<Distance<Object>,Node<Object,Object,Integer>> parentNodeEdge = getMinimumDistanceNodeEdge(frontier);
//
//        // for each neighbor adjacent node of the minimum distance node, where n has not yet been
//        // removed from unsettled nodes do;
//        for (Edge<Distance<Object>, Node<Object,Object,Integer>> childNodeEdge : parentNodeEdge.getNode().getEdges())
//        {
//
//            // temp container for nodes discovered on the frontier aka settled nodes
//            List<Node<Object,Object,Integer>> tempShortestPath = new LinkedList<>();
//
//            // node adjacent to start node
//            Node<Object,Object,Integer> adjacentNode = childNodeEdge.getNode();
//
//            // edge distance = dist[minimum dist node] + dist_between([minimum dist node] , [evaluation node])
//            Integer edgeWeight = parentNodeEdge.getDistance().getValue() + childNodeEdge.getDistance().getValue();
//
//            // remove minimal node from the undiscovered frontier/unsettled nodes list
//            frontier.remove(parentNodeEdge);
//
//            log.info("{\n\tCurrent Minimal Node: {} \n\n\tAdjacent Node: {}" +
//                            "\n\n\tDistance from: {} -> {} := {} \n\n\tUnsettled Nodes: {} " +
//                            "\n\n\tSettled Nodes: {} \n}",
//                    parentNodeEdge,childNodeEdge,parentNodeEdge.getNode().getName(),
//                    adjacentNode.getName(),edgeWeight,frontier,shortestPath);
//
//            // update adjacent node distance, if edgeDistance < adjacentNode distance;
//            if (edgeWeight < adjacentNode.getDistance().getValue())
//            {
//                // update adjacentNode distance = edgeDistance;
//                adjacentNode.setDistance(new Distance<>(edgeWeight));
//
//                log.info("\nUpdated Adjacent Node: {}\n", childNodeEdge);
//
//                // add the adjacent node to the undiscovered frontier/unsettled nodes list for later processing
//                frontier.add(childNodeEdge);
//                // if destination node not in discovered/settled node initiate recursive call
//                if (!shortestPath.contains(destinationNode))
//                {
//                    // continue as long as we have undiscovered nodes on the frontier
//                    // add unexplored node to settledNode list
//                    tempShortestPath.addAll(calculateShortestPath(
//                            graph,
//                            adjacentNode.getDistance(),
//                            adjacentNode,
//                            destinationNode)
//                    );
//                }
//            }
//            // remove any undesired paths with accumulated distance greater than the
//            // accumulated distance of the destination node
//            if (tempShortestPath.contains(destinationNode))
//            {
//                // add the cleansed temp list to the settled node
//                shortestPath.addAll(tempShortestPath);
//            }
//        }
//
//        // our look back or back up; add previous node to discovered list of settled nodes;
//        shortestPath.addAll(List.of(parentNodeEdge.getNode()));
//
//        log.info("\nReturned Settled Nodes: {}\n",
//                shortestPath);
//
//        return shortestPath;
//    }
//
//    /**
//     * This evaluation function provides a means by which to find the edge with the
//     * minimum distance node.
//     * @param nodes the list of node.
//     * @return the node {@link Edge}<{@link Distance , Node}>with the lowest weight/cost edge.
//     * @see Distance
//     * @see Node
//     */
//    public Edge<Distance<Object>, Node<Object,Object,Integer>> getMinimumDistanceNodeEdge(
//            List<Edge<Distance<Object>, Node<Object,Object,Integer>>> nodes){
//        //////////////////////////////////////////////////////////////////
//        Node<Object,Object,Integer> minimumDistanceNode = null;
//        Distance<Object> minimumDistance = new Distance<>(Integer.MAX_VALUE);
//        // get the minimal distance node
//        for (Edge<Distance<Object>, Node<Object,Object,Integer>> n : nodes)
//        {
//            Integer nodeDistance = n.getDistance().getValue();
//            if( nodeDistance < minimumDistance.getValue() )
//            {
//                minimumDistance = n.getDistance();
//                minimumDistanceNode = n.getNode();
//            }
//        }
//        log.info("\nGet Minimum Node: {} \nMinimum Distance: {} \n" +
//                        "\nUnsettled Nodes: {} \n",
//                minimumDistance, minimumDistanceNode, nodes );
//
//        return new Edge<>(minimumDistance,minimumDistanceNode);
//    }

}
/////////////////////////////////////////////////////////////////////