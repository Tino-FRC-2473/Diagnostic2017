import matplotlib.pyplot as plt
import numpy as np
import socket
import sys
import csv
from pynput.keyboard import Key, Listener
import time

BASE_TIME = 1511843312
HOST = '10.24.73.61'
PORT = 50007
STOP_KEY = 'S'
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print 'trying to connect'
s.connect((HOST,PORT))
print 'connected'

times = []
rpm = []
current = []
startTime = 0
firstTime = True

while True:
	try:
		data = s.recv(1024)
		dataList = data.split()
		print 'dataList' + str(dataList)

		if firstTime
			startTime = int(dataList[1])
			startTime = False

		t = int(dataList[1]) - startTime
		r = dataList[2]
		c = dataList[3]
		sT = dataList[4]
		i1 = dataList[5]
		i2 = dataList[6]
		times.append(t)
		rpm.append(r)
		current.append(c)
	except KeyboardInterrupt:
		break

t = time.time() - BASE_TIME
print('saving data', t)
plt.savefig('rawRPM.png')
plt.savefig('rawCur.png')

txtFile = open('rawData.txt', 'w')

for i in range(len(times)):
    txtFile.write(str(times[i] + ' ' + str(rpm[i]) + ' ' + str(current[i]) + '\n');

txtFile.close()
quit()
