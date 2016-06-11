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
    
    var meterId: String!
    var zipCode: String!
    var fromDate: String!
    var toDate: String!
    
    let baseURL = "http://eazydelivery.azurewebsites.net/pcm/svc/pcm/"

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let hud = MBProgressHUD.showHUDAddedTo(self.view, animated: true)
        hud.label.text = "Loading data..."
        
        barChartView.descriptionText = ""
        barChartView.noDataTextDescription = "No data"
        barChartView.maxVisibleValueCount = 60
        
        let xAxis = barChartView.xAxis
        xAxis.drawGridLinesEnabled = false
        xAxis.spaceBetweenLabels = 2
        
        let leftAxis = barChartView.leftAxis;
        leftAxis.labelFont = UIFont.systemFontOfSize(10.0)
        leftAxis.labelCount = 8;
        leftAxis.labelPosition = .OutsideChart
        leftAxis.spaceTop = 0.15;
        leftAxis.axisMinValue = 0.0; // this replaces startAtZero = YES
        
        let rightAxis = barChartView.rightAxis;
        rightAxis.enabled = true;
        rightAxis.drawGridLinesEnabled = false;
        rightAxis.labelFont = UIFont.systemFontOfSize(10.0)
        rightAxis.labelCount = 8;
        rightAxis.valueFormatter = leftAxis.valueFormatter;
        rightAxis.spaceTop = 0.15;
        rightAxis.axisMinValue = 0.0; // this replaces startAtZero = YES
        
//        barChartView.legend.position = .BelowChartLeft;
//        _chartView.legend.form = ChartLegendFormSquare;
//        _chartView.legend.formSize = 9.0;
//        _chartView.legend.font = [UIFont fontWithName:@"HelveticaNeue-Light" size:11.f];
//        _chartView.legend.xEntrySpace = 4.0;
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    override func viewWillAppear(animated: Bool) {
        
        var parameters = [String : String]()
        parameters["meterId"] = meterId
        parameters["zipCode"] = zipCode
        
        let url: NSURL!
        
        if (fromDate.characters.count <= 0
            || toDate.characters.count <= 0) {
            url = NSURL(string: baseURL + "/latestStatus/")
        } else {
            url = NSURL(string: baseURL + "/searchHistory/")
            parameters["from"] = fromDate
            parameters["to"] = toDate
        }
        
        print("parameters => \(parameters)")
        
        let mutableURLRequest = NSMutableURLRequest(URL: url)
        mutableURLRequest.HTTPMethod = "POST"
        
        do {
            mutableURLRequest.HTTPBody = try NSJSONSerialization.dataWithJSONObject(parameters, options: NSJSONWritingOptions())
        } catch {
            // No-op
        }
        
        mutableURLRequest.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        Alamofire.request(mutableURLRequest)
            .validate()
            .responseJSON { response in
                print(response.request)
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
                            currentDataSet.setColor(UIColor.redColor())
                            
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

