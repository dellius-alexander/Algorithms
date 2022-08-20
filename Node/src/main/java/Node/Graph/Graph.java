package Node.Graph;

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
 * @param <Data> the node type parameter for this graph
 */
public class Graph<Data> {
    /**
     * Set of nodes
     */
    private Set<Data> nodes =  new HashSet<>();
    /**
     * Map of the shortest paths
     */
    private Map<Data, List<Data>> mapOfShortestPaths = new TreeMap<>();


    /**
     * Graph of nodes
     * @param nodes set of nodes
     */
    public Graph(Set<Data> nodes) {
        this.nodes = nodes;
    }

    /**
     * Add list of nodes to this graphs
     * @param nodes the list of nodes
     */
    public Graph(List<Data> nodes) {
        this.nodes.addAll(nodes);
    }

    /**
     * Maintain a Map of the shortest paths related to all nodes in the graph
     * @param mapOfShortestPaths a Map of the shortest paths related to all nodes in the graph
     */
    public Graph(Map<Data, List<Data>> mapOfShortestPaths){ this.mapOfShortestPaths = mapOfShortestPaths; }

    /**
     * A graph of nodes
     */
    public Graph() {}

    /**
     * Add a node to the graph
     * @param node the node to add
     */
    public void addNode(Data node) { this.nodes.add(node); }

    /**
     * Adds a list of nodes to the graph
     * @param nodes the node to add
     */
    public void addNodes(List<Data> nodes) { this.nodes.addAll(nodes); }
    /**
     * Get the Map of the shortest paths related to all nodes in the graph
     * @return a Map of the shortest paths related to all nodes in the graph
     */
    public Map<Data, List<Data>> getMapOfShortestPaths() { return mapOfShortestPaths; }
    /**
     * Set a Map of the shortest paths related to all nodes in the graph
     * @param mapOfShortestPaths a Map of the shortest paths related to all nodes in the graph
     */
    public void setMapOfShortestPaths(Map<Data, List<Data>> mapOfShortestPaths) { this.mapOfShortestPaths = mapOfShortestPaths; }

    /**
     * Get the set of nodes in the graph
     * @return the set of nodes in the graph
     */
    public Set<Data> getNodes() {
        return this.nodes;
    }

    /**
     * Add a list of nodes to the graph
     * @param nodes the list of nodes to the graph
     */
    public void setNodes(Set<Data> nodes) {
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