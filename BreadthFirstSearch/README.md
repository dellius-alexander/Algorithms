# Breadth-First Search

<hr/>

When all actions have the same cost, an appropriate
strategy is breadth-first search, in which the root node is expanded
first, then all the successors of the root node are expanded next,
then their successors, and so on. This is a systematic search strategy
that is therefore complete even on infinite state spaces. We could
implement breadth-first search as a call to BEST-FIRST-SEARCH where
the evaluation function is the depth of the node—that is, the number
of actions it takes to reach the node.

<hr/>
<pre>
<h2>Procedure BreadthFirstSearch’s Algorithm</h2>
<p>
A breadth-first search algorithm we start at some node and traverse 
a directed graph ending at some destination node. At some point during 
the course of a traversal, every node in the graph changes state from 
undiscovered to discovered. In a breadth-first search of an undirected 
graph, we assign a direction to each edge, from the discoverer u to 
the discovered v. We thus denote u to be the parent of v. Since each 
node has exactly one parent, except for the root, this defines a tree 
on the vertices of the graph.
</p>
<hr/>

BFS(G, s)
    for each vertex u∈V[G]−{s} do
        state[u] = “undiscovered”
        p[u] = nil, i.e. no parent is in the BFS tree
    state[s] = “discovered”
    p[s] = nil
    Q = {s}
    while Q ̸= ∅ do
        u = dequeue[Q]
        process vertex u as desired
        for each v ∈ Adj[u] do
            process edge (u,v) as desired
            if state[v] = “undiscovered” then
                state[v] = “discovered”
                p[v] = u    {parent node = child node}
                enqueue[Q, v]
        state[u] = “processed”
       
</pre>