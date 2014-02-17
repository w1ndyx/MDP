import bluetooth
from interface import *

class android_interface(interface):
    def __init__(self):
        self.bt_addr = ""//mac number
        self.port = 3
        
    def connect(self):
        self.btsock = bluetooth.BluetoothSocket( bluetooth.RFCOMM )
        btsock.connect((self.bt_addr, self.port))
        
    def disconnect(self):
        self.btsock.close()

    def write(self,msg):
        self.btsock.send(msg)
        print "Write to Android: %s" %(msg)

    def read(self):
        msg = btsock.recv(1024)
        print "Read from Android: %s" %(msg)
        return self.decode(msg)

    def decode (self,msg):
        if msg == 'w':
            return "!A02"
        elif msg == 'a':
            return "!C01"
        elif msg == 's':
            return "!B01"
        elif msg == 'd':
            return "!D01"

