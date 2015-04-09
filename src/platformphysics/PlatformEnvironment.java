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
class PlatformEnvironment extends Environment {
    
    private ArrayList<Letter> letters;

    {
        letters = new ArrayList<>();
        letters.add(new LetterI(new Point(10, 10), new Velocity(0, 0)));
        
        
    }
    
    @Override
    public void initializeEnvironment() {
        this.setBackground(Color.WHITE);
    }

    @Override
    public void timerTaskHandler() {
        if (letters != null){
            for(Letter letter : letters){
                letter.move();
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
    }
    
}
