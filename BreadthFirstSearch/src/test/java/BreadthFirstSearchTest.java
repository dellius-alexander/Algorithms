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
import BreadthFirstSearch.BreadthFirstSearch;
import Graph.Graph;
import Node.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Queue;

@Slf4j
public class BreadthFirstSearchTest {
    @Test
    public void breadthFirstSearchTest()
    {
        try {
            /////////////////////////////////////////////////////////
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
            /////////////////////////////////////////////////////////
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
            /////////////////////////////////////////////////////////
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
            /////////////////////////////////////////////////////////
            Graph<Node<String,String,Integer>> graph = new Graph<Node<String, String, Integer>>();
            /////////////////////////////////////////////////////////
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
            /////////////////////////////////////////////////////////
            BreadthFirstSearch bfs = new BreadthFirstSearch();
            /////////////////////////////////////////////////////////
            Queue<Node<String,String,Integer>> results0 =  bfs
                    .breadthFirstSearch(
                            graph,
                            new Distance<>(0),
                            nodeA,
                            nodeI);
            /////////////////////////////////////////////////////////
            nodeA.setShortestPath(new ArrayList<>(results0));
            Long results0EndTime = System.currentTimeMillis() - startTime;
            log.info("\nRecursive Shortest Path 0:{} \nRuntime: {}\n", results0, results0EndTime);
            // we should have a list of 10 nodes returned from BFS
            Assert.assertEquals(results0.size(),8);
        } catch (Exception e){
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }

    }
}
