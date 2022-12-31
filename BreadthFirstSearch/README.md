# Breadth-First Search

<hr/>

<h2>Overview: BreadthFirstSearch’s Algorithm</h2>

<div >
<p>
When all actions have the same cost, an appropriate
strategy is breadth-first search, in which the root node is expanded
first, then all the successors of the root node are expanded next,
then their successors, and so on. This is a systematic search strategy
that is therefore complete even on infinite state spaces. We could
implement breadth-first search as a call to BEST-FIRST-SEARCH where
the evaluation function is the depth of the node—that is, the number
of actions it takes to reach the node.
</p>

<hr />
<h3>Procedure BreadthFirstSearch’s Algorithm</h3>

<p>
Breadth-first search (BFS) is an algorithm for traversing or searching a graph. 
It starts at a given node (called the "start" or "source" node) and explores all the 
nodes reachable from that node in the graph. It visits the nodes in a breadth-first 
manner, meaning it explores all the neighbors of a node before moving on to the next 
level of nodes.
</p>
  <p>The algorithm can be described as follows:</p>
  <ol>
    <li>
      <p>Initialize the start node as "discovered" and all other nodes as "undiscovered". Assign the start node a parent of <code>nil</code>, and initialize an empty queue <code>Q</code>. </p>
    </li>
    <li>
      <p>Add the start node to the queue <code>Q</code>. </p>
    </li>
    <li>
      <p>While the queue is not empty:</p>
      <ol>
        <li>Dequeue the first node <code>u</code> from the queue <code>Q</code>. </li>
        <li>Process the vertex <code>u</code> as desired. </li>
        <li>For each neighbor <code>v</code> of <code>u</code>: <ol>
            <li>Process the edge <code>(u, v)</code> as desired. </li>
            <li>If <code>v</code> has not been discovered, set its parent to <code>u</code>, 
mark it as "discovered", and add it to the queue <code>Q</code>. </li>
            <li>If <code>v</code> is the destination node ( <code>z</code>), break out of the 
loop. </li>
          </ol>
        </li>
        <li>Mark <code>u</code> as "processed". </li>
        <li>If <code>z</code> has been marked as "discovered", break out of the loop. </li>
      </ol>
    </li>
    <li>
      <p>Return the path from the start node <code>s</code> to the destination node <code>z</code>. 
This can be done by following the parent pointers from <code>z</code> back to <code>s</code>. </p>
    </li>
  </ol>
  <p>Some notes on the implementation:</p>
  <ul>
    <li>The variables <code>G</code>, <code>d</code>, <code>s</code>, and <code>z</code> are the 
input to the function. <code>G</code> is the graph, <code>d</code> is the distance/weight/cost of 
the edges, <code>s</code> is the start node, and <code>z</code> is the destination node. </li>
    <li>The <code>state</code> of a vertex is a string that can be either "undiscovered", "discovered", or "processed". 
    Please note that this is an arbitrary interpretation and can be implemented using numbers such as -1 for "undiscovered", 
    0 for "discovered" and 1 for "processed".</li>
    <li>The <code>p</code> function is a mapping that stores the parent of each vertex in the BFS tree. </li>
    <li>The queue <code>Q</code> is used to store the nodes that are yet to be processed. </li>
    <li>The <code>Adj(u)</code> function returns the neighbors of <code>u</code>. </li>
    <li>The <code>enqueue</code> and <code>dequeue</code> functions add and remove items from the queue <code>Q</code>, respectively. </li>
  </ul>
</div>

<hr />

```text
BreadthFirstSearch(G, d, s, z) := path from s to z

V(G) := set of vertices in G
E(G) := set of edges in G

for each vertex u in V(G) - {s} do
    state(u) = "undiscovered"
    p(u) = nil

state(s) = "discovered"
p(s) = nil
Q = {s}

while Q is not empty do
    u = dequeue(Q)
    process vertex u as desired
    for each v in Adj(u) do
        process edge (u,v) as desired
        if state(v) = "undiscovered" then
            state(v) = "discovered"
            p(v) = u
            enqueue(Q, v)
        if v = z then
            break
    state(u) = "processed"
    if v = z then
        break

return state(z)
```
