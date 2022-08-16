package BreadthFirstSearch;


import Dijkstra.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayDeque;
import java.util.Queue;

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
     *                  state[v] = “discovered”
     *                  p[v] = u    {parent node = child node}
     *                  enqueue[Q, v]
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
                        gNode.setDistance(new Distance<>( Integer.MAX_VALUE));
                    }
            );

            // initialization of the starting node with 0
            startNode.setDistance(distance);
        }
        enQueue.add(startNode);

        // add the start node to unsettled node list aka deQueue
        deQueue.add(
                new Edge<>(
                        distance,
                        startNode
                )
        );
        // while deQueue > 0
        while (deQueue.size() > 0)
        {
            // get the node on with the smallest/minimum distance
            Edge<Distance<Integer>, Node<String,String,Integer>> parentNodeEdge = deQueue.poll();
            Distance<Integer> parentNodeWeight = parentNodeEdge.getDistance();
            Queue<Node<String,String,Integer>> tempQueue = new ArrayDeque<>();
            // for each neighbor adjacent node of the minimum distance node, where n has not yet been
            // removed from unsettled nodes do;
            for (Edge<Distance<Integer>, Node<String,String,Integer>> childNodeEdge : parentNodeEdge.getNode().getEdges())
            {

                Node<String,String,Integer> childNode = childNodeEdge.getNode();
                Distance<Integer> childNodeWeight = childNodeEdge.getDistance();
                Integer pathWeight = parentNodeWeight.getValue() + childNodeWeight.getValue();
                log.info("\nParent Edge: {}\nChild Edge: {}\n",parentNodeEdge,childNodeEdge);
                if (childNode.getDistance().getValue() == Integer.MAX_VALUE)
                {
                    childNode.setDistance(new Distance<>(pathWeight));
                    tempQueue.add(childNode);
                    parentNodeEdge = childNodeEdge;
                    deQueue.add(parentNodeEdge);
                }
                if (enQueue.contains(destinationNode)){
                    break;
                }
            }

            enQueue.addAll(tempQueue);

        }
        log.info("\nPath Returned: {}", enQueue);
        return enQueue;
    }

}