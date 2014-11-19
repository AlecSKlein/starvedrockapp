//
//  FiltersViewController.swift
//  StarvedRockApp
//
//  Created by Kipros Moustoukkis on 10/20/14.
//  Copyright (c) 2014 Kipros Moustoukkis. All rights reserved.
//

import UIKit

@objc
protocol FiltersViewControllerDelegate {
    func filterSelected();
}

class FiltersViewController: UIViewController,UITableViewDataSource, UITableViewDelegate {
    // MARK: Table View Data Source
    
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("FilterCell", forIndexPath: indexPath) as FilterCell
        return cell
    }
}

class FilterCell: UITableViewCell {
    @IBOutlet weak var filterName: UILabel!
    @IBOutlet weak var filterToggle: UISwitch!
}