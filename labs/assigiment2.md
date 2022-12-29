# Concurrent System: Modelling process

## 1 Composing process

1. Start from the client-server example (File / Examples / chapter3 / client_server).

   ```FSP
   CLIENT = ( call -> wait -> continue -> CLIENT ).
   SERVER = ( request -> service -> reply -> SERVER ).
   || CLIENT_SERVER = ( CLIENT || SERVER ) / { call / request , reply / wait }.
   ```

   Modify it in order to model two new behaviours. The client has now a timeout that is started after a new call and if the timeout expires, the client is again ready to make a new call. The server can now have some failure that occurs whenever it receives a new request. If such a failure occurs, the service is not performed and the server is again ready to receive new request. 

   * Build the parallel composition of the new CLIENT and SERVER. 

     ```FSP
     CLIENT = ( call -> (wait -> continue -> CLIENT | timeout ->CLIENT) ).
     SERVER = ( request -> (service -> reply -> SERVER | failure -> SERVER)).
     || CLIENT_SERVER = ( CLIENT || SERVER ) / { call / request , reply / wait }.
     
     ///////////////////////////////////////
     CLIENT = ( call -> (wait -> continue -> CLIENT | timeout -> CLIENT) ).
     SERVER = ( request ->( service -> reply -> SERVER |failure -> SERVER)).
     ||CLIENT_SERVER = ( CLIENT || SERVER ) / { call / request , reply / wait }.
     ```

     

   *  The composition exhibits a deadlock. Can you identify in which situation (run) such a deadlock occurs ?

     `call -> service -> timeout && call -> timeout -> service`

   * How can you modify the model(s) in order to avoid deadlocks ? Is your solution consistent with the real intuition of the CLIENT-SERVER model ? Explain. 

     ```FSP
     CLIENT = ( call -> (wait -> continue -> CLIENT | timeout ->CLIENT) ).
     SERVER = ( request -> (service -> (reply -> SERVER | server_timeout -> SERVER) | failure -> SERVER)).
     || CLIENT_SERVER = ( CLIENT || SERVER ) / { call / request , reply / wait }.
     ```

     

   * Now, we would like to get an abstract version of the system, so, hide timeout and failure. What can you observe ?

     ```FSP
     CLIENT = ( call -> (wait -> continue -> CLIENT | timeout ->CLIENT) ).
     SERVER = ( request -> (service -> (reply -> SERVER | server_timeout -> SERVER) | failure -> SERVER)).
     || CLIENT_SERVER = ( CLIENT || SERVER ) / { call / request , reply / wait } \ {timeout, failure, server_timeout}.
     ```

     *We get a system in which we can abort it at any point of time.*

2. A garbage dispatcher system is composed of several parts operating concurrently. There are two kind of garbage : `paper` and `other`. The dispatcher receives a garbage (`get.paper` or `get.other`) and can throw the garbage in the corresponding bin according to the type of garbage (`throw.paper` or `throw.other`). Before throwing a garbage in the corresponding bin, the dispatcher can empty the two bins (`empty`) simultaneously. The two bins have the same limited capacity (defined by a constant). A bin can accept a garbage (`throw.paper` or `throw.other` depending on the bin) whenever its maximal capacity is not reached. It can be emptied (empty) at any time. Model the garbage dispatcher system as an FSP process SYSTEM which is the composition of a DISPATCHER process, two processes for the bins (PAPER_BIN and OTHER_BIN) and a USER process which will feed the dispatcher with garbages. The alphabet should be: {empty, {get, throw}.{other, paper}}.

   ```FSP
   const Capacity = 3
   set Garbage = {paper, other}
   
   DISPATCHER = (get[g:Garbage] -> (throw[g] -> DISPATCHER | empty -> throw[g] -> DISPATCHER)).
   BIN(Kind='paper, Capacity=Capacity) = BIN[0],
   BIN[current:0..Capacity] = (when(current < Capacity) throw[Kind] -> BIN[current+1] | empty -> BIN[0]).
   
   ||PAPER_BIN(C=CAPACITY) = BIN('paper, C).
   ||OTHER_BIN(C=CAPACITY) = BIN('other, C).
   
   USER = (get[Garbage] -> USER).
   
   ||SYSTEM(Capacity=Capacity) = (DISPATCHER || PAPER_BIN(Capacity)||OTHER_BIN(Capacity) || USER).
   
   ////////////////////////////////////////////////////////////////////////////////////////
   
   set Garbage = {paper, other}
   const Capacity = 5
   
   DISPATCHER = (get[g:Garbage] -> (throw[g] -> DISPATCHER | empty -> throw[g]->DISPATCHER)).
   
   BIN(Kind='paper, Capacity=Capacity) = BIN[0], 
   BIN[i:0..Capacity] = (when(i<Capacity) throw[Kind]-> BIN[i+1] | empty->BIN[0]).
   
   ||PAPER_BIN = BIN('paper, Capacity).
   ||OTHER_BIN = BIN('other, Capacity).
   
   USER = (get[g:Garbage] -> USER).
   
   ||SYSTEM = (PAPER_BIN || OTHER_BIN || USER || DISPATCHER).
   
   ```
   
   