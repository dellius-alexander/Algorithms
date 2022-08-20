package Node;

/////////////////////////////////////////////////////////////////////

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
public class Node<Name, Data, Metric> {
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
     * To string
     * @return the contents of this object
     */
    @Override

    public String toString() {
        return  "Node{" +
                "\n\t\"name\":" + getName() +
                ",\n\t\"data\":" + getData() +
                ",\n\t\"coordinate\":" + getCoordinate() +
//                ",\n\t\"shortestPath\":" + getShortestPath().stream().collect(Collectors.toUnmodifiableList()) +
                ",\n\t\"distance\":" + getDistance().toString() +
//                ",\n\t\"edges\":\"" + getEdges().stream().collect(Collectors.toUnmodifiableList()) +
                "\n}";

    }


}