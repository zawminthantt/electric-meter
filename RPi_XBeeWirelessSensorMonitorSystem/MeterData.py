class MeterData:

	def __init__(self,meter_id,zip_code,power,datetime,current,frequency,voltage,breaker_state,create_date):
		self.meter_id = meter_id
		self.zip_code = zip_code
		self.power = power
		self.datetime = datetime
		self.current = current
		self.frequency = frequency
		self.voltage = voltage
		self.breaker_state = breaker_state
		self.create_date = create_date

	def display(self):
			print	"MeterId:" , self.meter_id
			print	"ZipCode:" , self.zip_code
			print	"Power:" , self.power
			print	"DateTime:" , self.datetime
			print	"Current:" , self.current
			print	"Frequency:" , self.frequency
			print	"Voltage:" , self.voltage
			print	"Breaker_State" , self.breaker_state
			print	"CrateDate:" , self.create_date


#meterData1  = MeterData("mederid1","zipCode1","power1","datetime1","current1","freq1","voltage1","breakerSteate1","cretaedate1")
##print	"MeterId:" , meterData1.meter_id
#
#meterData1.display()
