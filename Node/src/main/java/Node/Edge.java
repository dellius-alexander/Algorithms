package Node;

import java.util.Objects;
/////////////////////////////////////////////////////////////////////
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
/**
 * The connections between nodes are called edges (sometimes called arcs).
 * In a weighted graph these edges have a weight (or cost) associated with
 * them. In a graph representing a map, the edges could represent a road or
 * rail connection between cities and the weight may represent the time to
 * travel between the cities. In a network graph the edges usually represent
 * the connections between devices such as Ethernet or fiber optic cables.
 * In this case the weight could represent the delay introduced by the connection.
 * @param <D>  the cost of weight of traveling to this node form some
 *                  source node
 * @param <N> the destination node
 */
public class Edge<D, N>
{
    /**
     * The edge distance
     */
    private D distance;
    /**
     * The node connected to this edge
     */
    private N node;

    /**
     * A node edge
     */
    public Edge(){}

    /**
     * A node edge
     * @param distance distance of this edge
     * @param node the node
     */
    public Edge(D distance, N node) {
        this.distance = distance;
        this.node = node;
    }

    /**
     * Get the distance of this edge
     * @return the distance of this edge
     */
    public D getDistance() {
        return this.distance;
    }

    /**
     * Get the node
     * @return the node
     */
    public N getNode() {
        return this.node;
    }

    /**
     * The node distance of this edge
     * @param distance the distance of this edge to node
     */
    public void setDistance(D distance) {
        this.distance = distance;
    }

    /**
     * Set the node
     * @param node the node
     */
    public void setNode(N node) {
        this.node = node;
    }

    /**
     * Get the edges of adjacent nodes
     * @return the edges of adjacent nodes
     */
    public Edge<D, N> getEdge(){
        return this;
    }

    /**
     * If object o is equal to this class object.
     * @param o the object to compare
     * @return true if equal otherwise false
     */
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Edge)) return false;
        final Edge<?, ?> other = (Edge<?, ?>) o;
        if (!other.canEqual( this)) return false;
        final Object this$source = this.getDistance();
        final Object other$source = other.getDistance();
        if (!Objects.equals(this$source, other$source)) return false;
        final Object this$destination = this.getNode();
        final Object other$destination = other.getNode();
        return Objects.equals(this$destination, other$destination);
    }
    /**
     * Is this other object an instance of this object.
     * @param other the object to compare
     * @return true if an instance of this object, false otherwise
     */
    protected boolean canEqual(final Object other) {
        return other instanceof Edge;
    }
    /**
     * Get the hashcode of this class
     * @return the hashcode
     */
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $source = this.getDistance();
        result = result * PRIME + ($source == null ? 43 : $source.hashCode());
        final Object $destination = this.getNode();
        result = result * PRIME + ($destination == null ? 43 : $destination.hashCode());
        return result;
    }
    /**
     * To string
     * @return the contents of this object
     */
    public String toString() {
        return "Edge{\n\t\"distance\":" + this.getDistance() +
                ",\n\t\"destination\":" + this.getNode() +
                "\n\t}";
    }

//    public static void main(String[] args) {
//        Edge<Distance<Integer>,Node<String,String,Integer>> edge =
//                new Edge<Distance<Integer>, Node<String, String, Integer>>(
//                        new Distance<>(9),
//                        new Node<String, String, Integer>("A","AA")
//                );
//
//        System.out.printf("\n%s\n",edge);
//    }
}
