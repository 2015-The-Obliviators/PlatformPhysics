/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformphysics;

import environment.Actor;
import environment.Direction;
import environment.Velocity;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author kevin.lawrence
 */
public abstract class Letter extends Actor {

    private LetterPart currentFloor;

    protected boolean debug = true;
    protected HashMap<String, LetterPart> floors;
    protected HashMap<String, LetterPart> parts;

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        floors = new HashMap<>();
        parts = new HashMap<>();
    }

    public Letter(Point position, Velocity velocity) {
        super(position, velocity);
    }
//</editor-fold>
    
    public void move(Direction direction, int distance){
        int x = 0;
        int y = 0;
        
        switch (direction){
            case LEFT :
                x = -1 * distance;
                break;
            case RIGHT :
                x = 1 * distance;
                break;
            case UP :
                y = -1 * distance;
                break;
            case DOWN :
                y = 1 * distance;
                break;
        }
        
        Point newPosition = (Point) getPosition().clone();
        newPosition.x += x;
        newPosition.y += y;
        setPosition(newPosition);
    }

    @Override
    public abstract void paint(Graphics graphics);

    @Override
    public void setPosition(Point position) {
        super.setPosition(position);

        // adjust the position of all the component parts: the parent has moved,
        // therefore the parts (and floors!) must move
        for (Entry<String, LetterPart> letterPart : getLetterParts()) {
            LetterPart part = letterPart.getValue();
            part.getRectangle().setLocation(position.x + part.getParentOffset().x, position.y + part.getParentOffset().y);
        }

        for (Entry<String, LetterPart> floor : getFloors()) {
            LetterPart part = floor.getValue();
            part.getRectangle().setLocation(position.x + part.getParentOffset().x, position.y + part.getParentOffset().y);
        }
    }

    public Set<Entry<String, LetterPart>> getLetterParts() {
        return parts.entrySet();
    }
    
    public Set<Entry<String, LetterPart>> getFloors() {
        return floors.entrySet();
    }
    

    

}
