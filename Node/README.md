# Node
A `node` represents the `state` of an object in the search tree 
of a search algorithm.

A search algorithm takes a search problem as input and returns 
a solution, or an indication of failure. Algorithms superimpose 
a search tree over the state-space graph, forming various paths 
from the initial state, trying to find a path that reaches a `goal 
state`. Each `node` in the search tree corresponds to a state in 
the state space and the edges in the search tree correspond to 
action's. The root of the tree corresponds to the initial state 
of the problem.

It is important to understand the distinction between the state 
space and the search tree. The state space describes the 
(possibly infinite) set of states in the world, and the actions 
that allow transitions from one state to another. The search 
tree describes paths between these states, reaching towards the 
goal. The search tree may have multiple paths to (and thus multiple 
`nodes` for) any given state, but each node in the tree has a unique 
path back to the root (as in all trees).