//
//  ViewController.swift
//  Electric Meter
//
//  Created by Zaw Min Thant on 9/6/16.
//  Copyright © 2016 Z.M.T. All rights reserved.
//

import UIKit
import Alamofire
import Charts

class ViewController: UIViewController {

    @IBOutlet weak var barChartView: BarChartView!

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let hud = MBProgressHUD.showHUDAddedTo(self.view, animated: true)
        hud.label.text = "Loading data..."
        
        barChartView.descriptionText = ""
        barChartView.noDataTextDescription = "No data"
        barChartView.setVisibleXRangeMaximum(10)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    override func viewWillAppear(animated: Bool) {
        let url = NSURL(string: "http://eazydelivery.azurewebsites.net/pcm/svc/pcm/searchHistory/")!
        let mutableURLRequest = NSMutableURLRequest(URL: url)
        mutableURLRequest.HTTPMethod = "POST"
        
        let parameters = ["meterId" : "1",
                          "locationId" : "1",
                          "from" : "2016-06-05 23:59:05",
                          "to" : "2016-06-08 23:59:05"]
        
        do {
            mutableURLRequest.HTTPBody = try NSJSONSerialization.dataWithJSONObject(parameters, options: NSJSONWritingOptions())
        } catch {
            // No-op
        }
        
        mutableURLRequest.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        Alamofire.request(mutableURLRequest)
            .validate()
            .responseJSON { response in
                if let JSON = response.result.value as? [String : AnyObject] {
                    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), {
                        print("JSON: \(JSON)")
                        
                        if let responseData = JSON["responseData"] as? NSArray {
                            
                            var frequencies = [BarChartDataEntry]()
                            var currents = [BarChartDataEntry]()
//                            var powers = [BarChartDataEntry]()
                            var voltages = [BarChartDataEntry]()
                            var xArray = [String]()
                            
                            for i in 0..<responseData.count {
                                let dict = responseData[i]
                                
                                if let frequency = dict["frequency"] {
                                    frequencies.append(BarChartDataEntry(value: (frequency?.doubleValue)!, xIndex: i))
                                }
                                
                                if let current = dict["current"] {
                                    currents.append(BarChartDataEntry(value: (current?.doubleValue)!, xIndex: i))
                                }
                                
//                                if let power = dict["power"] {
//                                    powers.append(BarChartDataEntry(value: (power?.doubleValue)!, xIndex: i))
//                                }
                                
                                if let voltage = dict["voltage"] {
                                    voltages.append(BarChartDataEntry(value: (voltage?.doubleValue)!, xIndex: i))
                                }
                                
                                if let dateTime = dict["datetime"] {
                                    xArray.append(dateTime as! String)
                                }
                            }
                            
                            let frequencyDataSet = BarChartDataSet(yVals: frequencies, label: "Frequency")
                            frequencyDataSet.setColor(UIColor.blueColor())
                            let voltageDataSet = BarChartDataSet(yVals: voltages, label: "Voltage")
                            voltageDataSet.setColor(UIColor.greenColor())
//                            let powerDataSet = BarChartDataSet(yVals: powers, label: "Power")
//                            powerDataSet.setColor(UIColor.redColor())
                            let currentDataSet = BarChartDataSet(yVals: currents, label: "Current")
                            currentDataSet.setColor(UIColor.yellowColor())
                            
                            let dataSets = [frequencyDataSet, voltageDataSet, currentDataSet]
                            
                            let chartData = BarChartData(xVals: xArray, dataSets: dataSets)
                            self.setChartDataCount(chartData)
                        }
                        
                        dispatch_async(dispatch_get_main_queue(), {
                            MBProgressHUD.hideHUDForView(self.view, animated: true)
                            
                        })
                    })
                }
        }
    }
    
    func setChartDataCount(chartData: BarChartData) {
        dispatch_async(dispatch_get_main_queue()) { 
            self.barChartView.data = chartData
        }
    }
}

