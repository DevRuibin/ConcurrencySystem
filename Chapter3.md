# Composite Processes

It means that several sequential processes active at the same time. In other words, It means that several threads of execution which may or may not actually execute at the same time.

Real concurrency = actual simultaneous execution.

Pseudo-concurrency = interleaved execution. E.g. multiple processes scheduled on a single processor.

## Durition of Action

Actions are atomic.

1. Transitions occur instantaneously, they take no time.
2. Time elapses "within states"
3. To model operations that take time, use two actions.


## Modelling assumption

1. Actions are atomic
2. Processes execute at arbitrary speeds
    A process can take an arbitary long time before performing an action.
3. All concurrent actions are interleaved.


## Interleaving VS Real parallelism

Because we ignore time(accsumption #2), if a and b can occur at the same time, they can also occur consecutively in either order, thus whether a and b can occur at the same time does't effect what we can say about concurrent exections.

## Disjoint Parallel Composition

```
If P, Q are processes with disjoint actions 
THEN (P || Q) is a process whose executions 
    are the interleavings of exections of P and Q
```

If P can do action a and then behave like P', Then (P||Q) can do action a and then behave like (P'||Q) and vice versa for Q

States of (P||Q) correspond to pairs.


## Concurrency VS Choices

```FSP
A = (a -> STOP).
B = (b -> STOP).
||P1 = (A||B).

P2 =
( a -> b -> STOP
| b -> a -> STOP ).
```

P1 and P2 describe the same LTS though built in different ways. At the LTS level, the fact that a and b are concurrent is only implicit in the possible ordering of actions.

## Interaction

Disjoint actions are interleaved and Share actions are synchronized. For shared actions, both processes perform the action simultaneously and the action cannot be performed by one process alone.

```FSP
IF P, Q are processes with shared actions A
THEN (P||Q) is a process whose executions 
    are the interleavings of executions of P and Q
    synchronized on actions in A.
```

If P can do unshared action a and then behave like P' Then (P||Q) can do action a and then behave like (p'||Q) and vice-versa for Q.

If P and Q can do shared acton a and then behave like P' and Q' respectively, Then (P||Q) can do action a and then behave like (p'||Q')

In a concurrent process (P||Q), an action a is shared between P and Q IFF a belongs to both alphabets of P and Q.
* actions in $$\alpha P\cap\alpha Q$$ are synchronized
* others are interleaved.


## Process Alphabet
The Alphabet $$\alpha$$P of a process is the set of actions in which P can engage.

### Alphabet: Primitive Processes

The set of actions that the process can perform after expending all indices and parameters.

### Alphabet: Concurrent Processes

The Union of the alphabets of the component processes. $$\alpha(P||Q) = \alpha P \cup \alpha Q$$.

## Parallel Composition Laws

1. Parallel Composition is commutative
2. Paralles Compositon is associative and therefor parentheses may be dropped.


## The Restroom Switch

```FSP
SWITCH = (on->off->SWITCH).
USER = (on->busy->off->USER).
||RESTROOM = ({a,b}:USER || {a,b}::SWITCH).
```

* Two users
* One switch. NB: the switch alone would allow a.on followed by b.off.


## Concurrent Sequential Process

Ternubates IFF all concurrent components terminate.

























































