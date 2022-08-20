package Dijkstra;

import Node.Graph.Graph;
import Node.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public abstract class DijkstraInterface<Name,Data,Metric> {

    private static final Logger log = LoggerFactory.getLogger(DijkstraInterface.class);

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
    public List<Node<Name,Data,Metric>> calculateShortestPath(
            Graph<Node<Name,Data,Metric>> graph,
            Distance<Metric> distance,
            Node<Name,Data,Metric> startNode,
            Node<Name,Data,Metric> destinationNode)
    {
        log.info("\nInitialization......\nStart Node: {}\n",startNode);

        // create two lists, undiscovered/unsettled nodes and discovered/settled nodes;
        // contains all unexplored nodes
        List<Edge<Distance<Metric>, Node<Name,Data,Metric>>> frontier = new LinkedList<>();
        // contains the path explored to the destination node
        List<Node<Name,Data,Metric>> shortestPath = new LinkedList<>();

        // initialize all nodes distance to +Infinity;
        // and start node distance to 0 or some method
        graph = initialize(graph, startNode, distance);
        // add the start node to unsettled node list
        frontier.add(
                new Edge<>(
                        distance,
                        startNode
                )
        );

        // get the node on with the smallest/minimum distance
        Edge<Distance<Metric>,Node<Name,Data,Metric>> parentNodeEdge = getMinimumDistanceNodeEdge(frontier);

        // for each neighbor adjacent node of the minimum distance node, where n has not yet been
        // removed from unsettled nodes do;
        for (Edge<Distance<Metric>, Node<Name,Data,Metric>> childNodeEdge : parentNodeEdge.getNode().getEdges())
        {

            // temp container for nodes discovered on the frontier aka settled nodes
            List<Node<Name,Data,Metric>> tempShortestPath = new LinkedList<>();

            // node adjacent to start node
            Node<Name,Data,Metric> adjacentNode = childNodeEdge.getNode();

            // remove minimal node from the undiscovered frontier/unsettled nodes list
            frontier.remove(parentNodeEdge);

            // update adjacent node distance, if edgeDistance < adjacentNode distance;
            // update adjacentNode distance = edgeDistance;
            if (evaluateGoal(parentNodeEdge,childNodeEdge))
            {

                log.info("\nUpdated Node: {}\n", childNodeEdge);

                // add the adjacent node to the undiscovered frontier/unsettled nodes list for later processing
                frontier.add(childNodeEdge);
                // if destination node not in discovered/settled node initiate recursive call
                if (!shortestPath.contains(destinationNode))
                {
                    // continue as long as we have undiscovered nodes on the frontier
                    // add unexplored node to settledNode list
                    tempShortestPath.addAll(calculateShortestPath(
                            graph,
                            adjacentNode.getDistance(),
                            adjacentNode,
                            destinationNode)
                    );
                }
            }
            // remove any undesired paths with accumulated distance greater than the
            // accumulated distance of the destination node
            if (tempShortestPath.contains(destinationNode))
            {
                // add the cleansed temp list to the settled node
                shortestPath.addAll(tempShortestPath);
            }
        }

        // our look back or back up; add previous node to discovered list of settled nodes;
        shortestPath.addAll(List.of(parentNodeEdge.getNode()));

        log.info("\nReturned Settled Nodes: {}\n",
                shortestPath);

        return shortestPath;
    }
    
    
    /**
     * This function allows you to initialize your evaluation parameter to fit
     * the problem domain. The start node on the other hand must be settled and
     * omitted from the initialization process.
     * @param graph the graph
     * @param startNode the start node
     * @param distance any desired metric system
     * @return the initialized graph
     */
    public abstract Graph<Node<Name,Data,Metric>> initialize(
            Graph<Node<Name,Data,Metric>> graph,
            Node<Name,Data,Metric> startNode,
            Distance<Metric> distance);

    /**
     * This evaluation function provides a means by which to find the edge with the
     * minimum distance node.
     * @param nodes the list of node.
     * @return the node {@link Edge}<{@link Distance ,Node}>with the lowest weight/cost edge.
     * @see Distance
     * @see Node
     */
    public abstract Edge<Distance<Metric>, Node<Name,Data,Metric>> getMinimumDistanceNodeEdge(List<Edge<Distance<Metric>, Node<Name,Data,Metric>>> nodes);

    /**
     * You must evaluate the parent and child node and evaluate if a goal
     * state has been reached and return the results of this analysis. This
     * allows for flexibility and customization based on the node contents.
     * @param parentNodeEdge parent node
     * @param childNodeEdge child node
     * @return true if goal state reached, false otherwise
     */
    public abstract boolean evaluateGoal(
            Edge<Distance<Metric>, Node<Name,Data,Metric>> parentNodeEdge,
            Edge<Distance<Metric>, Node<Name,Data,Metric>> childNodeEdge);



}
