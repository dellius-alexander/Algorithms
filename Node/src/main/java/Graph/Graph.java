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
package Graph;
/////////////////////////////////////////////////////////////////////
import java.util.*;
/////////////////////////////////////////////////////////////////////
/**
 * <pre>
 * Graphs are discrete structures consisting of vertices and
 * edges that connect these vertices. There are different
 * kinds of graphs, depending on whether  edges have
 * directions, whether multiple edges can connect the same
 * pair of vertices, and whether loops are allowed. Problems
 * in almost every conceivable discipline can be solved using
 * graph models.
 * </pre>
 * @param <Node> the node type parameter for this graph
 */
public class Graph<Node> {
    /**
     * Set of nodes
     */
    private Set<Node> nodes =  new HashSet<>();
    /**
     * Map of the shortest paths
     */
    private Map<Node, List<Node>> mapOfShortestPaths = new HashMap<>();

    /**
     * Graph of nodes
     * @param nodes set of nodes
     */
    public Graph(Set<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * Add list of nodes to this graphs
     * @param nodes the list of nodes
     */
    public Graph(List<Node> nodes) {
        this.nodes.addAll(nodes);
    }

    /**
     * Maintain a Map of the shortest paths related to all nodes in the graph
     * @param mapOfShortestPaths a Map of the shortest paths related to all nodes in the graph
     */
    public Graph(Map<Node, List<Node>> mapOfShortestPaths){ this.mapOfShortestPaths = mapOfShortestPaths; }

    /**
     * A graph of nodes
     */
    public Graph() {}

    /**
     * Add a node to the graph
     * @param node the node to add
     */
    public void addNode(Node node)
    {
//        mapOfShortestPaths.put(node, Dijkstra.)
        this.nodes.add(node); }

    /**
     * Adds a list of nodes to the graph
     * @param nodes the node to add
     */
    public void addNodes(List<Node> nodes) { this.nodes.addAll(nodes); }
    /**
     * Get the Map of the shortest paths related to all nodes in the graph
     * @return a Map of the shortest paths related to all nodes in the graph
     */
    public Map<Node, List<Node>> getMapOfShortestPaths() { return mapOfShortestPaths; }
    /**
     * Set a Map of the shortest paths related to all nodes in the graph
     * @param mapOfShortestPaths a Map of the shortest paths related to all nodes in the graph
     */
    public void setMapOfShortestPaths(Map<Node, List<Node>> mapOfShortestPaths) { this.mapOfShortestPaths = mapOfShortestPaths; }

    /**
     * Get the set of nodes in the graph
     * @return the set of nodes in the graph
     */
    public Set<Node> getNodes() {
        return this.nodes;
    }

    /**
     * Add a list of nodes to the graph
     * @param nodes the list of nodes to the graph
     */
    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * If object o is equal to this class object.
     * @param o the object to compare
     * @return true if equal otherwise false
     */
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Graph<?>)) return false;
        final Graph<?> other = (Graph<?>) o;
        if (!other.canEqual(this)) return false;
        final Object this$nodes = this.getNodes();
        final Object other$nodes = other.getNodes();
        return Objects.equals(this$nodes, other$nodes);
    }
    /**
     * Is this other object an instance of this object.
     * @param other the object to compare
     * @return true if an instance of this object, false otherwise
     */
    protected boolean canEqual(final Object other) {
        return other instanceof Graph;
    }
    /**
     * Get the hashcode of this class
     * @return the hashcode
     */
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $nodes = this.getNodes();
        result = result * PRIME + ($nodes == null ? 43 : $nodes.hashCode());
        return result;
    }
    /**
     * To string
     * @return the contents of this object
     */
    public String toString() {
        return "\nGraph{" +
                "\n\t\"nodes\":" + this.getNodes().stream() +
                "\n\t}";
    }

}