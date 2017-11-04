# Echo server program
import socket
import os
import time
HOST = ''                 # Symbolic name meaning all available interfaces
PORT = 50007              # Arbitrary non-privileged port
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST, PORT))
print "listening..."
s.listen(1)
global conn
conn, addr = s.accept()
#s.settimeout(2.0)
print 'Connected by', addr
global data

def ping():
    #data = os.system("ping -t 2000 -c 1 10.19.89.50")
    conn.send('s\n')
    data = conn.recv(1024)
    print "data: "+str(data)
    if not data: return False
    elif 'c\n' in data: return True
    else: return False

while 1:
    time.sleep(0.1)
    print "yoe"
    conn.send('s\n')
    data = conn.recv(1024)
    print "data: "+str(data)
    if not data: recieve = False
    elif 'c\n' in data: recieve = True
    else: recieve = False
    print str(recieve)
    if recieve:
        print "ff"
        #data = conn.recv(1024)
        #if "ye" in data: conn.send("hehe\n")
    else:
        print "gege"
        conn.close()
        print "listening..."
        s.listen(1)
        conn, addr = s.accept()
        print "connected!"
        #data = conn.recv(1024)
    print "---------"

conn.close()
