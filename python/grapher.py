import numpy as np
from scipy import integrate
import matplotlib.pyplot as plt
lines = open(raw_input("filename: ")+".txt").read().split('\n')

time_arr = []
rpm_arr = []
curr_arr = []

for line in lines:
    splitted = str(line).split(" ")
    time_arr.append(float(splitted[0]))
    rpm_arr.append(float(splitted[1]))
    curr_arr.append(float(splitted[2]))

plt.plot(time_arr, rpm_arr)
plt.savefig('rpm.png')
plt.close()
plt.plot(time_arr, curr_arr)
plt.savefig('curr.png')
plt.close()

rpm_int = integrate.cumtrapz(rpm_arr, time_arr, initial=0)
plt.plot(time_arr, rpm_int)
plt.savefig('rpm_int.png')
plt.close()

curr_int = integrate.cumtrapz(curr_arr, time_arr, initial=0)
plt.plot(time_arr, curr_int)
plt.savefig('curr_int.png')
plt.close()


avg_time_arr = []
avg_rpm_arr = []
avg_curr_arr = []

rolling_sum_rpm = 0
rolling_sum_curr = 0
for i in xrange(len(time_arr)):
    rolling_sum_rpm = rolling_sum_rpm + float(rpm_arr[i])
    rolling_sum_curr = rolling_sum_curr + float(curr_arr[i])
    if i % 5 == 0:
        avg_time_arr.append(time_arr[i])
        avg_rpm_arr.append(rolling_sum_rpm/5.0)
        avg_curr_arr.append(rolling_sum_curr/5.0)
        rolling_sum = 0;

plt.close()
plt.plot(avg_time_arr, avg_rpm_arr)
plt.savefig('sum10rpm.png')
plt.close()
plt.plot(avg_time_arr, avg_curr_arr)
plt.savefig('sum10curr.png')
