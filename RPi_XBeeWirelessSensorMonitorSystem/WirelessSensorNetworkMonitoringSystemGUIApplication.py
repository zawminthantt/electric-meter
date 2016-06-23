#####################################
#   File Name : WirelessSensorNetworkMonitoringSystemGUIApplication.py   
#   Author      : NayLA  
#   Date         : 02/06/2016
#####################################

import Tkinter

class simpleapp_tk(Tkinter.Tk):
    def __init__(self,parent):
        Tkinter.Tk.__init__(self,parent)
        self.parent = parent
        self.initialize()

    def initialize(self):
        self.grid()

        self.entryVariable = Tkinter.StringVar()
        self.entry = Tkinter.Entry(self,textvariable=self.entryVariable)
        self.entry.grid(column=0,row=0,sticky='EW')
        self.entry.bind("<Return>", self.OnPressEnter)
        self.entryVariable.set(u"Enter text here.")

        button = Tkinter.Button(self,text=u"Click me !",
                                command=self.OnButtonClick)
        button.grid(column=1,row=0)

        #self.labelVariable = Tkinter.StringVar()
        #label = Tkinter.Label(self,textvariable=self.labelVariable,
        #                     anchor="w",fg="white",bg="green")
        #label.grid(column=0,row=1,columnspan=2,sticky='EW')
        #self.labelVariable.set(u"Hello !")

        self.labelVariableVoltage = Tkinter.StringVar()
        labelVoltage = Tkinter.Label(self,textvariable=self.labelVariableVoltage,
                              anchor="w",fg="white",bg="green")
        labelVoltage.grid(column=0,row=1,columnspan=2,sticky='EW')
        self.labelVariableVoltage.set(u"Voltage : ")

        self.grid_columnconfigure(0,weight=1)
        self.resizable(True,True)
        self.update()
        self.geometry(self.geometry())       
        self.entry.focus_set()
        self.entry.selection_range(0, Tkinter.END)
           
        ###########################
        self.labelVariableCurrent = Tkinter.StringVar()
        labelCurrent = Tkinter.Label(self,textvariable=self.labelVariableCurrent,
                              anchor="w",fg="white",bg="green")
        labelCurrent.grid(column=0,row=2,columnspan=2,sticky='EW')
        self.labelVariableCurrent.set(u"Current : ")

        self.grid_columnconfigure(0,weight=1)
        self.resizable(True,True)
        self.update()
        self.geometry(self.geometry())       
        self.entry.focus_set()
        self.entry.selection_range(0, Tkinter.END)

        ###########################
        
        self.labelVariablePower = Tkinter.StringVar()
        labelPower = Tkinter.Label(self,textvariable=self.labelVariablePower,
                              anchor="w",fg="white",bg="green")
        labelPower.grid(column=0,row=3,columnspan=2,sticky='EW')
        self.labelVariablePower.set(u"Power : ")

        self.grid_columnconfigure(0,weight=1)
        self.resizable(True,True)
        self.update()
        self.geometry(self.geometry())       
        self.entry.focus_set()
        self.entry.selection_range(0, Tkinter.END)
              
        ###########################
        self.labelVariableFrequency = Tkinter.StringVar()
        labelFrequency = Tkinter.Label(self,textvariable=self.labelVariableFrequency,
                              anchor="w",fg="white",bg="green")
        labelFrequency.grid(column=0,row=3,columnspan=2,sticky='EW')
        self.labelVariableFrequency.set(u"Frequency : ")

        self.grid_columnconfigure(0,weight=1)
        self.resizable(True,True)
        self.update()
        self.geometry(self.geometry())       
        self.entry.focus_set()
        self.entry.selection_range(0, Tkinter.END)
              
        ###########################
        self.labelVariableBreakerState = Tkinter.StringVar()
        labelBreakerState = Tkinter.Label(self,textvariable=self.labelVariableBreakerState,
                              anchor="w",fg="white",bg="green")
        labelBreakerState.grid(column=0,row=4,columnspan=2,sticky='EW')
        self.labelVariableBreakerState.set(u"BreakerState :  OK")

        self.grid_columnconfigure(0,weight=1)
        self.resizable(True,True)
        self.update()
        self.geometry(self.geometry())       
        self.entry.focus_set()
        self.entry.selection_range(0, Tkinter.END)
              
        ###########################
        

    def OnButtonClick(self):
        self.labelVariable.set( self.entryVariable.get()+" (You clicked the button)" )
        self.entry.focus_set()
        self.entry.selection_range(0, Tkinter.END)

    def OnPressEnter(self,event):
        self.labelVariable.set( self.entryVariable.get()+" (You pressed ENTER)" )
        self.entry.focus_set()
        self.entry.selection_range(0, Tkinter.END)

    def OnDataReceive(self):
        self.labelVariable.set( self.entryVariable.get()+"Voltage : " )
        self.entry.focus_set()
        self.entry.selection_range(0, Tkinter.END)

if __name__ == "__main__":
    app = simpleapp_tk(None)
    app.title('Power Monitoring System')
    app.mainloop()
