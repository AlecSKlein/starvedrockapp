//
//  POIs.swift
//  StarvedRockApp
//
//  Created by Kipros Moustoukkis on 11/30/14.
//  Copyright (c) 2014 Kipros Moustoukkis. All rights reserved.
//

import Foundation
import CoreData

class POIs: NSManagedObject {

    @NSManaged var filterOnOff: NSNumber
    @NSManaged var latitude: NSNumber
    @NSManaged var longitude: NSNumber
    @NSManaged var poiName: String
    @NSManaged var poiNotes: String
    @NSManaged var poiPicture: NSData
    @NSManaged var poiType: String

}
