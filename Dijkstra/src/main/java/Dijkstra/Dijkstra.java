/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Algorithms  Copyright (C) 2022  Dellius Alexander
 *
 * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions; type `show c' for details.
 */
/////////////////////////////////////////////////////////////////////
package Dijkstra;
/////////////////////////////////////////////////////////////////////
import Graph.Graph;
import Node.*;
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
 *
 * - The node label
 * - The cost of the shortest path to that node (from the start node)
 * - The label of the previous node in the path
 *
 *
 * Using the information in this list you can backtrack through the
 * previous nodes back to the start node. This will give you the
 * shortest path (sequence of visited nodes) from the start node to
 * each node and the cost of each path. This can be seen in the
 * worked example that follows.
 * @since 2022-08-6
 * </pre>
 */
public class Dijkstra extends DijkstraAbstract<Object,Object,Object> {
    ///////////////////////////////////////////////////////////////////////
    private static final Logger log = LoggerFactory.getLogger(Dijkstra.class);
    ///////////////////////////////////////////////////////////////////////
    /**
     * Initialize each node metric parameter to fit the problem domain. The
     * start node on the other hand must be settled and omitted from the
     * initialization process.
     * @param graph the graph
     * @param startNode the start node
     * @param distance the initial starting distance
     * @return the initialized graph
     */
    public Graph<Node<Object,Object,Object>> initialize(
            Graph<Node<Object,Object,Object>> graph,
            Node<Object,Object,Object> startNode,
            Distance<Object> distance
    ) {
        if ((Integer) distance.getValue() ==  0)
        {
            startNode.setDistance(new Distance<>( (Object) 0));
            for (Node<Object,Object,Object> node : graph.getNodes())
            {
                if (!node.equals(startNode)) {
                    node.setDistance(new Distance<>( (Object) Integer.MAX_VALUE));
                }
            }
        }
        return graph;
    }
    ///////////////////////////////////////////////////////////////////////
    /**
     * Find the node edge with the lowest cost.
     * @param nodes the list of node.
     * @return the node {@link Edge} with the lowest
     *          weight/cost edge.
     * @see Distance
     * @see Node
     */
    public Edge<Distance<Object>,Node<Object,Object,Object>> getMinimumCostNodeEdge(
            List<Edge<Distance<Object>,Node<Object,Object,Object>>> nodes
    ){
        //////////////////////////////////////////////////////////////////
        Node<Object,Object,Object> minimumDistanceNode = null;
        Distance<Object> minimumDistance = new Distance<>( (Object) Integer.MAX_VALUE);
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
    ///////////////////////////////////////////////////////////////////////
    /**
     * You must evaluate the parent and child node and determine if a goal
     * state has been achieved and return the results of this analysis. This
     * allows for flexibility and customization based on the node metrics
     * and contents.
     * @param parentNodeEdge parent node
     * @param childNodeEdge child node
     * @return true if goal state reached, false otherwise
     */
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
            adjacentNode.setDistance(new Distance<>( (Object) edgeWeight));
            log.info("\nUpdated Adjacent Node: {}\n", childNodeEdge);
            return true;
        }
        return false;
    }
}
/////////////////////////////////////////////////////////////////////