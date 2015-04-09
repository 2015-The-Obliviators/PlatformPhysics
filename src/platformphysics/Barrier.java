/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformphysics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author kevin.lawrence
 */
public class Barrier extends Rectangle {
    private final BarrierType type;
    
    public Barrier(Point position, int width, int height, BarrierType type){
        super(position.x, position.y, width, height);
        this.type = type;
    }
    
    public void paint(Graphics graphics){
        graphics.setColor(Color.BLUE);
        graphics.fillRect(x, y, width, height);
    }
    
}
