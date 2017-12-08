import numpy as np
import socket
import sys
import csv

HOST = '10.24.73.56'
PORT = 50505
STOP_KEY = 'S'
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print('trying to connect')
s.connect((HOST,PORT))
print('connected')

stringData = []
startTime = 0
firstTime = True

while True:
	try:
		data = s.recv(1024)
		print('data: ' + str(data))

		if firstTime:
			startTime = str(data)
			startTime = False

		stringData.append(str(data))
	except KeyboardInterrupt:
		break

txtFile = open('data/rawStringData.txt', 'w')
txtFile.seek(0)
txtFile.truncate()

for i in range(len(stringData)):
    txtFile.write(stringData[i])
txtFile.close()
quit()
