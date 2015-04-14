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
import java.util.HashMap;

/**
 *
 * @author kevin.lawrence
 */
public final class LetterPart implements ParentPositionProviderIntf {
    
    public void paint(Graphics graphics){
        graphics.setColor(fillColor);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        
        graphics.setColor(borderColor);
        graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        
        if (isShowBarriers()){
            graphics.setColor(getBarrierColor());
            for (ChildBarrier barrier : getChildBarriers()){
                graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);           
            }
        }
    }
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        setBarriers(new HashMap<>());
//        showBarriers = true;
    }
    
    public LetterPart(Point parentOffset, Rectangle rectangle){
        this.parentOffset = parentOffset;
        this.rectangle = rectangle;
        
        setTopBarrier(true);
        setBottomBarrier(true);
        setLeftBarrier(true);
        setRightBarrier(true);        
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="ParentPositionProviderIntf">
    @Override
    public Point getParentPosition() {
        return this.rectangle.getLocation();
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private Point parentOffset;
    private Rectangle rectangle;
    
    private Color fillColor = Color.BLACK;
    private Color borderColor = Color.BLACK;
    private Color barrierColor = Color.ORANGE;
    private boolean showBarriers;
    
    private boolean topBarrier, bottomBarrier, leftBarrier, rightBarrier;
    private HashMap<String, ChildBarrier> barriers;
    
    public static String TOP_FLOOR = "TOP_FLOOR";
    public static String BOTTOM_CEILING = "BOTTOM_CEILING";
    public static String LEFT_WALL = "LEFT_WALL";
    public static String RIGHT_WALL = "RIGHT_WALL";
    
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

    /**
     * @return the fillColor
     */
    public Color getColor() {
        return fillColor;
    }

    /**
     * @param color the fillColor to set
     */
    public void setColor(Color color) {
        this.fillColor = color;
    }

    /**
     * @return the topBarrier
     */
    public boolean hasTopBarrier() {
        return topBarrier;
    }

    /**
     * @param topBarrier the topBarrier to set
     */
    public void setTopBarrier(boolean topBarrier) {
        if (!this.topBarrier && topBarrier){
            addBarrier(TOP_FLOOR, new ChildBarrier(new Point(this.rectangle.x, this.rectangle.y), this.rectangle.width, this.rectangle.height / 4, BarrierType.FLOOR, this, new Point(0, 0)));
        } else if (!topBarrier) {
            removeBarrier(TOP_FLOOR);
        }

        this.topBarrier = topBarrier;
    }

    /**
     * @return the bottomBarrier
     */
    public boolean hasBottomBarrier() {
        return bottomBarrier;
    }

    /**
     * @param bottomBarrier the bottomBarrier to set
     */
    public void setBottomBarrier(boolean bottomBarrier) {
         if (!this.bottomBarrier && bottomBarrier){
            addBarrier(BOTTOM_CEILING, new ChildBarrier(new Point(rectangle.x, rectangle.y  + (rectangle.height * 3 / 4)), rectangle.width, rectangle.height / 4, BarrierType.CEILING, this, new Point(0, rectangle.height * 3 / 4)));
        } else if (!bottomBarrier) {
            removeBarrier(BOTTOM_CEILING);
        }
       this.bottomBarrier = bottomBarrier;
    }

    /**
     * @return the leftBarrier
     */
    public boolean hasLeftBarrier() {
        return leftBarrier;
    }

    /**
     * @param leftBarrier the leftBarrier to set
     */
    public void setLeftBarrier(boolean leftBarrier) {
         if (!this.leftBarrier && leftBarrier){
            addBarrier(LEFT_WALL, new ChildBarrier(new Point(rectangle.x, rectangle.y), rectangle.width / 4, rectangle.height, BarrierType.WALL, this, new Point(0, 0)));
        } else if (!leftBarrier) {
            removeBarrier(LEFT_WALL);
        }
        
        this.leftBarrier = leftBarrier;
    }

    /**
     * @return the rightBarrier
     */
    public boolean hasRightBarrier() {
        return rightBarrier;
    }

    /**
     * @param rightBarrier the rightBarrier to set
     */
    public void setRightBarrier(boolean rightBarrier) {
         if (!this.rightBarrier && rightBarrier){
            addBarrier(RIGHT_WALL, new ChildBarrier(new Point(rectangle.x + (rectangle.width  * 3 / 4), rectangle.y), rectangle.width / 4, rectangle.height, BarrierType.WALL, this, new Point(rectangle.x + (rectangle.width * 3 / 4), 0)));
        } else if (!rightBarrier) {
            removeBarrier(RIGHT_WALL);
        }
        
        this.rightBarrier = rightBarrier;
    }

    /**
     * 
     */
    private void addBarrier(String key, ChildBarrier barrier) {
        barriers.put(key, barrier);
    }

    private void removeBarrier(String barrier) {
        barriers.remove(barrier);
    }

    /**
     * @param barriers the barriers to set
     */
    public void setBarriers(HashMap<String, ChildBarrier> barriers) {
        this.barriers = barriers;
    }
    
    public Iterable<ChildBarrier> getChildBarriers() {
        return barriers.values();
    }
    
    /**
     * @return the barrierColor
     */
    public Color getBarrierColor() {
        return barrierColor;
    }

    /**
     * @param barrierColor the barrierColor to set
     */
    public void setBarrierColor(Color barrierColor) {
        this.barrierColor = barrierColor;
    }

    /**
     * @return the showBarriers
     */
    public boolean isShowBarriers() {
        return showBarriers;
    }

    /**
     * @param showBarriers the showBarriers to set
     */
    public void setShowBarriers(boolean showBarriers) {
        this.showBarriers = showBarriers;
    }
//</editor-fold>

}
