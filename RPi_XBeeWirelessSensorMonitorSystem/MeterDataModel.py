from MeterData import MeterData
import sqlite3
import sys
from datetime import datetime

class MeterDataModel: 
	CONST_dbName = "meta_data_db"
	CONST_MetaTable = "meta_data"


	def __init__(self):
		connect = None
		try:
		    connection = sqlite3.connect('meter_data_db.db')
		    cursor = connection.cursor()    
		    cursor.execute('SELECT SQLITE_VERSION()')
		    data = cursor.fetchone()
		    print "SQLite version: %s" % data                
		except sqlite.Error, e:
		    print "Error %s:" % e.args[0]
		    sys.exit(1)
		finally:
		    if connection:
		       connection.close()


	def createMeterDataTable(self):
		connection = None
		#print "Will create table"

		createTableStr = " CREATE TABLE IF  NOT EXISTS meter_data ( id INTEGER PRIMARY KEY AUTOINCREMENT, meter_id INTEGER,zip_code TEXT(255), power TEXT(255),datetime TEXT(255), current TEXT(255),frequency TEXT(255), voltage TEXT(255), breaker_state TEXT(255), create_date DATETIME)"
		try:
		    connection = sqlite3.connect('meter_data_db.db')
		    with connection:
		        cursor = connection.cursor()    
		        cursor.execute(createTableStr)
		except sqlite3.Error, e:
		    print "Error %s:" % e.args[0]
		    sys.exit(1)
		finally:
		    if connection:
		        connection.close()

	def insertMeterRecord(self,meter_id,zip_code,power,datetime,current,frequency,voltage,breaker_state,create_date):
		connection = None
		#row =  {'id': meter_id': 4098, 'zip_code': 4139 , 'power': "power1" , 'datetime' : "today" ,'frequency':"frequence1" ,'voltage':243,'breaker_state' : "breaker state" ,'currentDate':"now"}
		row =  {'meter_id':meter_id , 'zip_code': zip_code ,'power': power,'datetime': datetime,'current':current,'frequency': frequency,'voltage': voltage,'breaker_state': breaker_state ,'create_date': create_date}
		#print row

		try:
		    connection = sqlite3.connect('meter_data_db.db')
		    with connection:
		        cursor = connection.cursor()  
			#cursor.execute("INSERT INTO meter_data (meter_id,zip_code,power,datetime,frequency,voltage,breaker_state,create_date) VALUES (meter_id:,:zip_code,:power,:datetime,:frequency,:voltage,:breaker_state,:create_date)",row)
		        cursor.execute("INSERT INTO meter_data (meter_id,zip_code,power,datetime,current,frequency,voltage,breaker_state,create_date) VALUES (:meter_id,:zip_code,:power,:datetime,:current,:frequency,:voltage,:breaker_state,:create_date)", row)
		except sqlite3.Error, e:
		    print "Error %s:" % e.args[0]
		    sys.exit(1)
		finally:
		    if connection:
		        connection.close()


	def selectMeterRecords(self):
		connection = None
		try:
		    connection = sqlite3.connect('meter_data_db.db')
		    with connection:
		        cursor = connection.cursor()
		        cursor.execute("SELECT * FROM meter_data")
		        for row in cursor.fetchall():
		           # print "%d\t%s\t%d" % (row[0], row[1], row[2])
			   print row
		except sqlite3.Error, e:
		    print "Error %s:" % e.args[0]
		    sys.exit(1)
		finally:
		    if connection:
		        connection.close()






