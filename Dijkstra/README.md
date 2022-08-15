# Dijkstra Algorithm

<hr/>

When actions have different costs, an obvious choice is to use best-first 
search where the evaluation function is the cost of the path from the root 
to the current node. This is called Dijkstra’s algorithm by the theoretical 
computer science community, and uniform-cost search by the AI community. 
The idea is that while breadth-first search spreads out in waves of uniform 
depth—first depth 1, then depth 2, and so on—uniform-cost search spreads 
out in waves of uniform path-cost. The algorithm can be implemented as a 
call to BEST-FIRST- SEARCH with PATH-COST as the evaluation function.

<hr/>
<pre>
<h2>Procedure Dijkstra’s Algorithm</h2>
<hr/>
Recursive implementation of Dijkstra Algorithm:
    Dijkstra’s algorithm is the method of choice for finding shortest 
    paths in an edge- and/or vertex-weighted graph. Given a particular 
    start vertex s, it finds the shortest path from s to every other 
    vertex in the graph, including your desired destination t.
<hr/>
<h3>
<strong>Dijkstra( G, d, a, z ):= [a...z]</strong> {shortest path from a to z}
</h3>
{<strong>Below:</strong> G has vertices/edges from a to z,
a := {v<sub>0</sub>,v<sub>1</sub>,...,v<sub>n</sub>} := z
and lengths w(v<sub>i</sub>, v<sub>j</sub>)
where w(v<sub>i</sub>, v<sub>j</sub>) = +Infinity
if {v<sub>i</sub>, v<sub>j</sub>} is not an edge in G}

Dijkstra(
    G := digraph,
    d := distance/weight/cost of edges,
    a := start node,
    z := target/destination node ):= shortest path to z


{<strong>Below:</strong> Node labels/weights are now 
initialized so that the label/weight of the start 
node/vertex a is 0 and all other node labels/weights 
are set to  +Infinity, and S is the empty set that 
represents the set of nodes in the shortest
path from a to z. Optionally: You can set a default value
of +Infinity to omit this step}

if (d == 0) {initial function call should initialize d := 0}
    for v<sub>i</sub> in n;
       v<sub>i</sub> := +Infinity;   {initialize all nodes to +Infinity}
    a := 0;     {initialize the start node distance/weight to 0}

L(a) := 0;  {add start node to list unsettled nodes}
S := 0;     {initialize list for nodes in shortest path from a to z}

{<strong>Below:</strong> while the destination node/vertex z is not part of
the shortest path list, pick the node u to a with the minimum distance/weight 
and add set S }

while z ∉ S; {here is where we recursively traverse down the 
                    tree of nodes backup the stack}
   u := a {vertex not in S with L(u) minimal distance
       node/vertex}
   S := S ∪ {u} {add u to set S}

   {<strong>Below:</strong> for all adjacent nodes/vertices not in
   shortest path set, compare the weights L(u) + w(u,v) &#60; L(v)}

    for all {v} vertices adjacent to u or not in S;
        if L(u) + w(u, v) &#60; L(v)
        then L(v) := L(u) + w(u, v);

        {this adds a vertex to S with minimal label and
        updates the labels of vertices not in S}
        
        L := L U {v}    {add minimal node to list L(v)}
        
{<strong>Below:</strong> this should return back up a list of nodes leading
to the root; the list represents the shortest path from
the start to destination node}

return S(z) {S(z) = list of shortest path from a to z}
</pre>
