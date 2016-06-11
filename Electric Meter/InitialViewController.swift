//
//  InitialViewController.swift
//  Electric Meter
//
//  Created by Zaw Min Thant on 11/6/16.
//  Copyright © 2016 Z.M.T. All rights reserved.
//

import UIKit
import Alamofire

class InitialViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {

    @IBOutlet weak var locationButton: UIButton!
    @IBOutlet weak var locationsPickerView: UIPickerView!
    @IBOutlet weak var datePicker: UIDatePicker!
    @IBOutlet weak var fromDateButton: UIButton!
    @IBOutlet weak var toDateButton: UIButton!
    
    var zipCodes = [String]()
    var meterIDs = [[String: [String]]]()
    var meterIDsList = [String]()
    var isSelectingFromDate = false
    var isDatePickerShown = false
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        let hud = MBProgressHUD.showHUDAddedTo(self.view, animated: true)
        hud.label.text = "Loading data..."
        
        
        let url = NSURL(string: "http://eazydelivery.azurewebsites.net/pcm/svc/pcm/loadMeters/")!
        let mutableURLRequest = NSMutableURLRequest(URL: url)
        mutableURLRequest.HTTPMethod = "POST"
        
        let parameters = [:]
        
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
                        
                        if let responseData = JSON["responseData"] as? NSArray {
                            
                            for i in 0..<responseData.count {
                                if let dict = responseData[i] as? NSDictionary {
                                    self.zipCodes.append(dict["zipCode"] as! String)
                                    
                                    if let meters = dict["meters"] as? NSArray {
                                        var meterArray = [String]()
                                        for meter in meters {
                                            if let meterId = meter["meterId"] {
                                                meterArray.append("\(meterId!)")
                                            }
                                        }
                                        
                                        self.meterIDs.append([dict["zipCode"] as! String : meterArray])
                                    }
                                }
                            }
                        }
                        
                        dispatch_async(dispatch_get_main_queue(), {
                            self.pickerView(self.locationsPickerView, didSelectRow: 0, inComponent: 0)
                            self.locationsPickerView.reloadAllComponents()
                            MBProgressHUD.hideHUDForView(self.view, animated: true)
                        })
                    })
                }
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func pickADeviceLocation(sender: UIButton) {
       locationsPickerView.hidden = false
//        self.view.bringSubviewToFront(locationsPickerView)
    }
    
    // MARK: - PickerView
    func numberOfComponentsInPickerView(pickerView: UIPickerView) -> Int {
        return 2
    }
    
    func pickerView(pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if component == 0 {
            return zipCodes.count
        }
        
        return meterIDsList.count
    }
    
    func pickerView(pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if component == 0 {
            return zipCodes[row]
        }
        
        return "MeterId \(meterIDsList[row])"
    }
    
    func pickerView(pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if component == 0 {
            meterIDsList = meterIDs[row][zipCodes[row]]!
            locationsPickerView.reloadComponent(1)
        } else {
            print(meterIDsList[row])
            locationButton.setTitle("MeterId: \(meterIDsList[row])", forState: .Normal)
            pickerView.hidden = true
        }
    }
    
    @IBAction func datePicker(sender: UIDatePicker) {
        let dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
        let strDate = dateFormatter.stringFromDate(sender.date)
        print(strDate)
        
        if isSelectingFromDate {
            fromDateButton.setTitle(dateFormatter.stringFromDate(sender.date), forState: .Normal)
        } else {
            toDateButton.setTitle(dateFormatter.stringFromDate(sender.date), forState: .Normal)
        }
    }
    
    @IBAction func selectADate(sender: UIButton) {
        isDatePickerShown = !isDatePickerShown
        
        if sender.tag == 0 {
            isSelectingFromDate = true
        } else {
            isSelectingFromDate = false
        }
        
        datePicker.hidden = !isDatePickerShown
    }
    
    @IBAction func viewGraph(sender: UIButton) {
        if (locationButton.currentTitle! == "Select Meter") {
            print("Choose a meter!!!")
            return
        }
                
        performSegueWithIdentifier("showGraph", sender: self)
        
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == "showGraph" {
            let viewController = segue.destinationViewController as! ViewController
            
            if (fromDateButton.currentTitle! == "From"
                && toDateButton.currentTitle! == "To") {
                viewController.fromDate = ""
                viewController.toDate = ""
            } else {
                viewController.fromDate = fromDateButton.currentTitle!
                viewController.toDate = toDateButton.currentTitle!
            }
            
            viewController.meterId = meterIDsList[locationsPickerView.selectedRowInComponent(1)]
            viewController.zipCode = zipCodes[locationsPickerView.selectedRowInComponent(0)]
        }
    }
}
