# batallaNavalDistribuida

this is a dummy project made as homework, the idea behind is you have a client and a servert that connect over tcp. the server 
generates a terrain of 10x10 position and with threads creates 4 boats(each boat with 1 thread), the boats should be generate 
without collision(NOT one over the other they can be beside each other). after the boats are generate the client send a position 
x,y if is correct the server respons correct and how many are left of that boat that continues until the person wins or the tryes
gets to 10(in wich case says how many hit the target).

## Getting Started

just copy the src folder in at project of your own an run it with your prefer ide(it was made with netbeans)
after that run the files FIRST the one called HilosSockets.java (this is the server) and then the client Cliente.java 

### Prerequisites

java and preferible and ide like netbeans but you can also run it by console

