package BreadthFirstSearch;


//import Dijkstra.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Graph.Graph;
import Node.*;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;


public class BreadthFirstSearch {
    private final static Logger log = LoggerFactory.getLogger(BreadthFirstSearch.class);

    /**
     * In a breadth-first search algorithm we start at some node and traverse a directed
     * graph ending at some destination node. At some point during the course of a traversal,
     * every node in the graph changes state from undiscovered to discovered. In a
     * breadth-first search of an undirected graph, we assign a direction to each edge,
     * from the discoverer u to the discovered v. We thus denote u to be the parent of
     * v. Since each node has exactly one parent, except for the root, this defines a
     * tree on the vertices of the graph.
     * <pre>
     * BFS(G, s)
     *      for each vertex u∈V[G]−{s} do
     *          state[u] = “undiscovered”
     *          p[u] = nil, i.e. no parent is in the BFS tree
     *      state[s] = “discovered”
     *      p[s] = nil
     *      Q = {s}
     *      while Q ̸= ∅ do
     *          u = dequeue[Q]
     *          process vertex u as desired
     *          for each v ∈ Adj[u] do
     *              process edge (u,v) as desired
     *              if state[v] = “undiscovered” then
     *              state[v] = “discovered” p[v] = u
     *              enqueue[Q, v]
     *          state[u] = “processed”
     * </pre>
     *
     * @param graph
     * @param distance
     * @param startNode
     * @param destinationNode
     * @return
     */
    public Queue<Node<String,String,Integer>> breadthFirstSearch(
            Graph<Node<String,String,Integer>> graph,
            Distance<Integer> distance,
            Node<String,String,Integer> startNode,
            Node<String,String,Integer> destinationNode
    ){
        log.info("\nInitialization......\nStart Node: {}\n",startNode);

        // create two lists, undiscovered/unsettled nodes and discovered/settled nodes;
        // contains all unexplored nodes
        Queue<Edge<Distance<Integer>, Node<String,String,Integer>>> deQueue = new ArrayDeque<>();
        // contains the path explored to the destination node
        Queue<Node<String,String,Integer>> enQueue = new ArrayDeque<>();
        // initialize all nodes distance to +Infinity;
        // and start node distance to 0
        if (distance.getValue() == 0)
        {
            // Initialization of all nodes with distance "infinite";
            graph.getNodes().forEach(
                    gNode -> {
                        Distance<Integer> d = new Distance<>( Integer.MAX_VALUE);
                        gNode.setDistance(d);
                    }
            );

            // initialization of the starting node with 0
            startNode.setDistance(distance);
        }

        // add the start node to unsettled node list
        deQueue.add(
                new Edge<>(
                        distance,
                        startNode
                )
        );
        // for each neighbor adjacent node of the minimum distance node, where n has not yet been
        // removed from unsettled nodes do;
        while (deQueue.size() != 0) {
            // get the node on with the smallest/minimum distance
            Edge<Distance<Integer>, Node<String,String,Integer>> parentEdge = deQueue.poll();

            Node<String,String,Integer> parent = parentEdge.getNode();
            Distance<Integer> edgeWeightParent = parentEdge.getDistance();

            // for each neighbor adjacent node of the minimum distance node, where n has not yet been
            // removed from unsettled nodes do;
            for (Edge<Distance<Integer>, Node<String,String,Integer>> childEdge : parentEdge.getNode().getEdges())
            {
                Node<String,String,Integer> child = childEdge.getNode();
                Distance<Integer> edgeWeightChild = childEdge.getDistance();
                Integer pathWeight = edgeWeightParent.getValue() + edgeWeightChild.getValue();
                if (pathWeight < child.getDistance().getValue()){
                    child.setDistance( new Distance<>( pathWeight ) );
                    if (deQueue.contains( childEdge )){
                        enQueue.add( child );
                    }
                }
            }
            enQueue.add( parent );
        }
        return enQueue;
    }


//    /**
//     * Retrieve the edge with the minimum distance node.
//     * @param nodes the list of node.
//     * @return the node {@link Edge}<{@link Distance,Node}>with the lowest weight/cost edge.
//     * @see Distance
//     * @see Node
//     */
//    private Edge<Distance<T>, Node<T,T,T>> getMinimumDistanceNodeEdge(
//            Queue<Edge<Distance<T>, Node<T,T,T>>> nodes){
//        //////////////////////////////////////////////////////////////////
//        Node<T,T,T> minimumDistanceNode = null;
//        Distance<T> minimumDistance = (Distance<T>) new Distance<>(Integer.MAX_VALUE);
//        // get the minimal distance node
//        for (Edge<Distance<T>, Node<T,T,T>> n : nodes)
//        {
//            Integer nodeDistance = (Integer) n.getDistance().getValue();
//            if( nodeDistance < (Integer) minimumDistance.getValue() )
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


    public static void main(String[] args)
    {
        try {
            Long startTime = System.currentTimeMillis();
            Node<String,String,Integer> nodeA = new Node<String,String,Integer>("A", "AA");
            Node<String,String,Integer> nodeB = new Node<String,String,Integer>("B", "BB");
            Node<String,String,Integer> nodeC = new Node<String,String,Integer>("C", "CC");
            Node<String,String,Integer> nodeD = new Node<String,String,Integer>("D", "DD");
            Node<String,String,Integer> nodeE = new Node<String,String,Integer>("E", "EE");
            Node<String,String,Integer> nodeF = new Node<String,String,Integer>("F", "FF");
            Node<String,String,Integer> nodeG = new Node<String,String,Integer>("G", "GG");
            Node<String,String,Integer> nodeH = new Node<String,String,Integer>("H", "HH");
            Node<String,String,Integer> nodeI = new Node<String,String,Integer>("I", "II");
            Node<String,String,Integer> nodeJ = new Node<String,String,Integer>("J", "JJ");
            Node<String,String,Integer> nodeK = new Node<String,String,Integer>("K", "KK");
            Node<String,String,Integer> nodeL = new Node<String,String,Integer>("L", "LL");

            nodeA.setCoordinate( new Coordinate<>(1,1));
            nodeB.setCoordinate(new Coordinate<>(1,2));
            nodeC.setCoordinate(new Coordinate<>(1,3));
            nodeD.setCoordinate(new Coordinate<>(1,4));
            nodeC.setCoordinate(new Coordinate<>(2,1));
            nodeE.setCoordinate(new Coordinate<>(2,2));
            nodeF.setCoordinate(new Coordinate<>(2,3));
            nodeG.setCoordinate(new Coordinate<>(2,4));
            nodeH.setCoordinate(new Coordinate<>(3,1));
            nodeI.setCoordinate(new Coordinate<>(3,2));
            nodeJ.setCoordinate(new Coordinate<>(3,3));
            nodeK.setCoordinate(new Coordinate<>(3,4));
            nodeL.setCoordinate(new Coordinate<>(4,1));


            nodeA.addAdjacentNode(nodeB, new Distance<>(1));
            nodeA.addAdjacentNode(nodeC, new Distance<>(1));
            nodeB.addAdjacentNode(nodeD, new Distance<>(1));
            nodeB.addAdjacentNode(nodeC, new Distance<>(1));
            nodeC.addAdjacentNode(nodeA, new Distance<>(1));
            nodeD.addAdjacentNode(nodeA, new Distance<>(1));
            nodeD.addAdjacentNode(nodeG, new Distance<>(1));
            nodeF.addAdjacentNode(nodeE, new Distance<>(1));
            nodeF.addAdjacentNode(nodeI, new Distance<>(1));
            nodeG.addAdjacentNode(nodeD, new Distance<>(1));
            nodeG.addAdjacentNode(nodeJ, new Distance<>(1));
            nodeH.addAdjacentNode(nodeL, new Distance<>(1));
            nodeH.addAdjacentNode(nodeK, new Distance<>(1));
            nodeI.addAdjacentNode(nodeG, new Distance<>(1));
            nodeI.addAdjacentNode(nodeK, new Distance<>(1));
            nodeJ.addAdjacentNode(nodeH, new Distance<>(2));
            nodeJ.addAdjacentNode(nodeI, new Distance<>(4));
            nodeK.addAdjacentNode(nodeH, new Distance<>(1));
            nodeK.addAdjacentNode(nodeB, new Distance<>(1));
            nodeL.addAdjacentNode(nodeJ, new Distance<>(5));
            nodeL.addAdjacentNode(nodeD, new Distance<>(5));

            Graph<Node<String,String,Integer>> graph = new Graph<Node<String,String,Integer>>();

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
            BreadthFirstSearch bfs = new BreadthFirstSearch();
            Queue<Node<String,String,Integer>> results0 =  bfs
                    .breadthFirstSearch(
                            graph,
                            new Distance<>(0),
                            nodeA,
                            nodeL);

            nodeA.setShortestPath( results0.stream().collect(Collectors.toList()) );
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
