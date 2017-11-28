import matplotlib.pyplot as plt
import numpy as np
import socket
from pynput.keyboard import Key, Listener

HOST = '172.22.11.2'
PORT = 50007
STOP_KEY = 'S'
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST,PORT))
d.setblocking(0)

pressed = False;

time = []
rpm = []
current = []
integralT = []
integral1 = []
integral2 = []

while True:
	try:
		data = s.recv(1024)
		dataList = data.split()
		t = dataList[0]
		r = dataList[1] 
		c = dataList[2]
		iT = dataList[3]
		i1 = datalist[4]
		i2 = dataList[5]
		time.append(t)
		rpm.append(r)
		current.append(c)
		integralT.append(iT)
		integral1.append(i1)
		integral2.append(i2)
	except KeyboardInterrupt:
		break

plt.figure(0)
plt.plot(time,rpm)
plt.show()
plt.savefig()

plt.figure(1)
plt.plot(time,current)
plt.show()
plt.savefig()

plt.figure(2)
plt.plot(time,integralT)
plt.show()
plt.savefig()

plt.figure(3)
plt.plot(time,integral1)
plt.show()
plt.savefig()

plt.figure(4)
plt.plot(time,integral2)
plt.show()
plt.savefig()
