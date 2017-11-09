# Echo server program
import rospy
from std_msgs.msg import String
import sys, select, termios, tty, time
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
print 'Connected by', addr
global data
global coordstring
coordstring = ""

rospy.init_node('server')

def callback(data):
    global coordstring
    coordstring = str(data)[6:]
    #print coordstring

def get_coords():
    return str(coordstring)

def listener():
    rospy.Subscriber('/coordinates', String, callback)

def ping():
    #data = os.system("ping -t 2000 -c 1 10.19.89.50")
    conn.send('s\n')
    data = conn.recv(1024)
    print "data: "+str(data)
    if not data: return False
    elif 'c\n' in data: return True
    else: return False

listener()
while 1:
    time.sleep(0.1)
    recieve = ping()
    print str(recieve)
    if recieve:
        s.settimeout(2.0)
        try:
            data = conn.recv(1024)
        except timeout:
            print "wererewrwe"
	print coordstring
        if "function triggered" in data: conn.send("coordinates: "+str(coordstring)+"\n")
        if "done" in data: quit()
    else:
        print "gege"
        conn.close()
        print "listening..."
        s.settimeout(None)
        s.listen(1)
        conn, addr = s.accept()
        print "connected!"
    print "---------"

conn.close()
