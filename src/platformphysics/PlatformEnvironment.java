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

/**
 *
 * @author kevin.lawrence
 */
class PlatformEnvironment extends Environment implements AccelerationProvider {

    private ArrayList<Barrier> environmentBarriers;
    private ArrayList<Letter> letters;
    private ArrayList<Letter> constraints;
    private ArrayList<Block> blocks;
    
    public static Color PURPLE =  new Color(100, 40, 120);
    public static Color YELLOW =  new Color(255, 250, 85);
    public static Color YELLOW_LIGHT =  new Color(255, 250, 85, 10);
    
    BlockLetterI bli;

    {
        environmentBarriers = new ArrayList<>();

        environmentBarriers.add(new Barrier(new Point(60, 100), 200, 2, BarrierType.FLOOR));
        environmentBarriers.add(new Barrier(new Point(500, 490), 1, 10, BarrierType.WALL));
        environmentBarriers.add(new Barrier(new Point(10, 500), 800, 10, BarrierType.FLOOR));

        letters = new ArrayList<>();
        letters.add(new LetterI(new Point(10, 40), new Velocity(0, 0)));

        for (Letter letter : letters) {
            letter.setAccelerationProvider(this);
        }
        
        blocks = new ArrayList<>();
        blocks.add(new Block(325, 250, 50, 50, true));
        blocks.add(new Block(400, 300, 50, 50, true));
        blocks.add(new Block(500, 375, 50, 50, true));
        
        blocks.stream().forEach((block) -> {
            block.setBorderColor(PURPLE);
            block.setFillColor(YELLOW_LIGHT);
        });
        
        
        bli = new BlockLetterI(300, 300, 100, 150, false);

    }

    @Override
    public void initializeEnvironment() {
        this.setBackground(Color.WHITE);
    }

    @Override
    public void timerTaskHandler() {
        checkIntersections();

        if (letters != null) {
            for (Letter letter : letters) {
                letter.move();
            }
        }
    }

    private ArrayList<Barrier> getAllBarriers(){
        ArrayList<Barrier> barriers = new ArrayList<>();
        
        barriers.addAll(environmentBarriers);
        blocks.stream().forEach((block) -> {
            barriers.addAll(block.getBarriers());
        });
        
        return barriers;
    }
    
    
    
    private void checkIntersections() {
        boolean letterVBlocked, letterHBlocked;
        
        for (Letter letter : letters) {
            letterVBlocked = false;
            letterHBlocked = false;
            
            for (Barrier barrier : getAllBarriers()) {
//                for (Entry<String, ChildBarrier> letterBarrier : letter.getBarriers()) {
                for (ChildBarrier letterBarrier : letter.getBarriers()) {
                    if (barrier.intersects(letterBarrier)) {
                        // assess the nature of the intersection (barrier type) 
                        // stop the appropriate motion
                        if (barrier.getType() == BarrierType.FLOOR) {
                            if (letterBarrier.getType() == BarrierType.CEILING) {
                                letterVBlocked |= true;
                            }
                        }

                        if (barrier.getType() == BarrierType.CEILING) {
                            if (letterBarrier.getType() == BarrierType.FLOOR) {
                                letterVBlocked |= true;
                            }
                        }
                        
                        if (barrier.getType() == BarrierType.WALL) {
                            if (letterBarrier.getType() == BarrierType.WALL) {
                                letterVBlocked |= true;
                            }
                        }
                    }
                }
            }

            letter.setHorizBlocked(letterHBlocked);
            letter.setVertBlocked(letterVBlocked);
        }
    }

    int speed = 6;

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            letters.stream().forEach((letter) -> {
                letter.move(Direction.LEFT, speed);
            });
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            for (Letter letter : letters) {
                letter.move(Direction.RIGHT, speed);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            for (Letter letter : letters) {
//                letter.move(Direction.UP, speed);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            for (Letter letter : letters) {
//                letter.move(Direction.DOWN, speed);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            if (bli != null){
                bli.setLocation(bli.x, bli.y - 1);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            if (bli != null){
                bli.setLocation(bli.x - 1, bli.y);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            if (bli != null){
                bli.setLocation(bli.x, bli.y + 1);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            if (bli != null){
                bli.setLocation(bli.x + 1, bli.y);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_1) {
            if (bli != null){
                bli.grow(BlockLetterI.Direction.UP);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            if (bli != null){
                bli.grow(BlockLetterI.Direction.DOWN);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            if (bli != null){
                bli.shrink(BlockLetterI.Direction.UP);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_4) {
            if (bli != null){
                bli.shrink(BlockLetterI.Direction.DOWN);
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
        if (letters != null) {
            letters.stream().forEach((letter) -> {
                letter.paint(graphics);
            });
        }

        if (environmentBarriers != null) {
            environmentBarriers.stream().forEach((barrier) -> {
                barrier.paint(graphics);
            });
        }
 
        if (blocks != null) {
            blocks.stream().forEach((block) -> {
                block.paint(graphics);
            });
        }
        
        if (bli != null){
            bli.paint(graphics);
        }
    }

//<editor-fold defaultstate="collapsed" desc="AccelerationProvider">
    private static Vector2D gravity = new Vector2D(1, 1);

    @Override
    public Vector2D getAcceleration() {
        return gravity;
    }
//</editor-fold>

}
