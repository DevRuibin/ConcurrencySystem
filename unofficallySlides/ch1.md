# Concepts, Models and Programs

## What is a concurrent program?

A sequential program has a single thread of control

A concurrent program has multiple threads of control allowing it perform multiple computations in parallel and to control multiple external activities which occur at he same time.

## Concurrent and Distributed Software?

Single machine -> shared memory interactions

Multiple machines -> network interactions

## Why Concurrent Programming?

* Performance gain from multiprocessing hardware
* Increased application throughput: avoid polling
* Increased application responsiveness
* More appropriate structure

## Concurrency is widespread but error prone

A very simple example:

* Process 1: x := x + 1
* Process 2: x := x - 1

Single line instruction are generally not atomic. Assuming read and write are atomic, the results depends on the order of read and write operations on x!

![c1p1](/home/ruibin/ConcurrencySystem/unofficallySlides/attachments/c1p1.png)

## Models for concurrent programming

Engineering is based on the use of simpler, abstract models for experimentation, reasoning and exhaustive analysis

### What is Abstraction?

* The act of withdrawing or removing something
* the act or process of leaving out of consideration one or more properties of a complex object so as to attend to others.
* a general concept formed by extracting common features from specific examples
* The process of formulating general concepts by abstracting common properties of instances.

### Why is abstraction import in software engineering

Once you realize that computing is all about constructing, manipulating, and reasoning about abstractions, it becomes clear that an important prerequisite for writing computer programs is the the ability to handle abstractions in a precise manner.

* Requirements: Elicit the critical aspects of the environment and the required system while neglecting the irrelevant.

* Design: Articulate the software architecture and component functionalities which satisfy functional and non-functional requirements while avoiding unnecessary implementation constraints.

* Programming: Use data abstraction and classes so as to generalize solutions.

* Abstract interpretation for program analysis: Map concrete domain to an abstract domain which captures the semantic for the purpose at hand.
