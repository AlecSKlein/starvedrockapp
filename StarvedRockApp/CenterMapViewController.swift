//
//  CenterMapViewController.swift
//  StarvedRockApp
//
//  Created by Kipros Moustoukkis on 10/20/14.
//  Copyright (c) 2014 Kipros Moustoukkis. All rights reserved.
//

import UIKit
import MapKit

@objc
protocol CenterMapViewControllerDelegate {
    optional func toggleLeftPanel()
    optional func toggleRightPanel()
    optional func collapseSidePanels()
}

class CenterMapViewController: UIViewController {
    @IBOutlet weak var mapView: MKMapView!
    
    var delegate: CenterMapViewControllerDelegate?
    let METERS_PER_MILE = 1609.344
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //1
        let location = CLLocationCoordinate2D(latitude: 41.3214, longitude: -88.9903)
        
        //2
        let span = MKCoordinateSpanMake(0.05, 0.05)
        let region = MKCoordinateRegionMake(location, span)
        mapView.setRegion(region, animated: true)
        
        //3
        let annotation = MKPointAnnotation()
        annotation.setCoordinate(location)
        annotation.title = "Starved Rock"
        annotation.subtitle = "State Park"
        mapView.addAnnotation(annotation)
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: Button Actions
    
    @IBAction func newsTapped(sender: AnyObject) {
        if let d = delegate {
            d.toggleLeftPanel?()
        }
    }
    
    @IBAction func filtersTapped(sender: AnyObject) {
        if let d = delegate {
            d.toggleRightPanel?()
        }
    }
}
