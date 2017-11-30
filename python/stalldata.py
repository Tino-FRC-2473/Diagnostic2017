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
integralT = []
integral1 = []
integral2 = []
startTime = 0
firstTime = True

def writeCSV(arrX, arrY, name):
    with open(str(name) + '.csv', 'a') as csvfile:
        spamwriter = csv.writer(csvfile, quoting=csv.QUOTE_MINIMAL)
        [str(i) for i in arrX]
        spamwriter.writerow(arrX)
        [str(i) for i in arrY]
        spamwriter.writerow(arrY)

while True:
	try:
		data = s.recv(1024)
		dataList = data.split()
		print 'dataList' + str(dataList)

		if (firstTime) {
			startTime = int(dataList[1])
			startTime = False
		}

		t = int(dataList[1]) - startTime
		r = dataList[2]
		c = dataList[3]
		sT = dataList[4]
		i1 = dataList[5]
		i2 = dataList[6]
		times.append(t)
		rpm.append(r)
		current.append(c)
		sumT.append(sT)
		integral1.append(i1)
		integral2.append(i2)
	except KeyboardInterrupt:
		break

t = time.time() - BASE_TIME
print('saving data', t)

plt.figure(0)
plt.plot(times,rpm)
plt.savefig('rpm.png')
writeCSV(times, rpm, 'rpm')
sys.stdout.write('.')

plt.figure(1)
plt.plot(times,current)
plt.savefig('cur.png')
writeCSV(times, current, 'cur')
sys.stdout.write('.')

plt.figure(2)
plt.plot(times,sumT)
plt.savefig('smT.png')
writeCSV(times, sumT, 'smT')
sys.stdout.write('.')

plt.figure(3)
plt.plot(times,integral1)
plt.savefig('in1.png')
writeCSV(times, integral1, 'in1')
sys.stdout.write('.')

plt.figure(4)
plt.plot(times,integral2)
plt.savefig('in2.png')
writeCSV(times, integral2, 'in2')
sys.stdout.write('.\n')

quit()
