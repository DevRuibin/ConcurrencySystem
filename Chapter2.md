# Primitive Processes

## Finite State Machines(FSM)

our model for processes:

### states

states: 0, 1, 2, 3, 4

initial state: 0

### Actions

stop, start, etc.

atomic(do not interrupt each other)

## LTS

It is a kind of FSM. State are opaque which means there is no information on the value of variables, registers, memory, buffers,...

Transitions are transparent. Actions can show data.

Transitions: (0, stop, 1), (0, start, 2), etc

- notation: 0 -stop->1, 0 -start->2.

Executions: 0 -start->2-run->2-yield->3...

- a sequence of successive transitions from the initial state.
- finite or infinite, full or not full

### Example: Light Switch

Two states:

- 0
- 1

Two actions:

- on
- off

Two transitions:

- 0 -on->1
- 1 -off->0

One infinite full execution

 on -> off -> on -> ...

## LTS Layout

The meaning of the LTS does not depend on layout.

## From LTS to FSP

Graphical representation of LTS is easy to build and easy to understand, but for small systems only

We want descriptions for large LTS that are

- easy to build
- easy to understand
- easy to process

FSP is a language for Finite State Processes.

### Example: Light Switch in FSP

```FSP
SWTICH = OFF,
OFF = (on -> ON),
ON  = (off -> OFF).
```

First observations:

- Three process definitions Switch, OFF, ON
- Process bodies made of actions and references to other processes.
- Mutually recursive definitions
- No loops.

## Action Prefix

> IF x is an action and P is a process
> THEN (x -> P) is a process
> that first engages in action x
> then behaves exactly as described by P.

Action identifiers start with a lowercase letter.
Process identifiers start with an UPPERCASE letter.

## A Halting Process

ONESHOT = (once -> stop).

Pre-defined process STOP engages in no action.
ONESHOT engages in actoin oce then stops.

## Process Definition Scopes

OFF, ON definitions are local to SWITCH

The main process identifier SWITCH is visible in the whole FSP program.

Auxiliary process identifiers OFF, ON are visiable in the bodies of SWITCH, OFF, ON only.

## Action Sequences

If (off -> OFF) is a process and on is an action then (on -> (off -> OFF)) is a process. Intermediate parentheses may be dropped.

## Choice

> IF x, y are actions, P, Q are processes,
> THEN (x -> P | y -> Q) is a process that first enganges in action x or y then behaves like P if the action was x or like Q if the action was y.

All alternatives must start with an action prefix. (P | y -> Q) is not a vaild process.

## Non-determinism

In general, a process is non-deterministic IFF it can produce different results given the same inputs.

In LTS or FSP, an LTS state or FSP process is non-deterministic IFF it has different outgoing transitions for the same action.

In particular, non-deterministic processes can be defined using the same action in different alternatives.

Any finite state machine can be defined in FSP.

## indexed actions

```FSP
BUFF = (in[i:0..3] -> out[i] -> BUFF).
```

Structed action labels in.0, in.1, etc...

Indexed action range prefix in [i:0..3]

- allow any in.\<n> for <n\> in 0..3
- binds i to \<n>

Indexed action prefix out[i]

- allow out.\<n> where \<n> is the value of expression i.

1. Action indices are a purely syntactic convenience.
2. Everything is expended into an enumeration of individual actions in the LTS
3. An action prefix indexed with a range is a choice.

## indexed Processes

1. Indices allowed on auxiliary processes only.
 main processes may have parameters, see later.
2. Actions and processes can have muyltiple indices.
3. Indices can be arithmetic expressions.

## process parameters

- parameters allowed on main process only.
- Mandatory default value.
- Scope: the whole definition
- used when composing processes.

## Sets of actions

- Action can be grouped into sets.
- Action sets can be declated.
- Action labels can be used as indices.

## Guarded actions

> IF x is an action, P is a process, B is an expression THEN (when B x -> P) is a process that may engage in action x followed by P if and only if the value of B is true.

## Successful Termination

STOP can perform no actions, corresponds to deadlock.

END is a base process which can perform no actions, corresponds to terminiation.

A process terminates IFF it reaches END

A sequential process = a process that can terminate.

## Sequential Composition: RULE

> IF P is a sequential process, Q is a process
> THEN P;Q is a process, that behaves like P then bahaves like Q when P terminates.

If Q is a sequential process, then P;Q is sequential process. Q can itself be a sequential composition.
