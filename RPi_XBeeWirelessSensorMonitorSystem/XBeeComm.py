#####################################
#   File Name : XBeeComm.py   
#   Author      : NayLA  
#   Date         : 30/05/2016
#####################################

import serial
from TimeStampLib import *
import sqlite3
import sys
from datetime import datetime
from MeterDataModel import MeterDataModel

class XBeeComm:

        pktSize = 19 # Important #
        sensorID = 0
        voltage = 0
        current = 0
        power = 0
        frequency = 0
        breakerStatus = 0
        
    
        def __init__(self, baudrate = 115200, timeout =4.0):
            self.port = serial.Serial("/dev/ttyS0", baudrate=115200 , timeout =4.0)
            #self.port.baudrate = 115200
            #self.port.timeout = 5.0
 
        def readPacket(self):
            rcv = self.port.read(self.pktSize).encode('hex')
            return rcv

        def checkPacket(self,rcvpkt):
            if (len(rcvpkt) == (self.pktSize*2)):#Added
                    headsync = int(rcvpkt[0:4],16)
                    endtail = int(rcvpkt[34:38],16)

                    print "HeadSync: " + hex(headsync)
                    print "Endtail: " + hex(endtail)

                    if ((headsync == 0xfffa ) and (endtail == 0x0d0a)):
                            return True
                    else:
                            return False
        

        def parseParameters(self):
            timestamp = TimeStamp()
            rcv = self.readPacket()
            print rcv
         
            if self.checkPacket(rcv) == True:
                    self.sensorID = int(rcv[4:8],16)
                    self.voltage  = int(rcv[12:16] ,16)
                    self.current =  int(rcv[16:20] ,16)
                    self.power =  int(rcv[20:24] ,16)
                    self.frequency =  int(rcv[24:28] ,16)
                    self.breakerStatus = int(rcv[28:32],16)

                    dt = timestamp.getTimeStamp()
                   
                    print ("TimeStamp : %s" %dt,"SensorID: %d " %self.sensorID,"Voltage: %d V" %self.voltage ,
                           "Current:  %d A" %self.current , " Power: %d W " %self.power,"Frequency: %d Hz" %self.frequency ,
                           "Breaker Status:  %d" %self. breakerStatus)

                    ########################
                    #  Database write here!
                    ########################
                    dataModel = MeterDataModel()
                    dataModel.createMeterDataTable()
                    dataModel.insertMeterRecord(self.sensorID,11975,self.power,  timestamp.getTimeStamp(),
                        self.current,self.frequency,self.voltage,self.breakerStatus,datetime.now())
                    ########################
                    
            else:
                    rcv = '0'
                    print "Unsynchroznied data packet received!\r\n\n"
                    

        def getSensorID(self):
            return self.sensorID
                            
        def getVoltage(self):
            return  self.voltage

        def getCurrent(self):
            return  self.current

        def getPower(self):
            return  self.power

        def getFrequency(self):
            return  self.frequency

        def getBreakerStatus(self):
            return  self.breakerStatus

        def commClose(self):
                self.port.close()
                print "Comm Port closed!"
                

        
