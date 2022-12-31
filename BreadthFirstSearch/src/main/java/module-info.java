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
 * BreadthFirstSearch
 *
 * <h2>Procedure BreadthFirstSearch’s Algorithm</h2>
 * <hr/>
 * In a Breadth-First search algorithm we start at some node and traverse
 * a directed graph ending at some destination node. At some point during
 * the course of a traversal, it explores all the neighbors of a node before
 * moving on to the next level of nodes, every node in the graph changes state from
 * undiscovered to discovered. In a breadth-first search of an undirected
 * graph, we assign a direction to each edge, from the discoverer `u` to
 * the discovered `v`. We thus denote `u` to be the parent of child `v`. Since each
 * node has exactly one parent, except for the root, this defines a tree
 * on the vertices of the graph. This algorithm has a modification
 * terminating at your target or destination node.
 * <hr/>
 * <h4>
 * <strong>BreadthFirstSearch(G, d, s, z) := path from s to z</strong>
 * </h4>
 * <pre>
 * V(G) := set of vertices in G
 * E(G) := set of edges in G
 *
 * for each vertex u in V(G) - {s} do
 *     state(u) = "undiscovered"
 *     p(u) = nil
 *
 * state(s) = "discovered"
 * p(s) = nil
 * Q = {s}
 *
 * while Q is not empty do
 *     u = dequeue(Q)
 *     process vertex u as desired
 *     for each v in Adj(u) do
 *         process edge (u,v) as desired
 *         if state(v) = "undiscovered" then
 *             state(v) = "discovered"
 *             p(v) = u
 *             enqueue(Q, v)
 *         if v = z then
 *             break
 *     state(u) = "processed"
 *     if v = z then
 *         break
 *
 * return state(z)
 * </pre>
 */
module BreadthFirstSearch {

    requires lombok;
    requires org.json;
    requires java.desktop;
    requires org.slf4j;
    requires Node;
    exports BreadthFirstSearch;

}