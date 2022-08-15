import BreadthFirstSearch.BreadthFirstSearch;
import Dijkstra.Coordinate;
import Dijkstra.Distance;
import Dijkstra.Graph;
import Dijkstra.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Queue;
import java.util.stream.Collectors;

@Test
public class BreadthFirstSearchTest {
    private final static Logger log = LoggerFactory.getLogger(BreadthFirstSearchTest.class);
    @Test
    public void breadthFirstSearchTest(){

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

            nodeA.setCoordinate(new Coordinate<>(1,1));
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
            nodeJ.addAdjacentNode(nodeH, new Distance<>(1));
            nodeJ.addAdjacentNode(nodeI, new Distance<>(1));
            nodeK.addAdjacentNode(nodeH, new Distance<>(1));
            nodeK.addAdjacentNode(nodeB, new Distance<>(1));
            nodeL.addAdjacentNode(nodeJ, new Distance<>(1));
            nodeL.addAdjacentNode(nodeD, new Distance<>(1));

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
            // we should have a list of 10 nodes returned from BFS
            Assert.assertEquals(results0.size(),10);
        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }

    }
}
