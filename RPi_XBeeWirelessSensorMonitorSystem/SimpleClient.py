#####################################
#   File Name : SimpleClient.py   
#   Author      : NayLA  
#   Date         : 31/05/2016
#####################################

import socket               # Import socket module

s = socket.socket()         # Create a socket object
host = socket.gethostname() # Get local machine name
port = 8081               # Reserve a port for your service.

#s.bind(('192.168.1.46', port))   # Bind to IP & port
s.connect((host, port))
print s.recv(1024)
s.close                     # Close the socket when done
s.shutdown
