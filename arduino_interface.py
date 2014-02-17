import serial
from interface import *

class arduino_interface(interface):
    def __init__(self):
        self.port = '/dev/ttyACM0'
        self.baud = 115200
        
    def connect(self):
        self.sersock = serial.Serial (port, baud)
        #init socket connection
        self.sersock.write("")
        self.sersock.write("")
        
    def disconnect(self):
        self.sersock.close()

    def write(self,msg):
        self.sersock.write(msg)
        print "Write to Arduino: %s" %(msg)

    def read(self):
        msg = self.sersock.readline()
        print "Read from Arduino: %s" %(msg)
        return msg
        
