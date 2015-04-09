/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformphysics;

import environment.Velocity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map.Entry;

/**
 *
 * @author kevin.lawrence
 */
public class LetterI extends Letter {

    private static String TOP = "TOP";
    private static String BEAM = "BEAM";
    private static String BOTTOM = "BOTTOM";

    public LetterI(Point position, Velocity velocity) {
        super(position, velocity);

        parts.put(TOP, new LetterPart(new Point(0, 0), new Rectangle(position.x + 0, position.y + 0, 30, 10)));
        parts.put(BEAM, new LetterPart(new Point(10, 10), new Rectangle(position.x + 10, position.y + 10, 10, 50)));
        parts.put(BOTTOM, new LetterPart(new Point(0, 60), new Rectangle(position.x + 0, position.y + 60, 30, 10)));

        floors.put(TOP, new LetterPart(new Point(0, 8), new Rectangle(position.x + 0, position.y + 8, 30, 2)));
        floors.put(BOTTOM, new LetterPart(new Point(0, 68), new Rectangle(position.x + 0, position.y + 68, 30, 2)));

        for (Entry<String, LetterPart> floor : getFloors()) {
            LetterPart part = floor.getValue();
            part.setColor(Color.RED);
        }
    }

    @Override
    public void paint(Graphics graphics) {
        for (Entry<String, LetterPart> entry : getLetterParts()) {
            LetterPart part = entry.getValue();
            part.paint(graphics);
        }

        if (debug) {
            for (Entry<String, LetterPart> floor : getFloors()) {
                LetterPart part = floor.getValue();
                part.paint(graphics);
            }
        }

    }

}
