/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformphysics;

import environment.PhysicsObject;
import environment.Velocity;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author kevin.lawrence
 */
public class Letter extends PhysicsObject {
    private Rectangle currentFloor;
    
    private ArrayList<Rectangle> floors;
    
    
    public Letter(Point position, Velocity velocity) {
        super(position, velocity);
    }
    
}
