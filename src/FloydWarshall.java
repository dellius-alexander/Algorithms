
public class FloydWarshall
{

    public Integer[][] distance;
    public Integer[][][] resultsSet;
    public Integer[][] origin_graph;
    public static final int INF = 9999;
    public int V;
    /**********************************************************************************************************************
     * Default Constructor
     *********************************************************************************************************************/
    public FloydWarshall(){}
    /**********************************************************************************************************************
     * Constructor assigning the graph 
     * @param graph
     *********************************************************************************************************************/
    public FloydWarshall(Integer[][] graph)
    {
        this.distance = graph;
        this.origin_graph = graph;
        this.V = graph.length;
        this.resultsSet = new Integer[V+1][V+1][V+1];
    }
    /**********************************************************************************************************************
     * Get the graph
     * @return
     *********************************************************************************************************************/
    public Integer[][] getGraph()
    {
        return this.distance;
    }
    /**
     * Determines if null conditions exist within the array
     * @param graph
     * @return
     */
    public boolean validateGraph(Integer[][] graph)
    {
        for (int r = 0; r < graph.length; r++) {
            for (int c = 0; c < graph.length; c++) {
                if (graph[r][c].toString() != null ) {
                    return false;
                }
            }
        }
        return true;
    }
    /**********************************************************************************************************************
     * Floyd Warshall Algorithm on Weighted Edges
     * @return True if successful only  
     ***********************************************************************************************************************/
    public boolean floyd_Warshall_Algorithm_Weighted()
    {       
        if (this.distance == null) 
        {
            System.out.println("Please initialize your graph!!!\n\nThe graph is currently null!!!\n\n");
            System.exit(0);
        }
       
        // V  = this.distance.length; // Vertices quantity
        // N represents the current node; N - 1 = previous unlocked node
        int N = 0;
        // source node
        int i = 0;
        // destination node
        int j = 0;
        
        // Assign the root N = node 0 to the root of our results set
        for (i = 0; i < V; i++)
        {
            for (j = 0; j < V; j++)
            {
                resultsSet[N][i][j] = distance[i][j];
            }
        }
        // Unlocking starting at N = node 1
        for ( N = 1; N < V; N++) 
        {
            for ( i = 0; i < V; i++) 
            {
                for ( j = 0; j < V; j++) 
                {
                    // capture our results set of each unlocking
                    resultsSet[N][i][j] = (distance[i][N-1] + distance[N-1][j] < distance[i][j] ? (distance[i][N-1] + distance[N-1][j]): distance[i][j]);
                    // unlock current itteraton/node recurrsively
                    distance[i][j] = (distance[i][N-1] + distance[N-1][j] < distance[i][j] ? (distance[i][N-1] + distance[N-1][j]): distance[i][j]);
                }
            }
        }
        return true;
    }
    /***********************************************************************************************************************
     * Floyd Warshall Algorithm on Weighted Edges
     * @param graph The graph to be evaluated
     * @return
     ***********************************************************************************************************************/
    public boolean floyd_Warshall_Algorithm_Weighted(Integer[][] graph)
    {        
        // initialize each array
        this.distance = graph;
        this.origin_graph = graph;
        // Vertices quantity
        V  = this.distance.length;  
        this.resultsSet = new Integer[V+1][V][V];

        // VERIFY that our array is not null before we continue
        if (this.distance == null) 
        {
            System.out.println("Please initialize your graph!!!\n\nThe graph is currently null!!!\n\n");
            System.exit(0);
        }          
     
        // N represents the current node; N - 1 = represents the previous node or previous unlocked node
        int N = 0; 
        // source node
        int i = 0; 
        // destination node
        int j = 0;        
        // Assign the root N = node 0 to the root of our results set
        for (i = 0; i < V; i++) 
        {
            for (j = 0; j < V; j++) 
            {
                resultsSet[N][i][j] = distance[i][j];
            }
        }
        // Unlocking starting at N = node 1
        for ( N = 1; N <= V; N++) 
        {
            for ( i = 0; i < V; i++) 
            {
                for ( j = 0; j < V; j++) {
                    // capture our results set of each unlocking
                    resultsSet[N][i][j] = (distance[i][N - 1] + distance[N - 1][j] < distance[i][j] ? (distance[i][N - 1] + distance[N - 1][j]) : distance[i][j]);
                    // unlock current itteraton/node recurrsively
                    distance[i][j] = (distance[i][N - 1] + distance[N - 1][j] < distance[i][j] ? (distance[i][N - 1] + distance[N - 1][j]) : distance[i][j]);
                }
            }
        }
        return true;
    }

    /***********************************************************************************************************************
     * Main Method
     * @param args
     **********************************************************************************************************************/
    public static void main(String[] args) {

        Integer[][] root_graph = {{0, 3, INF, 5, INF, 2},
                {3, 0, 7, INF, INF, INF},
                {INF, 7, 0, 4, 13, 12},
                {5, INF, 4, 0, 15, INF},
                {INF, INF, 13, 15, 0, 19},
                {2, INF, 12, INF, 19, 0}};
        FloydWarshall fw = new FloydWarshall(root_graph);
        fw.floyd_Warshall_Algorithm_Weighted();
        fw.printResults();
        //fw.floyd_Warshall_Algorithm_Weighted(root_graph);
        //fw.printResults(fw.resultsSet);
    }

    /***********************************************************************************************************************
     * Print results set
     **********************************************************************************************************************/
    public void printResults() {
        int N, i, j;
        String border = "";
        for (int k = 0; k < V; k++) {
            border += "---------";
        }
        System.out.println("\n");
        for (N = 0; N < V; N++) {
            System.out.println((N == 0 ? "Original Graph: \n\n" : "Unlocking Node " + N + ": \n\n"));
            for (i = 0; i < V; i++) {
                System.out.println(border + "-");
                for (j = 0; j < V; j++) {
                    System.out.format("|%5s   ", this.resultsSet[N][i][j].toString());
                }
                System.out.println("|");
            }
            System.out.println(border + "-");
            System.out.println("\n\n");
        }
    }

    /***********************************************************************************************************************
     * Print results set
     * @param resultsSet    The results set
     **********************************************************************************************************************/
    public void printResults(Integer[][][] resultsSet) {

        int V = resultsSet.length;
        int N, i, j;
        String border = "";
        for (int k = 0; k < V; k++) {
            border += "---------";
        }
        System.out.println("\n");
        for (N = 0; N < V; N++) {
            System.out.println((N == 0 ? "Original Graph: \n\n" : "Unlocking Node " + N + ": \n\n"));
            for (i = 0; i < V - 1; i++) {
                System.out.println(border + "-");
                for (j = 0; j < V - 1; j++) {
                    System.out.format("|%5s   ", resultsSet[N][i][j].toString());
                }
                System.out.println("|");
            }
            System.out.println(border + "-");
            System.out.println("\n\n");
        }
    }
}
        