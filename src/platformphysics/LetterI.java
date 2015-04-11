/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformphysics;

import environment.Velocity;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map.Entry;

/**
 *
 * @author kevin.lawrence
 */
public class LetterI extends Letter implements ParentPositionProviderIntf {

    private static String TOP_ARM = "TOP";
    private static String STEM = "STEM";
    private static String BOTTOM_ARM = "BOTTOM";

    private static String TOP_ARM_FLOOR = "TOP_FLOOR";
    private static String TOP_ARM_CEILING = "TOP_ARM_CEILING";
    private static String BOTTOM_ARM_FLOOR = "BOTTOM_ARM_FLOOR";
    private static String BOTTOM_ARM_CEILING = "BOTTOM_CEILING";

    public LetterI(Point position, Velocity velocity) {
        super(position, velocity);

        parts.put(TOP_ARM, new LetterPart(new Point(0, 0), new Rectangle(position.x + 0, position.y + 0, 30, 10)));
        parts.put(STEM, new LetterPart(new Point(10, 10), new Rectangle(position.x + 10, position.y + 10, 10, 50)));
        parts.put(BOTTOM_ARM, new LetterPart(new Point(0, 60), new Rectangle(position.x + 0, position.y + 60, 30, 10)));

//        barriers.put(TOP_ARM_FLOOR, new LetterPart(new Point(0, -1), new Rectangle(position.x + 0, position.y -1, 30, 1)));
//        barriers.put(TOP_ARM_CEILING, new LetterPart(new Point(0, 9), new Rectangle(position.x + 0, position.y + 9, 30, 1)));
//        barriers.put(BOTTOM_ARM_FLOOR, new LetterPart(new Point(0, 59), new Rectangle(position.x + 0, position.y + 59, 30, 1)));
//        barriers.put(BOTTOM_ARM_CEILING, new LetterPart(new Point(0, 69), new Rectangle(position.x + 0, position.y + 69, 30, 1)));

        barriers.put(TOP_ARM_FLOOR, new ChildBarrier(new Point(0, -1), 30, 1, BarrierType.FLOOR, this, new Point(0, 0)));
        barriers.put(TOP_ARM_CEILING, new ChildBarrier(new Point(0, 9), 30, 1, BarrierType.CEILING, this, new Point(0, 9)));
        barriers.put(BOTTOM_ARM_FLOOR, new ChildBarrier(new Point(0, 59), 30, 1, BarrierType.FLOOR, this, new Point(0, 59)));
        barriers.put(BOTTOM_ARM_CEILING, new ChildBarrier(new Point(0, 69), 30, 1, BarrierType.CEILING, this, new Point(0, 69)));

//        for (Entry<String, LetterPart> floor : getBarriers()) {
//            LetterPart part = floor.getValue();
//            part.setColor(Color.RED);
//        }
    }

    @Override
    public void paint(Graphics graphics) {
        for (Entry<String, LetterPart> entry : getLetterParts()) {
            LetterPart part = entry.getValue();
            part.paint(graphics);
        }

        if (debug) {
            for (Entry<String, ChildBarrier> barrierSet : getBarriers()) {
                barrierSet.getValue().paint(graphics);
//                Childe barrier = barierSet.getValue();
//                barrier.paint(graphics);
            }
        }

    }

//<editor-fold defaultstate="collapsed" desc="ParentPositionProviderIntf Interface Methods">
    @Override
    public Point getParentPosition() {
        return this.getPosition();
    }
//</editor-fold>
}
