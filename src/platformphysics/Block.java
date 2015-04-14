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
import java.util.ArrayList;

/**
 *
 * @author kevin.lawrence
 */
public class Block extends Rectangle {
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        barriers = new ArrayList<>();

        topBarrier = true;
        bottomBarrier = true;
        leftBarrier = true;
        rightBarrier = true;

        updateBarriers();
    }
    
    public Block(int x, int y, int width, int height){
        super(x, y, width, height);
    }
    
    public Block(int x, int y, int width, int height,
            boolean topBarrier, boolean bottomBarrier, boolean leftBarrier,
            boolean rightBarrier){
        super(x, y, width, height);
        
        this.topBarrier = topBarrier;
        this.bottomBarrier = bottomBarrier;
        this.leftBarrier = leftBarrier;
        this.rightBarrier = rightBarrier;
        
        updateBarriers();
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Painting">
    private Color fillColor;
    private Color borderColor;
    
    private boolean drawBarriers;
    
    public void paint(Graphics graphics){
        graphics.setColor(getFillColor());
        graphics.fillRect(x, y, width, height);
        
        graphics.setColor(getBorderColor());
        graphics.drawRect(x, y, width, height);
        
        if (isDrawBarriers()){
            for(Barrier barrier : barriers){
                barrier.paint(graphics);
            }
        }
    }
    
    /**
     * @return the fillColor
     */
    public Color getFillColor() {
        return fillColor;
    }
    
    /**
     * @param fillColor the fillColor to set
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
    
    /**
     * @return the borderColor
     */
    public Color getBorderColor() {
        return borderColor;
    }
    
    /**
     * @param borderColor the borderColor to set
     */
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }
    
    /**
     * @return the drawBarriers
     */
    public boolean isDrawBarriers() {
        return drawBarriers;
    }
    
    /**
     * @param drawBarriers the drawBarriers to set
     */
    public void setDrawBarriers(boolean drawBarriers) {
        this.drawBarriers = drawBarriers;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private boolean topBarrier;
    private boolean bottomBarrier;
    private boolean leftBarrier;
    private boolean rightBarrier;
    
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
        this.rightBarrier = rightBarrier;
    }
    
    ArrayList<Barrier> barriers = new ArrayList<>();
    public ArrayList<Barrier> getBarriers(){
        return barriers;
    }
    
    private void updateBarriers() {
        barriers.clear();
        
        if (hasTopBarrier()){
            barriers.add(new Barrier(getLocation(), width, height / 2, BarrierType.FLOOR));
        }
        
        if (hasBottomBarrier()){
            Point loc = (Point) getLocation().clone();
            loc.y = height / 2;
            barriers.add(new Barrier(loc, width, height / 2, BarrierType.CEILING));
        }
        
        if (hasLeftBarrier()){
            barriers.add(new Barrier(getLocation(), width / 2, height, BarrierType.WALL));
        }
        
        if (hasRightBarrier()){
            Point loc = (Point) getLocation().clone();
            loc.x = width / 2;
            barriers.add(new Barrier(this.getLocation(), width / 2, height, BarrierType.WALL));
        }
    }
    
//</editor-fold>

  
}
