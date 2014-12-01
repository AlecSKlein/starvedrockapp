//
//  Notifications.swift
//  StarvedRockApp
//
//  Created by Kipros Moustoukkis on 11/30/14.
//  Copyright (c) 2014 Kipros Moustoukkis. All rights reserved.
//

import Foundation
import CoreData

class Notifications: NSManagedObject {

    @NSManaged var dateTime: String
    @NSManaged var expires: String
    @NSManaged var text: String
    @NSManaged var type: String

}
