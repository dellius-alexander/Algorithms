import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dijkstra 
{

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
     ****************************************************************************************/
//////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////

    public final static int INF = 9999;
    private static Logger log = Logger.getLogger("Dijkstra");
    
    
    /****************************************************************************************
     * Default Constructor
     ****************************************************************************************/
    public Dijkstra(){}
    /****************************************************************************************
     * Dijkstra Algorihm
     * @param graph
     * @param startNode
     ****************************************************************************************/
    public Dijkstra(Integer graph[][], Integer startNode)
    {
        log.setLevel(Level.INFO);
        int S = startNode-1;
        int V = graph.length;
        int N = 0;  // node number
        int i = 0;  // from node
        int j = 0;  // to node
        int k = 0;  // current node
        // identifies the location of edges found and stores the location value at this 
        // position in the array
        int l = 0; 
        // settledNodes: is the "shortest path tree" set of nodes evaluated to have the shortest 
        // path from source node edge nodes of length(s) w(i,j)
        // initially it is empty but we will fill it up as we evaluate each node
        Hashtable<String, Integer[][]> settledNodes = new Hashtable<String, Integer[][]>(); 
        // unsettledNodes: is the set of nodes not yet evaluated
        // we will initialize this table with all values in the graph and remove them
        // as they are evaluated and eventually settled
        Hashtable<String, Integer[]> unsettledNodes = new Hashtable<String, Integer[]>();
        // Create a hash table of visable nodes from any settled, parent or root node;
        // these nodes are removed from view as they are settled in the shortest path
        // from root
        Hashtable<String, Integer> frontier = new Hashtable<String, Integer>();
        Integer nextShortestPath =0;
        // Represents a single node and the weighted edges from its self (parent) to leaf nodes
        Integer[] node = null;
        List<Integer[][]> results = new ArrayList<Integer[][]>();
        Integer[][] setNodes = new Integer[V][V];
        String key="";
         // Get all weights of all nodes that have an edge of this parent node
         for (i = 0; i < V; i++) 
         { // represents from node
            node = new Integer[V];
            for (j = 0; j < V; j++) 
            {  // represents to nodes                
                node[j] = graph[i][j];
            }
            //System.out.println(printNode(node));
            key = "Node "+(i+1)+":";
            unsettledNodes.put(key,node);
        }
       
        
        /**
         *  1.  Start at the "ROOT" node; it has no parent so it will be 
         *          labeled as Root; the distance from Root to itself is "0"
         */
        // Initialize all distances/weights in the settledNodes to INFINITY, assign a value of "0"
        // from the root node to itself and settle the root node
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    if (i == S && j == S) {
                        setNodes[i][j] = 0;
                        log.info((i + 1) + " to " + (j + 1) + " = " + 0);
                    } else if (i == S && j != S) {
                        frontier.put((j + 1) + "", graph[i][j]);
                        setNodes[i][j] = graph[i][j];
                    } else {
                        setNodes[i][j] = INF;
                    }
                }
            }
        //System.out.println(frontier);
        //System.out.println(printResults(setNodes));


        /**
         * 2.  After settling our starting node we need to look around
         *      on the frontier and find the smallest weighted edge.
         *      The node at that edge will be the shortest next path
         *      to treverse along the graph.  For this graph we started
         *      node "1" and the visible nodes (aka frontier nodes)
         *      are "2=1, 3=7 & 4=5"; the next shortest path to traverse
             *      will be node "2" at a distance of "1"
             *
            Enumeration el = frontier.keys();
            while (el.hasMoreElements()) {
                String key = el.nextElement().toString();
                int val = frontier.get(key);
                System.out.println(key + " " + val);

            }



/*


        System.out.println("\n\n");      
        String keyName ="Settled Node "+(S+1);
        settledNodes.put(keyName, setNodes);
        System.out.println(settledNodes.keys().nextElement() +"\n");
        printResults(settledNodes.get(keyName));
        keyName ="Settled Node "+3;
        settledNodes.put(keyName, graph);
        System.out.println(settledNodes.keys().nextElement() +"\n");
        printResults(settledNodes.get(keyName));
        System.out.println("\n\n");
        results.add(setNodes);
        System.out.println("\n\n");
       
        
       // Iterable arr = element.asIterator();
        while (el.hasMoreElements()) {
            String key = el.nextElement().toString();
            System.out.println(key + " " + frontier.get(key));
        }
        System.out.println(frontier);
        //System.out.println((frontier.keys().nextElement()) + "  "+ frontier.get(elem));
        System.out.println("\n\n")
        
*/
       
           
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
            rst = String.format("| %-5d",(j < V ? graph[i][j]:""));
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

        Dijkstra dj = new Dijkstra(graph, 1);
        

    }
}