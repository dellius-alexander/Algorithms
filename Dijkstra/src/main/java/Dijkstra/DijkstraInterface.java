package Dijkstra;

import Graph.*;
import Node.*;

import java.io.Serializable;
import java.util.List;


/**
 * The Dijkstra Interface allows for customizable initialization and cost
 * evaluation of node traversal across edges between nodes.
 * @param <Id> a unique identifier or object identifier
 * @param <Data> the object stored by each node
 * @param <Metric> the system of measurement used to evaluate the cost|weight|distance of
 *                node traversal across edges between nodes.
 */
public interface DijkstraInterface<Id,Data,Metric> extends Serializable {

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
    List<Node<Id,Data,Metric>> calculateShortestPath(
            Graph<Node<Id,Data,Metric>> graph,
            Distance<Metric> distance,
            Node<Id,Data,Metric> startNode,
            Node<Id,Data,Metric> destinationNode);
    /**
     * Initialize each node metric parameter to fit the problem domain. The
     * start node on the other hand must be settled and omitted from the
     * initialization process.
     * @param graph the graph
     * @param startNode the start node
     * @param distance the initial starting distance
     * @return the initialized graph
     */
    Graph<Node<Id,Data,Metric>> initialize(
            Graph<Node<Id,Data,Metric>> graph,
            Node<Id,Data,Metric> startNode,
            Distance<Metric> distance);

    /**
     * Find the node edge with the lowest cost.
     * @param nodes the list of node.
     * @return the node {@link Edge}<{@link Distance ,Node}>with the lowest
     *          weight/cost edge.
     * @see Distance
     * @see Node
     */
    Edge<Distance<Metric>, Node<Id,Data,Metric>> getMinimumCostNodeEdge(
            List<Edge<Distance<Metric>, Node<Id,Data,Metric>>> nodes);

    /**
     * You must evaluate the parent and child node and determine if a goal
     * state has been achieved and return the results of this analysis. This
     * allows for flexibility and customization based on the node metrics
     * and contents.
     * @param parentNodeEdge parent node
     * @param childNodeEdge child node
     * @return true if goal state reached, false otherwise
     */
    boolean evaluateGoal(
            Edge<Distance<Metric>, Node<Id,Data,Metric>> parentNodeEdge,
            Edge<Distance<Metric>, Node<Id,Data,Metric>> childNodeEdge);



}
