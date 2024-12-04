### About This Project
This program was originally written in the Eclipse IDE for Java Developers.

_Last Updated: April 27th, 2022_

# OVERVIEW
This program was created to demonstrate proficiency in constructing a binary
search tree and perform pre-order, in-order, post-order, and level-order
traversals, where all traversals have recursive implementations with the
exception of level-order. It also serves to compare the speed at which each
method of traversal occurs.

## IMPORTANT
**You can optionally see the order in which elements are traversed for each
traversal method by uncommenting lines 76, 92, 108, and 122 in `IntBST.java`.
However, due to file I/O overhead, enabling the feature dirties the time data.**

## Traversal Comparison Methodology
Initially, 10 `IntBST`s (integer binary search trees) are created, each
containing 500 pseudo-random integers ranging from 1-1000 inclusive. One of
these `IntBST`s will be used in a trial, totaling 10 trials.

For every new IntBST that is generated, the time it takes to generate its 500
constituent integers and insert them is measured and inserted into an
`ArrayList` called `generationTimes`. The reason for this is because to produce
the most accurate final results, the same `IntBST` that is used in the first
trial of the preorder traversal will be used in the first trial for the inorder
traversal, and so on. The 10 `IntBST`s are different, but each traversal type
shares the same IntBST for any one trial.

Since all traversal types use the same 10 `IntBST`s for their trials, their
unique runtimes can be determined by adding the average of the elements in
`generationTimes` to the time taken to write to their respective files, where
the actual traversals occur. This is precisely what I have done.
	
## Output
The program prints the resultant average times for each traversal method over
10 trials, each over a pseudo-randomly populated BST, to the console.

### My Thoughts
While I don't believe this program is complex enough to make a precise empirical
conclusion, it seems that the traversal methods perform virtually identically
with the sole exception of level-order. The proximal results for pre-order,
in-order, and post-order traversal are congruent with their shared
time-complexity of O(n). Level-order has a time-complexity of O(n) as well, but
the necessary overhead of using a queue combined with the lack of an elegant
recursive implementation tips the scales a bit.
