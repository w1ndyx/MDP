import socket
from interface import *


class pc_interface (interface):
    def __init__(self):
        self.udp_ip = "192.168.10.10"
        self.port = 5143
        
    def connect(self):
        self.ipsock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        #make port reusable
        self.ipsock.setsockopt (socket.SOL_SOCKET, socket.SO_REUSEADDR,1)
        self.ipsock.setsockopt (socket.SOL_SOCKET, socket.SO_BROADCAST,1)
        self.ipsock.bind((self.udp_ip, self.port))
        #receive the first message from client, know the client address
        data, self.pcaddr = self.ipsock.recvfrom (1024)

    def disconnect(self):
        self.ipsock.close()

    def write(self,msg):
        self.ipsock.sendto (msg, self.pcaddr)
        print "Write to PC: %s" %(msg)

    def read(self):
        msg, addr = ipsock.recvfrom (1024)
        print "Read from PC: %s" %(msg)
        return msg
        
