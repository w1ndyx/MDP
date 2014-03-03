import serial
import bluetooth
import thread
import time
import socket
from collections import deque

# Define functions for the thread

def setIPComm ():
    UDP_IP = "192.168.44.1"
    UDP_PORT = 5143
    ipsock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    #make the port reusable
    ipsock.setsockopt (socket.SOL_SOCKET, socket.SO_REUSEADDR,1)
    ipsock.setsockopt (socket.SOL_SOCKET, socket.SO_BROADCAST,1)
    ipsock.bind((UDP_IP, UDP_PORT))
    data, pcaddr = ipsock.recvfrom (1024)
    print "conneted", pcaddr
    return ipsock, pcaddr

def setSerComm ():
    sersock = serial.Serial('/dev/ttyACM0', 115200) # Establish the connection with Arduino
    return sersock

def setBTComm ():
    btaddr = "08:60:6E:A4:E4:D4"
    port = 3
    btsock=bluetooth.BluetoothSocket( bluetooth.RFCOMM )
    btsock.connect((btaddr, port))
    return btsock

def ipWrite (threadName, delay):
    stop_flag = 0
    while stop_flag == 0:
        time.sleep (delay)
        print "len ipq" , len(ipq)
        if len(ipq) >0:
            msg = ipq.popleft()
            ipsock.sendto (msg, pcaddr)
            print "%s: %s --msg: %s" % ( threadName, time.ctime(time.time()), msg)

def ipRead (threadName, delay):
    stop_flag = 0
    buf =1024
    while stop_flag == 0:
        time.sleep (delay)
        msg, addr = ipsock.recvfrom (buf)
        serq.append (msg)
        print "%s: %s--msg: %s" % ( threadName, time.ctime(time.time()),msg )

def serWrite (threadName, delay):
    stop_flag = 0
    while stop_flag == 0:
        time.sleep (delay)
        if len(serq) >0:
            msg = serq.popleft()
            sersock.write(msg)
            print "%s: %s --msg: %s" % ( threadName, time.ctime(time.time()), msg)

def serRead (threadName, delay):
    stop_flag = 0
    while stop_flag == 0:
        time.sleep (delay)
        msg = sersock.readline()
        ipq.append (msg)
        print "%s: %s--msg: %s" % ( threadName, time.ctime(time.time()),msg )

def btWrite (threadName, delay):
    stop_flag = 0
    while stop_flag == 0:
        time.sleep (delay)
        if len(btq) >0:
            msg = btq.popleft()
            btsock.send(msg)
            print "%s: %s --msg: %s" % ( threadName, time.ctime(time.time()), msg)

def btRead (threadName, delay):
    stop_flag = 0
    while stop_flag == 0:
        time.sleep (delay)
        msg = btsock.recv(1024)
        serq.append (msg)
        print "%s: %s--msg: %s" % ( threadName, time.ctime(time.time()),msg )




ipsock,pcaddr = setIPComm ()
sersock = setSerComm ()
btsock = setBTComm ()
ipq = deque([])
serq = deque ([])
btq = deque ([])


try:
    thread.start_new_thread (serRead, ("Thread-1-serRead",0.5))
    thread.start_new_thread (serWrite, ("Thread-2-serWrite",0.5))
    thread.start_new_thread (ipWrite, ("Thread-3-ipWrite",0.5))
    thread.start_new_thread (ipRead, ("Thread-4-ipRead",0.5))
    thread.start_new_thread (btWrite, ("Thread-5-btWrite",0.5))
    thread.start_new_thread (btRead, ("Thread-6-btRead",0.5))
    

except:
    print "Error: unable to start thread"

while 1:
    pass
