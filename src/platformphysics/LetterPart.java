/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformphysics;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author kevin.lawrence
 */
public class LetterPart {
    
    public void paint(Graphics graphics){
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    public LetterPart(Point parentOffset, Rectangle rectangle){
        this.parentOffset = parentOffset;
        this.rectangle = rectangle;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private Point parentOffset;
    private Rectangle rectangle;
    
    /**
     * @return the parentOffset
     */
    public Point getParentOffset() {
        return parentOffset;
    }
    
    /**
     * @param parentOffset the parentOffset to set
     */
    public void setParentOffset(Point parentOffset) {
        this.parentOffset = parentOffset;
    }
    
    /**
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        return rectangle;
    }
    
    /**
     * @param rectangle the rectangle to set
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
//</editor-fold>
}
