//
//  ViewController.swift
//  Electric Meter
//
//  Created by Zaw Min Thant on 9/6/16.
//  Copyright © 2016 Z.M.T. All rights reserved.
//

import UIKit
import Alamofire

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    override func viewWillAppear(animated: Bool) {
        
        let hud = MBProgressHUD.showHUDAddedTo(self.view, animated: true)
        hud.label.text = "Loading ..."
        
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
                if let JSON = response.result.value {
                    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), {
                        print("JSON: \(JSON)")
                        
                        dispatch_async(dispatch_get_main_queue(), {
                            MBProgressHUD.hideHUDForView(self.view, animated: true)
                        })
                    })
                }
        }
    }
}

