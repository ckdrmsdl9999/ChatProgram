ChatProgram
===========

Cross platform basic chat program written in Java, with Table Top RPGs in mind.

Currently has a "Functional" client and server, packaged together

First thing to do is connect to a server (file >> connect) give it the server ip/ fqdn, currently ports are unsupported (to be added at a later date)
Make sure that traffic can be sent on port 5000, that is the current (and future default) port

Current features include 
d10 dice rolling (other dice to be implemented)
display name changing
player whispering (using name given at logon, not display)


More features will come, including interactive character sheets, gm heads up panel (hp/ mana /etc tracker, linked to clients) and a game map, and possible some others ive not thought of yet.

All features in development are currently focused on White Wolf's World of Darkness series (mainly the Changeling line for the moment)



Useage

Client
java -jar ./ChatProgram.jar

Client + Server
java -jar ./ChatProgram.jar server
Client + Server (no gui)
java -jar ./ChatProgram.jar server nogui

Server
java -jar ./ChatProgram.jar serveronly

Server (no gui)
java -jar ./ChatProgram.jar serveronly nogui
