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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author kevin.lawrence
 */
public abstract class Letter extends Actor implements ParentPositionProviderIntf {

//<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        childBarriers = new ArrayList<>();
        parts = new HashMap<>();
    }

    public Letter(Point position, Velocity velocity) {
        super(position, velocity);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Methods">
    int maxX = 3;
    int maxy = 2;

    public void accelerate(Vector2D accelerationVector) {
        if (!horizBlocked) {
            int x = getVelocity().x + accelerationVector.x;
            if (Math.abs(x) < maxX) {
                getVelocity().x = x;
            }
        }

        if (!vertBlocked) {
            int y = getVelocity().y + accelerationVector.y;
            if (Math.abs(y) < maxX) {
                getVelocity().y = y;
            }
        }
    }

    @Override
    public void move() {
        if (getAccelerationProvider() != null) {
            accelerate(getAccelerationProvider().getAcceleration());
        }

        move(getVelocity().x, getVelocity().y);
    }

    public void move(Direction direction, int distance) {
        int x = 0;
        int y = 0;

        switch (direction) {
            case LEFT:
                x = -1 * distance;
                break;
            case RIGHT:
                x = 1 * distance;
                break;
            case UP:
                y = -1 * distance;
                break;
            case DOWN:
                y = 1 * distance;
                break;
        }

        move(x, y);
    }

    public void move(int x, int y) {
        Point newPosition = (Point) getPosition().clone();

        if (!horizBlocked) {
            newPosition.x += x;
        }

        if (!vertBlocked) {
            newPosition.y += y;
        }

        setPosition(newPosition);
    }

    @Override
    public abstract void paint(Graphics graphics);
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="ParentPositionProviderIntf Interface Methods">
    @Override
    public Point getParentPosition() {
        return this.getPosition();
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Properties">
    private AccelerationProvider accelerationProvider;
    private HashMap<String, LetterPart> parts;

    protected boolean debug = true;
    private boolean horizBlocked = false;
    private boolean vertBlocked = false;

    @Override
    public void setPosition(Point position) {
        super.setPosition(position);

        // adjust the position of all the component parts: the parent has moved,
        // therefore the parts (and barriers!) must move
        for (Entry<String, LetterPart> letterPartMap : getLetterParts()) {
            LetterPart letterPart = letterPartMap.getValue();
            letterPart.getRectangle().setLocation(position.x + letterPart.getParentOffset().x, position.y + letterPart.getParentOffset().y);
        }

        for (ChildBarrier barrier : getBarriers()) {
            barrier.updatePosition();
        }
    }

    public Set<Entry<String, LetterPart>> getLetterParts() {
        return getParts().entrySet();
    }

    private ArrayList<ChildBarrier> childBarriers;// = new ArrayList<>();

    public Iterable<ChildBarrier> getBarriers() {
//        return barriers.values();
        return childBarriers;
    }

    private void addBarriers(LetterPart part) {
        for (ChildBarrier childBarrier : part.getChildBarriers()) {
            childBarriers.add(childBarrier);
        }
    }

    /**
     * @return the parts
     */
    public HashMap<String, LetterPart> getParts() {
        return parts;
    }

    /**
     *
     */
    public void addPart(String partKey, LetterPart part) {
        parts.put(partKey, part);

        addBarriers(part);
    }

    /**
     * @return the blocked
     */
    public boolean isHorizBlocked() {
        return horizBlocked;
    }

    /**
     * @param horizBlocked the horizBlocked to set
     */
    public void setHorizBlocked(boolean horizBlocked) {
        this.horizBlocked = horizBlocked;

        if (horizBlocked) {
            stop();
        }
    }

    /**
     * @return the vertBlocked
     */
    public boolean isVertBlocked() {
        return vertBlocked;
    }

    /**
     * @param vertBlocked the vertBlocked to set
     */
    public void setVertBlocked(boolean vertBlocked) {
        this.vertBlocked = vertBlocked;
    }

    /**
     * @return the accelerationProvider
     */
    public AccelerationProvider getAccelerationProvider() {
        return accelerationProvider;
    }

    /**
     * @param accelerationProvider the accelerationProvider to set
     */
    public void setAccelerationProvider(AccelerationProvider accelerationProvider) {
        this.accelerationProvider = accelerationProvider;
    }
//</editor-fold>

}
