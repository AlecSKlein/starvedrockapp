//
//  ContainerViewController.swift
//  StarvedRockApp
//
//  Created by Kipros Moustoukkis on 10/20/14.
//  Copyright (c) 2014 Kipros Moustoukkis. All rights reserved.
//

import UIKit
import QuartzCore

enum SlideOutState {
    case BothCollapsed
    case LeftPanelExpanded
    case RightPanelExpanded
}

class ContainerViewController: UIViewController, CenterMapViewControllerDelegate {
    
    var centerNavigationController: UINavigationController!
    var centerMapViewController: CenterMapViewController!
    var currentState: SlideOutState = .BothCollapsed {
        didSet {
            let shouldShowShadow = currentState != .BothCollapsed
            showShadowForCenterViewController(shouldShowShadow)
        }
    }
    var newsViewController: NewsViewController?
    var filtersViewController: FiltersViewController?
    
    let centerPanelExpandedOffset: CGFloat = 60
    
    override init() {
        super.init(nibName: nil, bundle: nil)
    }
    
    required init(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        centerMapViewController = UIStoryboard.centerMapViewController()
        centerMapViewController.delegate = self
        
        // wrap the centerMapViewController in a navigation controller, so we can push views to it
        // and display bar button items in the navigation bar
        centerNavigationController = UINavigationController(rootViewController: centerMapViewController)
        view.addSubview(centerNavigationController.view)
        addChildViewController(centerNavigationController)
        
        centerNavigationController.didMoveToParentViewController(self)
    }
    
    // MARK: CenterMapViewController delegate methods
    
    func toggleLeftPanel() {
        let notAlreadyExpanded = (currentState != .LeftPanelExpanded)
        
        if notAlreadyExpanded {
            addLeftPanelViewController()
        }
        
        animateLeftPanel(shouldExpand: notAlreadyExpanded)
    }
    
    func toggleRightPanel() {
        let notAlreadyExpanded = (currentState != .RightPanelExpanded)
        
        if notAlreadyExpanded {
            addRightPanelViewController()
        }
        
        animateRightPanel(shouldExpand: notAlreadyExpanded)
    }
    
    func addLeftPanelViewController() {
        if (newsViewController == nil) {
            newsViewController = UIStoryboard.newsViewController()
            
            addChildNewsPanelController(newsViewController!)
        }
    }
    
    func addRightPanelViewController() {
        if (filtersViewController == nil) {
            filtersViewController = UIStoryboard.filtersViewController()
            
            addChildFiltersPanelController(filtersViewController!)
        }
    }
    
    func addChildNewsPanelController(sidePanelController: NewsViewController) {
        view.insertSubview(sidePanelController.view, atIndex: 0)
        
        addChildViewController(sidePanelController)
        sidePanelController.didMoveToParentViewController(self)
    }
    
    func addChildFiltersPanelController(sidePanelController: FiltersViewController) {
        view.insertSubview(sidePanelController.view, atIndex: 0)
        
        addChildViewController(sidePanelController)
        sidePanelController.didMoveToParentViewController(self)
    }
    
    func animateLeftPanel(#shouldExpand: Bool) {
        if (shouldExpand) {
            currentState = .LeftPanelExpanded
            
            animateCenterPanelXPosition(targetPosition: CGRectGetWidth(centerNavigationController.view.frame) - centerPanelExpandedOffset)
        } else {
            animateCenterPanelXPosition(targetPosition: 0) { finished in
                self.currentState = .BothCollapsed
                
                self.newsViewController!.view.removeFromSuperview()
                self.newsViewController = nil
            }
        }
    }
    
    func animateRightPanel(#shouldExpand: Bool) {
        if (shouldExpand) {
            currentState = .RightPanelExpanded
            
            animateCenterPanelXPosition(targetPosition: -CGRectGetWidth(centerNavigationController.view.frame) + centerPanelExpandedOffset)
        } else {
            animateCenterPanelXPosition(targetPosition: 0) { finished in
                self.currentState = .BothCollapsed
                
                self.filtersViewController!.view.removeFromSuperview()
                self.filtersViewController = nil
            }
        }
    }
    
    func animateCenterPanelXPosition(#targetPosition: CGFloat, completion: ((Bool) -> Void)! = nil) {
        UIView.animateWithDuration(0.5, delay: 0, usingSpringWithDamping: 0.8, initialSpringVelocity: 0, options: .CurveEaseInOut, animations: {
            self.centerNavigationController.view.frame.origin.x = targetPosition
        }, completion: completion)
    }
    
    func showShadowForCenterViewController(shouldShowShadow: Bool) {
        if (shouldShowShadow) {
            centerNavigationController.view.layer.shadowOpacity = 0.8
        } else {
            centerNavigationController.view.layer.shadowOpacity = 0.0
        }
    }
    
    // MARK: Gesture recognizer
    
    func handlePanGesture(recognizer: UIPanGestureRecognizer) {
    }
}

private extension UIStoryboard {
    class func mainStoryboard() -> UIStoryboard { return UIStoryboard(name: "Main", bundle: NSBundle.mainBundle()) }
    
    class func newsViewController() -> NewsViewController? {
        return mainStoryboard().instantiateViewControllerWithIdentifier("LeftViewController") as? NewsViewController
    }
    
    class func filtersViewController() -> FiltersViewController? {
        return mainStoryboard().instantiateViewControllerWithIdentifier("RightViewController") as? FiltersViewController
    }
    
    class func centerMapViewController() -> CenterMapViewController? {
        return mainStoryboard().instantiateViewControllerWithIdentifier("CenterViewController") as? CenterMapViewController
    }
}