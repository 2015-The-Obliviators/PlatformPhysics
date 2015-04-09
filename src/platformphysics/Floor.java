/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformphysics;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author kevin.lawrence
 */
public class Floor {
    
    public Floor(Point parentOffset, Rectangle rectangle){
        this.parentOffset = parentOffset;
        this.rectangle = rectangle;
    }
    
    private Point parentOffset;
    private Rectangle rectangle;
    
}
