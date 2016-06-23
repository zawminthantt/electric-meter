import sqlite3
import sys
from datetime import datetime
from MeterDataModel import MeterDataModel

dataModel = MeterDataModel()
print datetime.now()
dataModel.createMeterDataTable()
#dataModel.insertMeterRecord("meterid1","zipCode1","power1","datetime1","current","frequency1","voltage1","bareaker1",str(datetime.now()))
dataModel.selectMeterRecords()
