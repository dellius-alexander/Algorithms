package Node;

import lombok.NonNull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
 * <pre>
 * Nodes (also called vertices) are represented in the diagram by labeled
 * coordinates, name or id. Each node will store data relevant to the scenario. For example,
 * if you use a graph to store data for a map, each node might represent a
 * city and could store the city name. If you use a graph to store data about
 * a local area network, each node might represent a network device, and could
 * store the IP address and physical location of the device.
 * </pre>
 * @param <Data> the data stored in the node
 */
public class Node<Name, Data, Metric> implements Comparable<Node<Name, Data, Metric>> {
    /**
     * The identifier of this node
     */
    private Name name;
    /**
     * The data stored by this node
     */
    private Data data;
    /**
     * The {@link Coordinate} of this node
     */
    private Coordinate<Metric, Metric> coordinate;
    /**
     * The shortest path
     */
    private List<Node<Name,Data, Metric>> shortestPath;
    /**
     * The distance of this node from an adjacent edge
     */
    private Distance<Metric> distance;
    /**
     * List of edges
     */
    private List<Edge<Distance<Metric>, Node<Name,Data, Metric>>> edges;

    /**
     * A node
     * @param name node name or id
     */
    public Node(Name name){
        this.name = name;
        this.data = null;
        this.coordinate = new Coordinate<Metric, Metric>();
        this.shortestPath = new LinkedList<>();
        this.distance = new Distance<Metric>();
        this.edges = new LinkedList<>();
    }

    /**
     * A node
     * @param name node name or id
     * @param data data stored in this node
     */
    public Node(Name name, Data data)
    {
        this.name = name;
        this.data = data;
        this.coordinate = new Coordinate<Metric, Metric>();
        this.shortestPath = new LinkedList<>();
        this.distance = new Distance<Metric>();
        this.edges = new LinkedList<>();
    }

    /**
     * A node
     * @param name node name or id
     * @param data data stored in this node
     * @param coordinate the coordinates of this node
     */
    public Node(Name name, Data data, Coordinate<Metric, Metric> coordinate)
    {
        this.name = name;
        this.data = data;
        this.coordinate = coordinate;
    }

    /**
     * Add an adjacent node to the list of adjacent node
     * @param destination the adjacent node
     * @param distance the distance or cost to get to this node
     */
    public void addAdjacentNode(Node<Name,Data, Metric> destination, Distance<Metric> distance) {
        this.edges.add( new Edge<>( distance,destination)); }


    /**
     * Set the distance or cost to access this node
     * @param distance the {@link Distance}
     */
    public void setDistance(Distance<Metric> distance) {
        this.distance =  distance;
    }

    /**
     * Get the distance to access this node
     * @return the {@link Distance}
     */

    public Distance<Metric> getDistance() { return this.distance; }

    /**
     * Get the node name
     * @return the node name
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Get the node data
     * @return the node data
     */
    public Data getData() {
        return this.data;
    }

    /**
     * Get the node coordinates
     * @return the node coordinates
     */
    public Coordinate<Metric, Metric> getCoordinate() {
        return this.coordinate;
    }

    /**
     * Get the shortest path to last destination
     * @return the shortest path to most recent or
     * some destination node
     */
    public List<Node<Name,Data, Metric>> getShortestPath() {
        return this.shortestPath;
    }

    /**
     * Get the list of connected/adjacent edges to this node
     * @return the list of connected/adjacent edges to this node
     */
    public List<Edge<Distance<Metric>, Node<Name,Data, Metric>>> getEdges() {
        return this.edges;
    }

    /**
     * Set the node name
     * @param name the node name
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Set the data of this node
     * @param data the data of this node
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Set the coordinate of this node
     * @param coordinate the coordinate of this node
     */
    public void setCoordinate(Coordinate<Metric, Metric> coordinate) {
        this.coordinate = coordinate;
    }

//    /**
//     * Set the coordinate of this node
//     * @param coordinate the coordinate of this node
//     */
//    public void setCoordinate(Coordinate<Integer,Integer> coordinate) {
//        this.coordinate = (Coordinate<Metrix,Metrix>) coordinate;
//    }

    /**
     * Set the shortest path to most recent node
     * @param shortestPath the shortest path to most recent node
     */
    public void setShortestPath(List<Node<Name,Data, Metric>> shortestPath) {
        this.shortestPath =  shortestPath;
    }
    /**
     * Set the adjacent node edges
     * @param edges the adjacent node edges
     */
    public void setEdges(List<Edge<Distance<Metric>, Node<Name,Data, Metric>>> edges) {
        this.edges = edges;
    }

    /**
     * If object o is equal to this class object.
     * @param o the object to compare
     * @return true if equal otherwise false
     */
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Node)) return false;
        final Node<?,?,?> other = (Node<?,?,?>) o;
        if (!other.canEqual(( this))) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (!Objects.equals(this$name, other$name)) return false;
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        if (!Objects.equals(this$data, other$data)) return false;
        final Object this$coordinate = this.getCoordinate();
        final Object other$coordinate = other.getCoordinate();
        if (!Objects.equals(this$coordinate, other$coordinate))
            return false;
        final Object this$shortestPath = this.getShortestPath();
        final Object other$shortestPath = other.getShortestPath();
        if (!Objects.equals(this$shortestPath, other$shortestPath))
            return false;
        final Object this$distance = this.getDistance();
        final Object other$distance = other.getDistance();
        if (!Objects.equals(this$distance, other$distance)) return false;
        final Object this$edges = this.getEdges();
        final Object other$edges = other.getEdges();
        return Objects.equals(this$edges, other$edges);
    }

    /**
     * Is this other object an instance of this object.
     * @param other the object to compare
     * @return true if an instance of this object, false otherwise
     */
    protected boolean canEqual(final Object other) {
        return other instanceof Node;
    }

    /**
     * Get the hashcode of this class
     * @return the hashcode
     */
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        final Object $coordinate = this.getCoordinate();
        result = result * PRIME + ($coordinate == null ? 43 : $coordinate.hashCode());
        final Object $shortestPath = this.getShortestPath();
        result = result * PRIME + ($shortestPath == null ? 43 : $shortestPath.hashCode());
        final Object $distance = this.getDistance();
        result = result * PRIME + ($distance == null ? 43 : $distance.hashCode());
        final Object $edges = this.getEdges().stream();
        result = result * PRIME + ($edges == null ? 43 : $edges.hashCode());
        return result;
    }
    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(@NonNull Node o) {
        return o.compareTo(this);
    }

    /**
     * To string
     * @return the contents of this object
     */
    @Override

    public String toString() {
        return  "Node{" +
                "\n\t\"name\":" + getName() +
                ",\n\t\"data\":" + getData() +
                ",\n\t\"coordinate\":" + getCoordinate() +
                ",\n\t\"shortestPath\":" + getShortestPath().size() +
                ",\n\t\"distance\":" + getDistance() +
                ",\n\t\"edges\":\"" + getEdges().size() +
                "\n\t}";

    }


}