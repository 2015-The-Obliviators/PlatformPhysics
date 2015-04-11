/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformphysics;

import environment.Direction;
import environment.Environment;
import environment.Velocity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map.Entry;

/**
 *
 * @author kevin.lawrence
 */
class PlatformEnvironment extends Environment implements AccelerationProvider {
    
    private ArrayList<Barrier> barriers;
    private ArrayList<Letter> letters;
    private ArrayList<Letter> constraints;

    {
        barriers = new ArrayList<>();
        barriers.add(new Barrier(new Point(10, 500), 800, 2, BarrierType.FLOOR));
        barriers.add(new Barrier(new Point(60, 100), 200, 2, BarrierType.FLOOR));
        
        letters = new ArrayList<>();
        letters.add(new LetterI(new Point(10, 10), new Velocity(1, 0)));
        
        for(Letter letter : letters){
            letter.setAccelerationProvider(this);
        }
        
    }
    
    @Override
    public void initializeEnvironment() {
        this.setBackground(Color.WHITE);
    }

    @Override
    public void timerTaskHandler() {
        checkIntersections();
        
        if (letters != null){
            for(Letter letter : letters){
                letter.move();
            }
        }
    }
    
    private void checkIntersections(){
        boolean letterBlocked = false;
        for (Barrier barrier : barriers){
            for (Letter letter : letters){
                letterBlocked = false;
            
                for (Entry<String, ChildBarrier> letterBarrier : letter.getBarriers()){
                    if (barrier.intersects(letterBarrier.getValue())){
                        // assess the nature of the intersection (barrier type) 
                        // stop the appropriate motion
                        if (barrier.getType() == BarrierType.FLOOR){
                            if (letterBarrier.getValue().getType() == BarrierType.CEILING){
                                letter.stop();
//                                letter.getVelocity().y = 0;
                                letterBlocked |= true;
//                                letter.setBlocked(true);
                            }
                        }
                    }
                }
//                if (letterBlocked){
                    letter.setBlocked(letterBlocked);
//                }
            }
        }
    }

    int speed = 2;
    
    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            for (Letter letter : letters){
                letter.move(Direction.LEFT, speed);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            for (Letter letter : letters){
                letter.move(Direction.RIGHT, speed);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP){
            for (Letter letter : letters){
                letter.move(Direction.UP, speed);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            for (Letter letter : letters){
                letter.move(Direction.DOWN, speed);
            }
        }
        
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
    
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (letters != null){
            for(Letter letter : letters){
                letter.paint(graphics);
            }
        }

        if (barriers != null){
            for(Barrier barrier : barriers){
                barrier.paint(graphics);
            }
        }
    }

//<editor-fold defaultstate="collapsed" desc="AccelerationProvider">
    private Vector2D gravity = new Vector2D(0, 1);
    
    @Override
    public Vector2D getAcceleration() {
        return gravity;
    }
//</editor-fold>
    
}
