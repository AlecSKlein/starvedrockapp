//
//  FiltersViewController.swift
//  StarvedRockApp
//
//  Created by Kipros Moustoukkis on 10/20/14.
//  Copyright (c) 2014 Kipros Moustoukkis. All rights reserved.
//

import UIKit
import CoreData

@objc
class FiltersViewController: UITableViewController, UITableViewDataSource, UITableViewDelegate, NSFetchedResultsControllerDelegate {
    
    // Mark: Core Data
    
    let managedObjectContext = (UIApplication.sharedApplication().delegate as AppDelegate).managedObjectContext
    
    var fetchedResultsController: NSFetchedResultsController = NSFetchedResultsController()
    
    func getFetchedResultsController() -> NSFetchedResultsController {
        fetchedResultsController = NSFetchedResultsController(fetchRequest: filterFetchRequest(), managedObjectContext: managedObjectContext!, sectionNameKeyPath: nil, cacheName: nil)
        return fetchedResultsController
    }
    
    func filterFetchRequest() -> NSFetchRequest {
        let fetchRequest = NSFetchRequest(entityName: "POIs")
        
        let sortDescriptor = NSSortDescriptor(key: "poiName", ascending: true)
        
        fetchRequest.sortDescriptors = [sortDescriptor]
        
        return fetchRequest
    }
    
    func controllerDidChangeContent(controller: NSFetchedResultsController!) {
        tableView.reloadData()
    }
    
    override func viewDidLoad() {
        //super.viewDidLoad()
        
        fetchedResultsController = getFetchedResultsController()
        fetchedResultsController.delegate = self
        fetchedResultsController.performFetch(nil)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    // MARK: Table View Data Source
    
    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        if let count = fetchedResultsController.sections?.count {
            return count
        }
        return 0;
    }
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        let sectionInfo = fetchedResultsController.sections![section] as NSFetchedResultsSectionInfo
        return sectionInfo.numberOfObjects
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCellWithIdentifier("FilterCell", forIndexPath: indexPath) as FilterCell
        
        let filter = fetchedResultsController.objectAtIndexPath(indexPath) as POIs
        
        cell.filterName?.text = "\(filter.poiName)"
        cell.filterToggle?.on = filter.filterOnOff.boolValue
        
        return cell
    }
    
    // Mark: Table View Delegate
    
    override func tableView(tableView: UITableView, didDeselectRowAtIndexPath indexPath: NSIndexPath) {
    }
}

class FilterCell: UITableViewCell {
    @IBOutlet weak var filterName: UILabel!
    @IBOutlet weak var filterToggle: UISwitch!
    
    @IBAction func toggle(sender: AnyObject) {
        
    }
}