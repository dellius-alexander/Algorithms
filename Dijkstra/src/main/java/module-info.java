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
/////////////////////////////////////////////////////////////////////

/**
 * Dijkstra's algorithm is an algorithm for finding the shortest
 * paths between nodes in a graph, which may represent, for example,
 * road networks. The algorithm operates by constructing a set of
 * nodes that have been visited, and maintaining a set of tentative
 * distances between each node and the starting node.
 *
 * Using set notation, we can represent the set of visited nodes
 * as V, and the set of tentative distances as D. Dijkstra's
 * algorithm operates by repeatedly selecting the node from the
 * set V that has the minimum distance in D, and updating the
 * distances of its neighbors based on the distance to the starting
 * node through that node.
 *
 * Here is a pseudocode representation of Dijkstra's algorithm using set notation:
 *
 * <pre>
 *  procedure Dijkstra(G, s):
 *   V = {}
 *   D = {s: 0}
 *   while V != G:
 *     u = min(D, key=D.get)
 *     V.add(u)
 *     for v in G.neighbors(u):
 *       if v not in V:
 *         if v not in D or D[v] <code>></code> D[u] + G.distance(u, v):
 *           D[v] = D[u] + G.distance(u, v)
 *   return D
 * </pre>
 *
 * In this pseudocode, G is a graph data structure, s is the starting
 * node, V is the set of visited nodes, and D is the set of tentative
 * distances. The algorithm repeatedly selects the unvisited node with the
 * minimum distance in D, adds it to the set of visited nodes, and updates
 * the distances of its neighbors based on the distance to the starting
 * node through that node. When all nodes have been visited, the algorithm
 * returns the set of tentative distances.
 *
 */
module Dijkstra {

    requires lombok;
    requires org.json;
    requires java.desktop;
    requires org.slf4j;
    requires Node;
    exports Dijkstra;

}