

public class AdjacencyMatrix {
    int vertex;
    boolean matrix[][];

    public AdjacencyMatrix(int vertex) {
        this.vertex = vertex;
        matrix = new boolean[vertex][vertex];
    }

    public void addEdge(int source, int destination) {
        //add edge
        matrix[source-1][destination-1]=true;

        //add bak edge for undirected graph
        //matrix[destination-1][source-1] = true;
    }

    public void removeEdge(int source, int destination) {
        //add edge
        matrix[source-1][destination-1]=false;

        //add bak edge for undirected graph
        //matrix[destination][source] = true;
    }

    public void printGraph() {
        System.out.println("Graph: (Adjacency Matrix)");
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j <vertex ; j++) {
                System.out.print((matrix[i][j] == true ? 1:0) + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < vertex; i++) {
            System.out.print("Vertex " + i + " is connected to:");
            for (int j = 0; j <vertex ; j++) {
                if((matrix[i][j] == true ? 1:0)==1){
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }


    public void multiply(int power, boolean[][] matrix)
    {
        boolean[] row = null;
        boolean[] col = null;
        boolean[][][] results = null;
            for (int i = 0; i < matrix.length(); i++) {
                
            }
    }

    public static void main(String[] args) {
        AdjacencyMatrix graph = new AdjacencyMatrix(4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 2);
        graph.addEdge(4, 1);
        graph.addEdge(4, 2);
        graph.addEdge(4, 3);
        graph.printGraph();
    }
}