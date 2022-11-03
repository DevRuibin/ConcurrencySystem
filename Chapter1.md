# Introduction

Concurrent computer system have many processes which interacting together and interacting with their environment.

How do you validate?

- Testing
- Modelling and Analysis

## Concurrent programs

Sequential program: operations occur in a specified sequential order.
	- A thread of execution
	- incl. function/procedure/method calls.

Concurrent Program: multiple operations may occur at the same time.
	
	- Or without a specified order
	- Even if the actual execution is purely sequential
	- Expressed as multiple threads.

Concurrent Programming Pros & Cons

1. Pros
	* Inherently distributed systems
	* Perform multiple tasks, server multiple users
	* Control multiple devices independently
	* off-load time-consuming tasks to background threads.

2. Cons
	* More complexity
	* Less predictability: in which order will things occur.

## mathematical models

specify the system and their properties in a precise way, with a formal defination of the semantics of these sepcifications.

## Computerized analysis

Denote those specifications in a machine-readable language, use automated tools to analyse them.


## Elements of Modelling and Analysis

Modelling: a language to describe concurrent systems and their properties.

Semantic: a formal defination of what the model means

properties: facts that can be checked on the models.

Verification: techinques and tools to mechianically check the properties.


## FSP

It is a modelling language not a programming language.

- abstracts away from data details, resources, user interactions...
- Focuses on the order in which actions take place.

## Semantics: labelled transition system

Finate state mechines with labels on transitions. 

- That is graphs
- No data, no variables, only lebels

Semantics maps every FSP process to a LTS

Graphically reperesentable

Can be compared

Can be minimized


Properties: Temporal Logic:

Linear temporal logic(LTL): a logic for talking about sequences of events.
