import serial
import time
sermsg= "abcdetest"
sersock = serial.Serial('/dev/ttyACM0', 9600) # Establish serial connection
flag = 0
delay = .01
while flag != 1:
    print "writing.."
    sersock.write(sermsg)
    print "reading.."
    time.sleep (delay)
    msg = sersock.readline()
    print msg
    
sersock.close()
