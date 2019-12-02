# Project - *Big Catch*

## Overview:

The program is designed to self-write a story about a number fishermen catching big fish by implementing Java Thread.

## Purpose:

This project is a practice of coding the creation and execution of threads, and with the use of the Thread class methods. In order to synchronize the threads you will have to use (when necessary), run( ), start( ), currentThread( ), getName( ), join( ), yield( ), sleep(time), isAlive( ), getPriority( ), setPriority( ), interrupt( ), isInterrupted( ), synchronized methods.

## Process:

### Background Story:

Lake Big Fish is known for it’s very large fish. There is a town on the shore called Breton. Near Breton is an island called Marrowind with a fishing hole where the large fish often come to feed. This fishing hole has become famous among amateur fishermen who come from all around to fish in the famous fishing hole. There is a shop in Breton that will stuff the prize and mount it for the fishermen so they can come home with proof of their “Big Catches”. The fishermen can also bring in lesser fish for money. And use very small fish for bait.

### The conditions of ending the program: 

Every **fisheman (num_fm)** will leave once he has at least one “Big Catch” and enough money to pay for his trip, **vacation_cost**.

### Steps: 

1. A fisherman that has not achieved his “Big Catch” or not recouped his vacation_cost will travel to the island to get his turn at the fishing hole. Fishermen gather on the island of Morrowind and wait (simulated by sleeping a long time) for the fishing hole to be available. 
2. The fishing hole is guarded by a Ranger. The ranger randomly interrupts one of the fishermen (use **interrupt()** and **isInterrupted()**). The ranger allows the fishermen access to the fishing hole and allowed one turn to fish. If the fisherman gets no fish, the Ranger allows the fisherman to increase his priority for a very short time and fish one more time. After that time at the fishing hole, the fisherman will immediately reset his priority back to the default value and will allow another fisherman to use the fishing hole (**use yield( )**). 
3. If the fisherman is successful he will receive a random fish (bait, 10, 20,30,40,50 or 200 pounds fish). If the fisherman does not have at least 10 pounds of fish he must wait for the ranger to give him access to the fishing hole again. When he has a fish big enough (above 10 pounds) to cash or mount in for money he takes a boat to the town and goes to the shop (use **sleep()** to simulate the boat trip). 
4. When he gets to the shop he sets his variable **needs_help(i)** to true and **busy waits** until a clerk is ready to help him. There are **num_ca**, customer associates but only one line of fishermen. A customer associate should pick the next waiting fisherman in a mutual exclusion fashion (this can be done from inside of a synchronized method) and in the FCFS order. The fisherman gets his mounted fish (above 200 pounds) or his money for the other fish ($0.75 per pound of fish).
5. If the fisherman achieves his goal of a big fish and vacation_cost. He will go home. However, each fisherman must join the previous fisherman in sequence. For example, in case that fisherman (i+1) is alive, fisherman (i) will join fisherman (i+1) (use **isAlive( )** and **join( )**). The last fisherman to leave for home (fisherman(0) ) will let the Ranger know that it is time for him to terminate. The customer associates will terminate as well.

## Simple Flowchart:

<img src='https://github.com/lxy878/Big_Catch/blob/master/flowchart.png' width='400' height='300' alt='cralwer page' />

## Demo Output:

[1]Fisherman[0]: waiting for fishing<br>
[1]Fisherman[1]: waiting for fishing<br>
[1]Fisherman[2]: waiting for fishing<br>
[1]Fisherman[3]: waiting for fishing<br>
[2]Fisherman[4]: waiting for fishing<br>
[2]Fisherman[5]: waiting for fishing<br>
[2]Assoicate[0]: no fisherman is in the queue<br>
[2]Assoicate[1]: no fisherman is in the queue<br>
[3]Ranger: picked Fisherman[4]<br>
[3]Fisherman[4]: sleep interrupted<br>
[3]Fisherman[4]: starts fishing<br>
[3]Fisherman[4]: caught 30 pounds fish<br>
[3]Ranger: picked Fisherman[1]<br>
[3]Fisherman[4]: is taking a boat Trip<br>
[4]Fisherman[1]: sleep interrupted<br>
[4]Fisherman[1]: starts fishing<br>
[4]Fisherman[1]: caught 30 pounds fish<br>
[4]Fisherman[1]: is taking a boat Trip<br>
[4]Ranger: picked Fisherman[2]<br>
[5]Fisherman[2]: sleep interrupted<br>
[5]Fisherman[2]: starts fishing<br>
[5]Fisherman[2]: caught 30 pounds fish<br>
[5]Fisherman[2]: is taking a boat Trip<br>
[5]Ranger: picked Fisherman[0]<br>
[5]Fisherman[0]: sleep interrupted<br>
[5]Fisherman[0]: starts fishing<br>
[5]Fisherman[0]: caught 200 pounds fish<br>
[6]Fisherman[0]: is taking a boat Trip<br>
[6]Ranger: picked Fisherman[5]<br>
[6]Fisherman[5]: sleep interrupted<br>
[6]Fisherman[5]: starts fishing<br>
[6]Fisherman[5]: caught 5 pounds fish<br>
[6]Fisherman[5]: waiting for fishing<br>
[6]Ranger: picked Fisherman[5]<br>
[6]Fisherman[5]: sleep interrupted<br>
[6]Fisherman[5]: starts fishing<br>
[7]Fisherman[5]: caught 5 pounds fish<br>
[7]Fisherman[5]: waiting for fishing<br>
[7]Ranger: picked Fisherman[3]<br>
[7]Fisherman[3]: sleep interrupted<br>
[7]Fisherman[3]: starts fishing<br>
[7]Fisherman[3]: caught 5 pounds fish<br>
[7]Fisherman[3]: waiting for fishing<br>
[7]Ranger: picked Fisherman[5]<br>
[7]Fisherman[5]: sleep interrupted<br>
[8]Fisherman[5]: starts fishing<br>
[8]Fisherman[5]: caught 0 pounds fish<br>
[8]Fisherman[5]: tries one more time<br>
[8]Fisherman[5]: starts fishing<br>
[8]Fisherman[5]: caught 40 pounds fish<br>
[8]Fisherman[5]: rested<br>
[8]Ranger: picked Fisherman[3]<br>
[8]Fisherman[5]: is taking a boat Trip<br>
[8]Fisherman[3]: sleep interrupted<br>
[8]Ranger: no fisherman comes to fish<br>
[8]Fisherman[3]: starts fishing<br>
[8]Fisherman[3]: caught 30 pounds fish<br>
[8]Fisherman[3]: is taking a boat Trip<br>
[505]Assoicate[0]: no fisherman is in the queue<br>
[505]Assoicate[1]: no fisherman is in the queue<br>
[512]Ranger: no fisherman comes to fish<br>
[1010]Assoicate[0]: no fisherman is in the queue<br>
[1010]Assoicate[1]: no fisherman is in the queue<br>
[1016]Ranger: no fisherman comes to fish<br>
[1510]Assoicate[0]: no fisherman is in the queue<br>
......<br>
[2516]Assoicate[0]: no fisherman is in the queue<br>
[2530]Ranger: no fisherman comes to fish<br>
[3009]Fisherman[0]: is walking into the shop<br>
[3009]Fisherman[2]: is walking into the shop<br>
[3009]Fisherman[4]: is walking into the shop<br>
[3009]Fisherman[3]: is walking into the shop<br>
[3010]Fisherman[3]: is standing into the queue<br>
[3009]Fisherman[1]: is walking into the shop<br>
[3009]Fisherman[5]: is walking into the shop<br>
[3010]Fisherman[1]: is standing into the queue<br>
[3010]Fisherman[4]: is standing into the queue<br>
[3010]Fisherman[2]: is standing into the queue<br>
[3010]Fisherman[0]: is standing into the queue<br>
[3010]Fisherman[5]: is standing into the queue<br>
[3022]Assoicate[0]: is serving Fisherman[0]<br>
[3022]Assoicate[1]: is serving Fisherman[2]<br>
[3034]Ranger: no fisherman comes to fish<br>
[3515]Fisherman[0]: mounted 200 pounds fish<br>
[3515]Fisherman[0]: is leaving the shop<br>
[3515]Fisherman[0]: waiting for fishing<br>
[3518]Fisherman[2]: cashed out 30 pounds fish for $22.5<br>
[3518]Fisherman[2]: is leaving the shop<br>
[3518]Fisherman[2]: waiting for fishing<br>
[3526]Assoicate[1]: is serving Fisherman[4]<br>
[3526]Assoicate[0]: is serving Fisherman[3]<br>
[3535]Ranger: picked Fisherman[0]<br>
[3535]Fisherman[0]: sleep interrupted<br>
[3535]Fisherman[0]: starts fishing<br>
[3535]Fisherman[0]: caught 0 pounds fish<br>
[3535]Fisherman[0]: tries one more time<br>
[3535]Fisherman[0]: starts fishing<br>
[3535]Fisherman[0]: caught 20 pounds fish<br>
[3535]Fisherman[0]: rested<br>
[3535]Ranger: picked Fisherman[2<br>]
[3535]Ranger: no fisherman comes to fish<br>
[3536]Fisherman[2]: sleep interrupted<br>
[3536]Fisherman[2]: starts fishing<br>
.....<br>
[39791]Fisherman[5]: cashed out 50 pounds fish for $37.5<br>
[39791]Fisherman[5]: is leaving the shop<br>
[39791]Fisherman[0]: is standing into the queue<br>
[39791]Fisherman[5]: joins to Fisherman[4]<br>
.....<br>
[51849]Fisherman[4]: cashed out 40 pounds fish for $30.0<br>
[51849]Fisherman[4]: is leaving the shop<br>
[51849]Fisherman[4]: joins to Fisherman[3]<br>
......<br>
[52856]Fisherman[2]: is walking into the shop<br>
[52856]Fisherman[3]: cashed out 30 pounds fish for $22.5<br>
[52856]Fisherman[3]: is leaving the shop<br>
[52856]Fisherman[2]: is standing into the queue<br>
[52856]Fisherman[3]: joins to Fisherman[2]<br>
......<br>
[53361]Fisherman[2]: cashed out 40 pounds fish for $30.0<br>
[53361]Fisherman[2]: is leaving the shop<br>
[53361]Fisherman[2]: joins to Fisherman[1]<br>
.......<br>
[59402]Fisherman[1]: cashed out 40 pounds fish for $30.0<br>
[59402]Fisherman[1]: is leaving the shop<br>
[59402]Fisherman[1]: joins to Fisherman[0]<br>
.......<br>
[64437]Assoicate[1]: is serving Fisherman[0]<br>
[64889]Assoicate[0]: no fisherman is in the queue<br>
[64923]Fisherman[0]: cashed out 30 pounds fish for $22.5<br>
[64923]Fisherman[0]: is leaving the shop<br>
[64923]Fisherman[0]: calls the assoicate to go home<br>
[64923]Fisherman[0]: calls the ranger to go home<br>
[64923]Fisherman[0]: is going home<br>
[64923]Fisherman[1]: is going home<br>
[64923]Fisherman[2]: is going home<br>
[64923]Fisherman[3]: is going home<br>
[64923]Fisherman[4]: is going home<br>
[64923]Fisherman[5]: is going home<br>
[64939]Ranger: is going home<br>
[65390]Assoicate[0]: is going home<br>
[65390]Assoicate[1]: is going home<br>
