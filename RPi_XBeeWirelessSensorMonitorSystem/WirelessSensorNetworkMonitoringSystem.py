#####################################
#   File Name : WirelessSensorNetworkMonitoringSystem.py   
#   Author      : NayLA  
#   Date         : 31/05/2016
#####################################

import RPi.GPIO as GPIO

import sys
import time
import thread
import datetime
import socket
from CharLCDLib import *
from XBeeComm import *
from TimeStampLib import *
from Server import *
from Client import *


ON = 1
OFF = 0
butPin = 12

pinMotionDetector = 17
pinInList = [4,9,10,11]
relayPinList = [22,23,24,25]
#pinOutList = [5,6,13,19,26,16,20,21]

DoorOneStatus = 0
DoorTwoStatus = 0
DoorThreeStatus = 0
DoorFourStatus = 0
MotionSensorStatus = 0

tempDegreeC = 0

class IOPort:

                def __init__(self):
                        GPIO.setmode(GPIO.BCM)
                        GPIO.setwarnings(False)
                        self.pinOutList = [5,6,13,19,26,16,20,21]

                def  readpin(self, pin_no):
                                pinIn = pin_no # Broadcom pin 32 (GPIO 12)
                                GPIO.setmode(GPIO.BCM) # Broadcom pin-numbering scheme
                                GPIO.setup(pinIn, GPIO.IN, pull_up_down=GPIO.PUD_UP) # Button pin set as input w/ pull-up
                                #self.pinInList = [4,9,10,11]
                                return  GPIO.input(pinIn)#Read GPIO pin 12

                def switch(self,pin,state):
                        ledpin = self.pinOutList[pin]
                        GPIO.setup(ledpin , GPIO.OUT)
                
                        if(state == 1):
                                        GPIO.output(ledpin,GPIO.HIGH)
                        else :
                                        GPIO.output(ledpin,GPIO.LOW)
		


def xbeeThread(sensordata):
    while 1:
        sensordata.parseParameters()
        
        timestamp =  TimeStamp()
        
        year = timestamp.getYear()
        month = timestamp.getMonth()
        date = timestamp.getDate()
        hr = timestamp.getHour()
        mm = timestamp.getMinute()
        sec = timestamp.getSecond()

        sensorID = sensordata.getSensorID()
        voltage = sensordata.getVoltage()
        current = sensordata.getCurrent()
        power = sensordata.getPower()
        frequency = sensordata.getFrequency()
        breakerState = sensordata.getBreakerStatus()

        dicSensorData = {"SensorID": sensorID,"voltage": voltage, "current": current ,"power": power,
                                      "frequency": frequency , "breakerState": breakerState}


def lcdThread(sensordata,delay):
    while 1:
        lcd = Lcd()
        lcd.clear()                                                                                    
        lcd.display_string("Voltage: %d V" % sensordata.getVoltage(), 1)
        lcd.display_string("Current: %d A" % sensordata.getCurrent(), 2)
        lcd.display_string("Power: %d W" % sensordata.getPower(), 3)
        lcd.display_string("Frequency: %d Hz" % sensordata.getFrequency(), 4)
        sleep(delay)


def networkingThread(server, dicSensorData, timestamp ,delay):
    while 1:
        server.waitForConnection()
        server.serviceToClient(dicSensorData ,timestamp)
        sleep(delay)

def networkingClientForCloudThread(client, dicSensorData, timestamp ,delay):
    while 1:
        datetime = timestamp.getTimeStamp()
        sensorID = dicSensorData.getSensorID()
        voltage = dicSensorData.getVoltage()
        current = dicSensorData.getCurrent()
        power = dicSensorData.getPower()
        frequency = dicSensorData.getFrequency()
        breakerState = dicSensorData.getBreakerStatus()
        
        client.sendData("119615",str(sensorID),str(power),datetime,str(current),str(frequency),str(voltage),str(breakerState))
        sleep(delay)
        
        
def main():

                EventSaved = 0
                lcd = Lcd()
                lcd.clear()
                lcd.display_string(" System startup...", 1)
                lcd.display_string(" Initializing... ", 3)             
                print("Here we go! Press CTRL+C to exit")
                
                led = IOPort()
                webserver = Webserver()
                cloudClient = Client()
                timestamp =  TimeStamp()
                xbeedata = XBeeComm()
                
                print("Performing system check....")
                for x in xrange(0,8):
                            led.switch(x,ON)
                            sleep(0.3)
                            led.switch(x,OFF)

                print("System running....")
                #####################
                               
                try:

                    try:
                    
                        thread.start_new_thread(xbeeThread, (xbeedata, ))
                        thread.start_new_thread(lcdThread,(xbeedata,2, ))
                        thread.start_new_thread(networkingThread,( webserver, xbeedata ,timestamp , 3 , ))
                        thread.start_new_thread(networkingClientForCloudThread,(cloudClient, xbeedata, timestamp ,5))
                        
                    except:
                        print "Error: unable to start thread"

                except KeyboardInterrupt: # If CTRL+C is pressed, exit cleanly:
                    xbeedata.commClose()
                    webserver.closeConnection()
                    GPIO.cleanup() # cleanup all GPIO

                        
                while 1:
                    pass # Busy-wait for keyboard interrupt ( Ctrl + C)
                    

if __name__ == "__main__":
	main()
                
