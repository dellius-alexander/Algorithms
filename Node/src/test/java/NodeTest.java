import Node.*;
import Graph.Graph;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;



@Slf4j
public class NodeTest {

    @Test
    public void nodeGraphTest() {
        try {
            Long startTime = System.currentTimeMillis();
            Node<Object, Object, Object> nodeA = new Node<Object, Object, Object>("A", "AA");
            Node<Object, Object, Object> nodeB = new Node<Object, Object, Object>("B", "BB");
            Node<Object, Object, Object> nodeC = new Node<Object, Object, Object>("C", "CC");
            Node<Object, Object, Object> nodeD = new Node<Object, Object, Object>("D", "DD");
            Node<Object, Object, Object> nodeE = new Node<Object, Object, Object>("E", "EE");
            Node<Object, Object, Object> nodeF = new Node<Object, Object, Object>("F", "FF");
            Node<Object, Object, Object> nodeG = new Node<Object, Object, Object>("G", "GG");
            Node<Object, Object, Object> nodeH = new Node<Object, Object, Object>("H", "HH");
            Node<Object, Object, Object> nodeI = new Node<Object, Object, Object>("I", "II");
            Node<Object, Object, Object> nodeJ = new Node<Object, Object, Object>("J", "JJ");
            Node<Object, Object, Object> nodeK = new Node<Object, Object, Object>("K", "KK");
            Node<Object, Object, Object> nodeL = new Node<Object, Object, Object>("L", "LL");

            nodeA.setCoordinate(new Coordinate<Object, Object>(1, 1));
            nodeB.setCoordinate(new Coordinate<Object, Object>(1, 2));
            nodeC.setCoordinate(new Coordinate<Object, Object>(1, 3));
            nodeD.setCoordinate(new Coordinate<Object, Object>(1, 4));
            nodeC.setCoordinate(new Coordinate<Object, Object>(2, 1));
            nodeE.setCoordinate(new Coordinate<Object, Object>(2, 2));
            nodeF.setCoordinate(new Coordinate<Object, Object>(2, 3));
            nodeG.setCoordinate(new Coordinate<Object, Object>(2, 4));
            nodeH.setCoordinate(new Coordinate<Object, Object>(3, 1));
            nodeI.setCoordinate(new Coordinate<Object, Object>(3, 2));
            nodeJ.setCoordinate(new Coordinate<Object, Object>(3, 3));
            nodeK.setCoordinate(new Coordinate<Object, Object>(3, 4));
            nodeL.setCoordinate(new Coordinate<Object, Object>(4, 1));
            System.out.printf("\n%s",nodeA);
            nodeA.addAdjacentNode(nodeB, new Distance<Object>(1));
            nodeA.addAdjacentNode(nodeC, new Distance<Object>(1));
            nodeB.addAdjacentNode(nodeD, new Distance<Object>(1));
            nodeB.addAdjacentNode(nodeC, new Distance<Object>(1));
            nodeC.addAdjacentNode(nodeA, new Distance<Object>(1));
            nodeD.addAdjacentNode(nodeA, new Distance<Object>(1));
            nodeD.addAdjacentNode(nodeG, new Distance<Object>(1));
            nodeF.addAdjacentNode(nodeE, new Distance<Object>(1));
            nodeF.addAdjacentNode(nodeI, new Distance<Object>(1));
            nodeG.addAdjacentNode(nodeD, new Distance<Object>(1));
            nodeG.addAdjacentNode(nodeJ, new Distance<Object>(1));
            nodeH.addAdjacentNode(nodeL, new Distance<Object>(1));
            nodeH.addAdjacentNode(nodeK, new Distance<Object>(1));
            nodeI.addAdjacentNode(nodeG, new Distance<Object>(1));
            nodeI.addAdjacentNode(nodeK, new Distance<Object>(1));
            nodeJ.addAdjacentNode(nodeH, new Distance<Object>(2));
            nodeJ.addAdjacentNode(nodeI, new Distance<Object>(4));
            nodeK.addAdjacentNode(nodeH, new Distance<Object>(1));
            nodeK.addAdjacentNode(nodeB, new Distance<Object>(1));
            nodeL.addAdjacentNode(nodeJ, new Distance<Object>(5));
            nodeL.addAdjacentNode(nodeD, new Distance<Object>(5));

            Graph<Node<Object, Object, Object>> graph = new Graph<Node<Object, Object, Object>>();

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
            // verify nodes in graph
            Node<Object, Object, Object> result = null;
            for (Node<Object, Object, Object> n : graph.getNodes())
            {
                if (n.equals(nodeA))
                {
                    result = n;
                    log.info("{}",n);
                }
            }
            Long results0EndTime = System.currentTimeMillis() - startTime;
            log.info("Runtime: {}",results0EndTime);
            Assert.assertEquals(result,nodeA);

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
