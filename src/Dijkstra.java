/** ************************************************************************************************
 * The Dijkstra's algorithm is by far the best shortest path algorithm
 * This program illustrates the algorithm and program control flow of the data structure
 * @author Dellius Alexander
 *
 * ALGORITHM: Dijkstra’s Algorithm
 * (G = Graph: weighted connected simple graph, with all weights positive)
 * {G has vertices a = v_0, v_1,… , v_n = z and lengths w(v_i , v_j )
 *      where w(v_i , v_j ) = ∞ if {v_i , v_j } is not an edge in G}
 * for i := 1 to n L(v_i ) := ∞
 * L(a) := 0
 * S := ∅
 * {the labels are now initialized so that the label of a is 0 and all
 *      other labels are ∞, and S is the empty set}
 * while z ∉ S
 *      u := a vertex not in S with L(u) minimal
 *      S := S ∪ {u}
 *      for all vertices v not in S
 *          if L(u) + w(u, v) < L(v) then L(v) := L(u) + w(u, v)
 *          {this adds a vertex to S with minimal label and updates the
 *          labels of vertices not in S}
 * return L(z) {L(z) = length of a shortest path from a to z}
 * *************************************************************************************************
 * Dijkstra's Algorithm:  Key Ideas
 * We keep track of two sets of nodes.  One set of nodes is settled, the
 * other set is on the frontier.  Terminology varies; these are my recommendations.
 * Once  a  node  is settled,  we'll  know  the  shortest  distance  and
 * best  path  to that node (from the chosen start node).  We'll repeat the
 * following steps:
 * 1.   Choose an appropriate node from the frontier.
 * 2.   Settlethat node, and update distance/path information.
 * 3.   If possible, add new nodes to the frontier.
 * Once the frontier is empty, the algorithm terminates.  If we have a
 * particular ending node in mind, we can stop once that node becomes settled.
 *
 * Step 1:  We begin by settling the start node (at distance 0 from itself)
 *          Other nodes are settled in increasing order of distance (from start/parent node)
 *
 * Step 2:  After each settling, we update the value of the current best weighted
 *          distance to each node, passing through only nodes that are already settled.
 *          In other words, settled nodes function like intermediate nodes from Warshall/Floyd.
 *
 * Step 3:  When updating distances through settled nodes, we'll only need to
 *          consider nodes for which there is an edge from the one that was just settled.
 *          This is the key to entire algorithm.
 *
 * Step 4:  Instead of full paths, we keep a "parent/intermediate" for each node;
 *          tracking back through these "parents/intermediate" nodes gives us the
 *          shortest path from start;
 *
 * *************************************************************************************************
 *
 * DETAILED STEP BY STEP:
 *  ■ EDGE: is defined as a connection between two nodes
 *  ■ Frontier: is defined as any unsettled node that is "a visible edge from a settled
 *      node"
 *      (eg: after settling Node "F" the frontier nodes are now "C & G";
 *      all other unsettled nodes are not considered because they are not on the
 *      frontier of "F")
 *
 *  ■ Settled: a node who's distance is evaluated from a parent/root node
 *  ■ Unsettled: an unevaluated node on the edges of settled nodes;
 *               unsettled nodes are assigned avalue of infinity as
 *               are not currently being evauated
 *  ■ R = Root
 *  ■ Start Node: "F"
 *
 * Note: Create 3 lists:
 *              » Settled : a list of settled nodes
 *              » Unsettled : a list of unsettled nodes
 *              » Edges : a list of all edges
 *
 * Node:          A     B   C   D   E  "F"  G
 * Distance:    | 11 | 10 | 5 | 7 | 5 | 0 | 1 |
 * Parent:      |  B | D  | F | C | G | R | F |
 *
 *  1.  Start at the "ROOT" node; it has no parent so it will be
 *          labeled as Root; the distance from Root to itself is "0"
 *
 *  1.2.For each round of setting any unsettled node gets a value of
 *          infinity until its turn to be evaluated unless is it
 *          part of our edge case evaluation
 *
 *  2.  Pick the closest edge(i,j) path with the shortest distance
 *          or smallest weight; record the distance/weight in the
 *          table and list its parent as the Root node or node
 *          closest to Root node
 *
 *  3.  The next node to be settled is chosen from the frontier;
 *          pick the one with the smallest distance; if their is a tie,
 *          choose arbitrarily; this node is now removed from the
 *          frontier, and added to the set of "settled" nodes, without
 *          changing its distance or parent.
 *  4.  After settling "N" nodes, we check to see if we have any
 *          shortcuts through "N"; we can use "N" as an intermediate
 *          node back to our Root node; in other words, we can only
 *          travel paths through settled nodes only; we need only
 *          consider the nodes that have an edge from "N"
 *  5.  For each edge from "N" to an unsettled node "u", compute the
 *          Set Distance(u): SUM = Distance(N) + edge_weight(u);
 *          Set Parent(u):   to "N"
 *
 *
 *************************************************************************************************/
///////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dijkstra
{
///////////////////////////////////////////////////////////////////////////////////////////////////

    public final static int INF = 99999;
    private static Logger log = Logger.getLogger("Dijkstra");


    /**********************************************************************************************
     * Default Constructor
     *********************************************************************************************/
    public Dijkstra(){}
    /**********************************************************************************************
     * Dijkstra Algorihm
     * @param graph
     * @param startNode
     **********************************************************************************************/
    public void DijkstraAlgorithm(Integer graph[][], Integer startNode) {
        //log.setLevel(Level.INFO);

        /**
         * First we will create the basis of the algorithm
         *  we have to initilaize several values to hold specific
         *  parameters to help us find the shortest path through the graph
         */
        Integer V = graph.length;                           // The total number of nodes in our graph
        Integer count = V;
        Integer minimumValue = 99999;                       // Represents the weight/distance of the shortest edge node
        Integer nextNodeShortestPathToTraverse = 0;         // Represents the node with the next best shortest path
        Integer[][] settledNodes = new Integer[V][V];       // Settled Nodes according to the best shortest path
        Integer[][] unsettledNodes = new Integer[V][V];     // Unsettled Nodes not visible from the frontier
        Integer[] frontier = new Integer[V];          // Nodes on the frontier
        Integer S = startNode - 1;                              // Represents our START NODE
        // i = source node, j = destination node of distance(i,j) | weight(i,j)
        Integer i = 0, j = 0, k = 0;
        Integer N = 0;                                      // Next node to traverse
        Integer val = 0;
        //   {INF, 1, 7, 5},      // 1
        //   {1, INF, INF, 3},    // 2
        //   {7, INF, INF, 2},    // 3
        //   {5, 3, 2, INF}};     // 4

            // initialize everything to INFINITY
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    settledNodes[i][j] = INF;
                    unsettledNodes[i][j] = INF;
                    frontier[j] = INF;
                }
            }
            log.info("\tUnsettled Nodes:=> " + Arrays.deepToString(unsettledNodes));
            log.info("\tSettled Nodes:=> " + Arrays.deepToString(settledNodes));
            log.info("\tFrontier Nodes:=> " + Arrays.deepToString(frontier));
            /**
             * Start the settling process
             */
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    /**
                     * Here we capture all nodes and allow the processing
                     * below to remove nodes from the pool of
                     * unsettled nodes
                     */
                    unsettledNodes[i][j] = graph[i][j];
                    /**
                     * Here is were we settle the current parent or root node
                     */
                    if (i == S) {  // capture all values of the current settling
                        unsettledNodes[i][j] = INF;
                        settledNodes[i][j] = graph[i][j];
                        if (i == S && i == j) {  // granular: Set to infinity when node looking at self
                            settledNodes[i][j] = INF;
                        }
                    }   // End of SETTLING
                    /*
                     * Here we capture the node with the shortest edge in the frontier
                     * of the node currently being settled
                     */
                    if (minimumValue > unsettledNodes[i][j]) {  // Find the next shortest path/edge node
                        minimumValue = graph[i][j];
                        nextNodeShortestPathToTraverse = N = i;
                    }   // End of find our shortest edge

                    if (count == V && i == S && j == 0) {  // keep count of how many times we need to iterate to find the shortest path
                        settledNodes[i][j] = 0;
                    }
                }   // End of FIRST inner for/j
            }   // End of outer for/i


            for (j = 0; j < V; j++){   // We now capture what is on our frontier and
                frontier[j] = graph[N][j];
            }

            System.out.println("\n\n");
            log.info("=>\tUnsettled Node: " + Arrays.deepToString(unsettledNodes));
            log.info("=>\tSettled Node: " + Arrays.deepToString(settledNodes));
            log.info("=>\tFrontier Node: " + Arrays.deepToString(frontier));

            while (count > 0) {
                count--;
                //DijkstraAlgorithm(unsettledNodes,nextNodeShortestPathToTraverse);
            }

    }
    /**
     * Removes a object pair from our hashtable and you provide
     * the key
     * @param nodeToRemove
     * @param frontier
     * @return
     */
    public Hashtable<String, Integer[]>  removeFromFrontier(Integer nodeToRemove, Hashtable<String, Integer[]>  frontier)
    {
        Enumeration el = frontier.keys();
        String key = null;
        Integer[] val = null;
        while (el.hasMoreElements()) {
            key = el.nextElement().toString();
            val = frontier.get(key);
            if(Integer.parseInt(key) == nodeToRemove){
                frontier.remove(key);
            }
            printHash_1D(frontier);
        }
        return frontier;
    }
    /**
     * Removes a object pair from our hashtable and you provide
     * the key
     * @param nodeToRemove
     * @param frontier
     * @return
     */
    public Hashtable<String, Integer>  removeFromFrontier(int nodeToRemove, Hashtable<String, Integer> frontier)
    {
        Enumeration el = frontier.keys();
        String key = null;
        int val = 0;
        while (el.hasMoreElements()) {
            key = el.nextElement().toString();
            val = frontier.get(key);
            if(Integer.parseInt(key) == nodeToRemove){
                frontier.remove(key,val);
            }
            System.out.println(key + " " + val);
        }
        return frontier;
    }

    /**
     * Get the next set of nodes to be added to the frontier
     * @param root
     * @param parentNode
     * @param hash
     * @return
     */
    public Hashtable<String, Integer> nodesOnFrontier(Integer root, Integer parentNode, Hashtable<String, Integer[]> hash)
    {
        Hashtable<String, Integer> frontier = new Hashtable<>();
        int i = 0;
        String key = "";
        Integer[] val = null;
        Enumeration el = hash.keys();
        while (el.hasMoreElements())
        {
            key = el.nextElement().toString();

            if(Integer.parseInt(key) == root)
            {
                hash.remove(key);
            }
            else if(Integer.parseInt(key) == parentNode )
            {
                val = hash.get(key);
                for (int j = 0; j < val.length; j++)
                {
                    if(val[j] != INF && j != root && j != parentNode)
                    {
                        frontier.put((j+1)+"",val[j]);
                    }
                }
            }
        }
        return frontier;
    }
    /**
     * Returns the node with the minimum distance/weight from the
     * frontier
     * @param hash  Hashtable consisting of <String, Integer> pair
     * @return  The node with the minimum valued weight from the frontier
     */
    public Integer nextShortestPath(Hashtable<String, Integer> hash)
    {
        /*
         * 2.  After settling our starting node we need to look around
         *      on the frontier and find the smallest weighted edge.
         *      The node at that edge will be the shortest next path
         *      to traverse along the graph.  For this graph we started
         *      node "1" and the visible nodes (aka frontier nodes)
         *      are "2=1, 3=7 & 4=5"; the next shortest path to traverse
         *      will be node "2" at a distance of "1"
         */
        Enumeration el = hash.keys();
        String key = null;
        int val = 0;
        int min = 999;
        int max = 0;
        while (el.hasMoreElements()) {
            key = el.nextElement().toString();
            val = hash.get(key);
            System.out.println(key + " " + val);
            max = Integer.parseInt(key);
            if(min > max)
            {
                min = max;
            }
        }
        return min;
    }
    /**
     * Print hashtable {String, Integer[]}
     * @param hash  Param must be of Hashtable<String, Integer[]>
     */
    public void printHash_1D(Hashtable<String, Integer[]> hash){
        String key = null;
        Enumeration ky = hash.keys();
        while (ky.hasMoreElements())
        {
            key = ky.nextElement().toString();
            System.out.print(key);
            System.out.println(printNode(hash.get(key)));

        }

    }
    /**
     * Print hashtable {String, Integer[][]}
     * @param hash  Param must be of Hashtable<String, Integer[][]>
     */
    public void printHash_2D(Hashtable<String, Integer[][]> hash){
        String key = null;
        Enumeration ky = hash.keys();
        while (ky.hasMoreElements())
        {
            key = ky.nextElement().toString();
            System.out.print(key);
            System.out.println(printResults(hash.get(key)));

        }

    }
    /***********************************************************************************************************************
     * Print results set
     * @param graph    The results set
     **********************************************************************************************************************/
    public String printResults(Integer[][] graph)
    {

        int V = graph.length;
        int i = 0, j = 0;
        String rst = "";
        rst = String.format("\n");
        rst = String.format("-----------------------------");
        for ( i = 0; i < V; i++)
        {
            for ( j = 0; j < V; j++)
            {
                rst = String.format("| %-5d", graph[i][j]);
            }
            rst = String.format("|");
            rst = String.format("-----------------------------");
        }
        rst = String.format("\n\n");
        return rst;
    }
    /**
     * prints the edges of a node
     * @param node
     */
    public String printNode(Integer[] node)
    {
        int V = node.length;
        int j = 0;
        String rst = "";
        rst += String.format("\n");
        rst += String.format("-----------------------------\n");
        for ( j = 0; j < V; j++)
        {
            rst += String.format("| %-5d",(j < V ? node[j]:""));
        }
        rst += String.format("|\n");
        rst += String.format("-----------------------------\n");
        rst += String.format("\n\n");
        return rst;
    }
    /*****************************************************************************************
     * Locate the alphabed representation of the number give up to letter "M"
     * @param num   The number representation of a alphabet from 1..Z alphabet
     * @return  The alphabet for the number given
     ****************************************************************************************/
    public String getChar(int num)
    {
        String alpha ="";
        switch (num) {
            case 0:      alpha = "A";
                break;
            case 1:      alpha = "B";
                break;
            case 2:      alpha = "C";
                break;
            case 3:      alpha = "D";
                break;
            case 4:      alpha = "E";
                break;
            case 5:      alpha = "F";
                break;
            case 6:      alpha = "G";
                break;
            case 7:      alpha = "H";
                break;
            case 8:      alpha = "I";
                break;
            case 9:      alpha = "J";
                break;
            case 10:     alpha = "K";
                break;
            case 11:     alpha = "L";
                break;
            case 12:     alpha = "M";
                break;
            default:   alpha = "Error: Sorry your range is out of bounds...";
                break;
        }
        return alpha;
    }
    /*****************************************************************************************
     * Locate the number representation of the character; up to number 12
     * @param chr   The character to be converted
     * @return  The number representation of the character
     ****************************************************************************************/
    public Integer getNum(char chr)
    {
        String number ="";
        switch (chr) {
            case 'A':     number = "0";
                break;
            case 'B':     number = "1";
                break;
            case 'C':     number = "2";
                break;
            case 'D':     number = "3";
                break;
            case 'E':     number = "4";
                break;
            case 'F':     number = "5";
                break;
            case 'G':     number = "6";
                break;
            case 'H':     number = "7";
                break;
            case 'I':     number = "8";
                break;
            case 'J':     number = "9";
                break;
            case 'K':    number = "10";
                break;
            case 'L':    number = "11";
                break;
            case 'M':    number = "12";
                break;
            default:    number = "Error: Sorry your range is out of bounds...";
                break;
        }
        return Integer.parseInt(number);
    }


    public static void main(String[] args)
    {
                                // A , B , C , D
                                // 1 , 2 , 3 , 4
        Integer[][] graph = {   {INF, 1, 7, 5},      // 1
                                {1, INF, INF, 3},    // 2
                                {7, INF, INF, 2},    // 3
                                {5, 3, 2, INF}};     // 4

        Dijkstra dj = new Dijkstra();
        dj.DijkstraAlgorithm(graph,1);


    }
}