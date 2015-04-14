/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformphysics;

import java.awt.Rectangle;

/**
 *
 * @author kevin.lawrence
 */
public class Block extends Rectangle {
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
    
    }
    
    public Block(int x, int y, int width, int height){
        super(x, y, width, height);
    }
    
    public Block(int x, int y, int width, int height,
            boolean topBarrier, boolean bottomBarrier, boolean leftBarrier,
            boolean rightBarrier){
        super(x, y, width, height);
    }
//</editor-fold>
    
}
