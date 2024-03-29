

# Review

## Appendix A

### A.1 Processes

A process is defined by one or more local processes separated by commas. The definition is terminated by a full stop. STOP and ERROR are primitive local processes.

#### Example

```FSP
Process = (a -> Local),
Local = (b -> STOP).
```

| operator             | explanation                                                  |
| -------------------- | ------------------------------------------------------------ |
| Action Prefix ->     | If x is an action and P a process then (x->P) describes a process that initially engaged in the action X and then behaves exactly as described by P. |
| Choice \|            | If x and y are actions then (x->P\|y->Q) describes a process which initially engaged in either of the actions x or y. After the first action has occurred, the subsequent behavior is described by P if the first action was x and Q if the first action was y. |
| Guarded Action       | The choice (when B x->P\|y->Q) means that when the guard B is true then the actions x and y are both eligible to be chosen, otherwise if B is false then the action x and Q cannot be chosen. |
| Alphabet Extension + | The alphabet of a process is the set of actions in which it can engage. P + S extends the alphabet of the process P with the actions in the set S. |

### A.2 Composite Processes

A composite process is the parallel composition of one or more processes. The definition of a composite process is preceded by ||.

#### Example

```FSP
||Composite = (P || Q)
```

| operator               | explanation                                                  |
| ---------------------- | ------------------------------------------------------------ |
| Parallel \|\|          | If P and Q are processes then (P\|\|Q) represents the concurrent execution of P and Q |
| Replicator **forall**  | **forall**[i:1..N] P(i) is the parallel composition (P(1) \|\|...\|\|p(N)). |
| Process Labeling **:** | a:P prefixes each label in the alphabet of P with a.         |
| Process Sharing **::** | $\{a_1, ..., a_x\}::P$ replaces every label n in the alphabet of P with the labels $a_1.n, ..., a_x.n$. Further, every transition (n->Q) in the definition of P is replaced with transitions ($\{a_1.n, ..., a_x.n\}\rightarrow Q$) |
| Priority High **<<**   | \|\|C=(p\|\|Q)<<{a1, ..., an} specifies a composition in which the actions a1, ..., an have higher priority than any other action in the alphabet of P\|\|Q including the silent action tau. In any choice in this system which has one or more of of the actions a1,...an labeling a transition, the  transitions labeled with lower priority actions are discarded. |
| Priority Low >>        | \|\|C=(P\|\|Q)>>{a1, ..., an} specifies a composition in which the actions a1, ..., an have lower priority than any other action in the alphabet of P\|\|Q including the silent action tau. In any choice in this system which has one or more transitions not labeled by a1, ...m an, the transitions labeled by a1, ..., an are discarded. |

### A.3 Common opearators

The operator in below table may be used in the definition of both processes and composite processes.

| operator                 | explanation                                                  |
| ------------------------ | ------------------------------------------------------------ |
| Conditional if then else | The process if B then P else Q behaves as the process P if the condition B is true; otherwise it behaves as Q. If the else Q is omitted and B is false, then the process behaves as STOP |
| Re-labeling /            | Re-labeling is applied to a process to change the names of action labels. The general form of re-labeling is: $/\{newlabel_1/oldlable_1, ...newlable_n/oldlabel_n\}$ |
| Hiding \                 | When applied to a process P, the hiding operator $\{a_1, ..., a_x\}$ removes the action  names $a_1, ..., ax$ from the alphabet of P and makes these concealed actions *silent*. These silent actions are labeled **tau**. Silent actions in different processes are not shared. |
| Interface @              | When applied to a process P, the interface operator $@\{a_1, ..., a_x\}$ hides all actions in the alphabet of P not labeled in the set $a_1, ..., a_X$. |

### A.4 Properties

| Operator              | explanation                                                  |
| --------------------- | ------------------------------------------------------------ |
| Safety **Property**   | A safety **Property** P defines a deterministic process that asserts that any trace including actions in the alphabet of P is accepted by P. |
| Progress **progress** | **progress **P = $\{a_1, a_2, a_n\}$ defines a process property P which asserts that in an infinite execution of a target system, at least one of the actions $a_1, a_2, ..., a_n$ will be executed infinitely often. |



## Chapter 1

1. What is Concurrent computer system?

   Concurrent computer system have many processes which interacting together and interacting with their environment.

2. How do you validate?

   - Measurements: It means to observe a real system

   - Simulation: Build a model of the system and simulate it. We can specify the system and their properties in a precise way, with a formal definition of the semantics of these specifications.
   - Mathematical models: Build a mathematical model of the system.

3. What is sequential program?

   Its operations occur in a specified sequential order.

4. What is Concurrent program?

   There are multiple operations may occur at the same time, even without a specified order.

5. Can you say pros & cons of concurrent programming?

   1. Pros
      1. Inherently distributed systems
      2. Perform multiple tasks, server multiple users
      3. Control multiple devices independently
      4. off-load time-consuming tasks to background threads.
   2. Cons
      1. More complexity
      2. Less predictability: in which order will things occur?

6. Specify the elements of modeling and analysis

   1. Modeling: a language to describe concurrent systems and their properties.
   2. Properties: facts that can be checked on the models.
   3. Verification: techniques and tools to mechanically check the properties.
   4. Semantics:  a formal definition of what the model means.

7. Introduce FSP

   It is a modeling language not a programming language. It abstracts away from data details, resources, use interfaces.., focuses on the order in which actions take places.

8. What is Label transition system?

   Finite state machines with labels on transitions. It is not include data, variables, only includes labels.

9. What is Linear temporal logic?

   It is a logic for talking about sequences of events.



## Chapter 2

1. state

   A process is the execution of a sequential program. The state of a process at any point in time consists of the values of explicit variables, declared by the programmer, and implicit variables such as program counter and contents of data/address registers.

2. statement

   As a process executes, it transforms its state by executing statements. Each statement consists of a sequences of one or more atomic actions that make indivisible state changes.

3. transitions

   The execution steps.

4. Executions

   A sequence of successive transitions from the initial state.

5. Action prefix

   If x in an action and P is a process, then the action prefix (x -> P) describes a process that initially engages in the action x and then behaves exactly as described by P.

6. Choice

   If x and y are actions then (x->P|y->Q) describes a process which initially engages in either x or y. After the first action has occurred, the subsequent behavior is described by P if the first action was x and Q if the first action was y.

7. Non-determinism

   In general, a process is non-deterministic if and only if it can produce different results given the same inputs.

8. Guarded actions

   The choice (when B x->P|y->Q) means that when the guard B is true then the actions x and y are both eligible to be chosen, otherwise if B is false then the action x cannot be chosen.

9. What is a successful termination?

   STOP can perform no actions, corresponds to deadlock. END is a base process which can perform no actions, corresponds to terminiation.

   A process terminates if and only if it reaches END.

   For a concurrent sequential process, it terminates if and only if all concurrent components terminate.

   A sequential process means a process that can terminate.

10. Sequential composition

    If P is a sequential process and Q is a process then p;Q is a process that behaves like P then behaves like Q when P terminates.



## Chapter 3

1. Composite process: It means that several sequential processes active at the same time. In other words, it means that several threads of execution which may or may not actually execute at the same time.

2. Real concurrency VS Pseudo concurrency

   Real concurrency is actual simultaneous execution.

   Pseudo-concurrency is interleaved execution.

3. Modeling assumption

   1. Actions are atomic
   2. Processes execute at arbitrary speeds. A process can take an arbitrary long time before performing an action.
   3. All concurrent actions are interleaved.

4. Disjoint parallel composition:

   If P, Q are processes with disjoint actions then (P||Q) is a process whose executions are the interleavings of execution of P and Q.

5. Interaction

   If P, Q are processes with shared actions A then (P||Q) is a process whose executions are the interleavings of executions of P and Q synchronized on actions in A.

   Disjoint actions are interleaved and shared actions are synchronized. For shared actions, both processes perform the action simultaneously and the action cannot be performed by one process alone.

6. The alphabet of $\alpha P$ of a process is the set of actions in which P can engage.

   For primitive processes, the set of actions that the process can perform after expending all indices and parameters.

   For concurrent processes, the Union of the alphabets of the component processes: $\alpha (P||Q)=\alpha P \cup \alpha Q$

7. Parallel composition laws:

   1. Parallel composition is commutative
   2. Parallel composition is associative and therefore parentheses may be dropped.

8. Hiding and Interface

   Hiding an action: replacing that action with internal action actually

   Hiding: P\A hides all actions in A from Processes

   Interface: P@A hides all actions in ($$\alpha P-A$$) from P: shows only actions in Action

9. Internal actions:

   $\tau$ is never part of the alphabet of a process and therefore processes never synchronize on $\tau$

10.  setting priorities

    P<\<A gives higher priority to actions of A in P. Whenever an action of A is enabled, other actions are disabled.(including tau).

    p>\>A gives lower priority to action of A in P. Whenever another action is enabled(including tau), actions of A are disabled.

11. state space size

    ```FSP
    SWITCH = (on -> off -> SWITCH). // 2 states, 2 transitions
    ||TWO_SWITCH = ({a, b}:SWITCH). // 4 states, 8 transitions
    ||THREE_SWITCH = ({a, b, c}:SWITCH). // 8 states, 24 transitions
    ||SWITCHES = (s[i:1..N]:SWITCH). // pow(2, N) states, N * pow(2, N) transitions.
    ```

    

​	P has $$N_s$$ states and $$N_t$$ transitions. (a[1..k]:P): $$N_s^k$$ states and $$k.N_s^{k-1}.N_t$$ transitions.



## Chapter 4

1. formal semantics
   1. EXP: a class of FSP programs
   2. DOM: A semantic domain. To represent the meaning of FSP programs
   3. sem: A semantic map gives a semantics to every programs
   4. one or more semantic relations. It is defined over Dom
2. Trace: A trace is a finite sequence of observable actions.
3.   