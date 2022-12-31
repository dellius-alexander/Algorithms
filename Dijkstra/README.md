# Dijkstra Algorithm

<hr />
<div class="markdown prose w-full break-words dark:prose-invert light">

<p>Dijkstra's algorithm is an algorithm for finding the shortest path 
between two nodes in a graph, which may represent, for example, a road network. 
The advanced form of Dijkstra's algorithm, which is sometimes referred to as 
Dijkstra(G, d, s, z), is a variant of the algorithm that allows you to specify 
a specific start node (s) and destination node (z), and returns the shortest 
path from the start node to the destination node.</p>

<p>In this revised 
pseudocode representation of the advanced form of Dijkstra's algorithm, the 
function <code>Dijkstra</code> takes four arguments:</p>
<ul>
<li><code>G</code>: a graph data structure</li>
<li><code>d</code>: the distance between nodes</li>
<li><code>s</code>: the starting node</li>
<li><code>z</code>: the destination node</li>
</ul>
<p>The function operates by constructing three sets:</p>
<ul>
<li><code>V</code>: a set of visited nodes</li>
<li><code>D</code>: a set of tentative distances</li>
<li><code>P</code>: a set of predecessor nodes</li>
</ul>
<p>It then defines a recursive function <code>recursive</code> 
that takes a single argument <code>u</code>, which represents 
the current node being processed. The <code>recursive</code> 
function does the following:</p>
<ol>
<li>Adds the current node<code>u</code> to the set of visited nodes <code>V</code>.</li>
<li>Iterates through the neighbors of the current node <code>u</code>.</li>
<li>For each neighbor <code>v</code>, if <code>v</code> has not yet been visited:
    <ul>
    <li>If <code>v</code> is not in the set of tentative distances <code>D</code> or 
    the tentative distance to <code>v</code> through <code>u</code> is shorter than the 
    current tentative distance to <code>v</code>, update the tentative distance to 
    <code>v</code> and set the predecessor of <code>v</code> to <code>u</code>.</li>
    <li>If <code>v</code> is the destination node <code>z</code>, return from the function.</li>
    </ul>
</li>
<li>Select the next unvisited node <code>u</code> with the minimum distance in <code>D</code>.</li>
<li>Call the <code>recursive</code> function recursively with <code>u</code> as the argument.</li>
</ol>
<p>The <code>Dijkstra</code> function then calls the <code>recursive</code> function with 
the starting node <code>s</code> as the argument. When the <code>recursive</code> function 
returns, the <code>Dijkstra</code> function constructs the shortest path from the start 
node to the destination node by tracing the path through the predecessor nodes stored 
in <code>P</code>, and returns the path.</p>

<p>Here is the complete pseudocode representation 
for the revised version of the advanced form of Dijkstra's algorithm:</p>
<pre><div class="bg-black mb-4 rounded-md">
<div class="flex items-center relative text-gray-200 bg-gray-800 px-4 py-2 text-xs font-sans">
<button class="flex ml-auto gap-2">
<svg stroke="currentColor" fill="none" stroke-width="2" viewBox="0 0 24 24" stroke-linecap="round" stroke-linejoin="round" class="h-4 w-4" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg"><path d="M16 4h2a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V6a2 2 0 0 1 2-2h2">
</path><rect x="8" y="2" width="8" height="4" rx="1" ry="1"></rect></svg>Copy code</button></div>
<div class="p-4 overflow-y-auto"><code class="!whitespace-pre-wrap hljs language-scss">function <span class="hljs-built_in">Dijkstra</span>(G, d, s, z):
  V = {}
  D = {s: <span class="hljs-number">0</span>}
  <span class="hljs-selector-tag">P</span> = {}
  def <span class="hljs-built_in">recursive</span>(u):
    V.<span class="hljs-built_in">add</span>(u)
    for v in G.<span class="hljs-built_in">neighbors</span>(u):
      if v not in V:
        if v not in D or D[v] &gt; D[u] + G.<span class="hljs-built_in">distance</span>(u, v):
          D[v] = D[u] + G.<span class="hljs-built_in">distance</span>(u, v)
          P[v] = u
        if v == z:
          return
    u = <span class="hljs-built_in">min</span>(D, key=D.get)
    <span class="hljs-built_in">recursive</span>(u)
  <span class="hljs-built_in">recursive</span>(s)
  path = []
  while z != s:
    path.<span class="hljs-built_in">insert</span>(<span class="hljs-number">0</span>, z)
    z = P[z]
  path.<span class="hljs-built_in">insert</span>(<span class="hljs-number">0</span>, s)
  return path
</code></div></div></pre></div>
