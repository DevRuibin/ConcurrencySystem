# Semantics

## Why Semantics?

Consider the following four scenarios

```FSP
(a -> STOP) and (a->STOP | a -> STOP)
(a -> (b -> STOP | c -> STOP)) and (a->b->STOP | a->c->STOP)
(a -> STOP || b -> STOP) and (a -> b -> STOP | b -> a -> STOP)
(a->STOP) and (i -> a -> STOP) \ {i}
```

Should these be considered the same? Or is one more sepcified/detailed/restricted than the other?

When are processes P and Q equivalent? Or when is Q is a refinement of P?

## Formal Semantics

* We have a class of FSP programs: Exp
    * More specifically, FSP process expressions
    * Defined by the synatx of FSP
* We need three things:
    1. A semantic domain: DOM
        * To represent the meaning of FSP programs
        * Traces, LTS
    2. A semantic map: sem: Exp => DOM
        * gives a semantics to every program
    3. One or more semantic relations: <->
        * Defined over DOM
        * Equivalence, pre-oreder, order...

## Syntax

Defined by a formal grammar(BNF)

$Exp^+$ is the set of all FSP process definitions, including primitive processes and composite processes.

$Exp^+$ contains syntactic objects

### Syntax Sugar

Some constructs defined by syntactic reduction. "Syntax sugar", does not really extend the expressivity

Exp is the set of all FSP basic process definitions

* Exp also contains syntactic objects
* With no syntax sugar
* Exp subset of $Exp^+$

## Actions

* A universal set of observable actions: Act
    * Act is infinte
    * Contains all possible action labels of all possible programs
    * For any process expression E, its alphabet $\alpha (E) \subseteq Act$ is finite
* One internal action: $\tau (\tau \notin Act)$
* The set of all actions: $Act \cup {\tau}$

## What semantics for FSP

* Wish list:
    * Support verification of relevant properties
    * Unambiguous: one meaning for every process
    * Relate things that are similar
    * Distinguish things that are different
* We will consider two semantics
    * Traces(briefly)
    * LTS(in detail)
* Both focus on observable behaviour
    * Abstract away from internal details
        * unobservable actions
        * concurrency structure
        * state variables
        * etc.

## Traces

A trace is a finite sequence of observable actions

* e.g. on off on off
* The empty trace is noted $\epsilon$(epsilon)
* Observable behaviour, thus no internal action!
* In this context, we consider only finite traces
* Infinite executions give all finite prefixes

$T=Act^*$ is the set of all traces

$2^T$ is the set of all sets of traces

## Traces Semantics

We define a semantic map:

$$tr: Exp \rightarrow 2^T$$

### Denotational semantics

* Defines a denotation for each process as a function of the denotations of its subprocesses

### Trace Semantic: Examples

```FSP
SWITCH = (on -> off -> SWITCH)
```

tr(SWITCH) = {$\epsilon$, on, off, on, off, on, ...}

```FSP
CONVERSE = (think -> talk -> STOP).
ITCH = (scratch -> STOP).
||CONVERSE_ITCH = (ITCH || CONVERSE).
```

tr(CONVERSE_ITCH) = {$\epsilon$, think, think talk, think talk scratch, think scratch, think scratch talk, scratch, scratch think, scratch think talk}

### Trace Semantics for Basic FSP

Some examples:

$$
\begin{align*}
tr(STOP) &= \{\epsilon\}\\
tr(a -> E) &= \{\epsilon\} \cup \{a.\sigma \in tr(E)\}\\
tr(E_1 | E_2) &= tr(E_1) \cup tr(E_2)\\
tr(E_1 || E_2) &= \{interleavings \: of \: \sigma _1\: and\: \sigma _2 \:synchronized\: over\: A\: |\: \sigma _1 \in tr(E_1), \sigma _2 \in tr(E_2), A = \alpha(E_1) \cap \alpha(E_2)\}
\end{align*}
$$
