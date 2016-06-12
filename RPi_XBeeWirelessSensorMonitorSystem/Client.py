import requests
import json

class Client:
	def __init__(self):
		print("Create client thread")

	def  sendData(self, zipCode, meterId, power, datetime, current, frequency, voltage, breakerState):
		url = 'http://eazydelivery.azurewebsites.net/pcm/svc/pcm/sendData'
		success = False
		payload = {
			"zipCode":zipCode,
			"meterId":meterId,
			"power":power,
			"datetime":datetime,
			"current":current,
			"frequency":frequency,
			"voltage":voltage,
			"breakerState":breakerState
			}
		try : 
			r = requests.post(url, json=payload)

			print('requesting....\r\n')
			print(r.status_code)
			if r.status_code == 200:
				data = r.json()
				success = data['success']
			print('----done----')
		except requests.exceptions.RequestException as e:
			print e

		print(success)
		if success:
  			## Save in database ##
			print("request is successful!!!\r\n")
		else :
			print("Unsuccessful!!!\r\n")
