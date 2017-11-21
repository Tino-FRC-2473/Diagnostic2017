import matplotlib.pyplot as plt
import numpy as np
import socket
import select

HOST = '172.22.11.2'
PORT = 50007
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST,PORT))
d.setblocking(0)

time = []
rpm = []
current = []
integral1 = []
integral2 = []

ready = select.select([mysocket], [], [], timeout_in_seconds)
while True:
		if ready[0]:
			data = str(s.recv(1024))
			dataList = data.split()
			t = dataList[0]
			r = dataList[1] 
			c = dataList[2]
			i1 = datalist[3]
			i2 = dataList[4]
			time.append(t)
			rpm.append(r)
			current.append(c)
			integral1.append(i1)
			integral2.append(i2)

plt.figure(0)
plt.plot(time,rpm)
plt.show()

plt.figure(1)
plt.plot(time,current)
plt.show()

plt.figure(2)
plt.plot(time,integral1)
plt.show()

plt.figure(3)
plt.plot(time,integral2)
plt.show()
