/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformphysics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author kevin.lawrence
 */
public final class BlockLetterI {

    public BlockLetterI(int x, int y, int width, int height, boolean stationary) {
        topBar = new BlockLetterPart(x, y, width, height / 5);
        stem = new BlockLetterPart(x, y, width / 3, height * 3 / 5);
        bottomBar = new BlockLetterPart(x, y, width, height / 5);

        topBar.setFillColor(Color.YELLOW);
        stem.setFillColor(Color.BLUE);
        bottomBar.setFillColor(Color.CYAN);

        parts = new ArrayList<>();
        parts.add(topBar);
        parts.add(stem);
        parts.add(bottomBar);

        stem.setConnectionUpdateHandler(new ConnectionUpdateHandlerIntf() {
            @Override
            public void onUpdate(Rectangle connector, Rectangle connected) {
                connected.setLocation(connector.x + (connector.width / 2) - (connected.width / 2), connector.y + connector.height);
            }
        });

        bottomBar.setConnectionUpdateHandler(new ConnectionUpdateHandlerIntf() {
            @Override
            public void onUpdate(Rectangle connector, Rectangle connected) {
                connected.setLocation(connector.x + (connector.width / 2) - (connected.width / 2), connector.y + connector.height);
            }
        });

        topBar.registerConnectionListeners(stem);
        stem.registerConnectionListeners(bottomBar);
        
        setLocation(x, y);
    }

    ArrayList<BlockLetterPart> parts;
    BlockLetterPart topBar, stem, bottomBar;
    int x, y;

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;

        topBar.setLocation(x, y);
    }

    public static enum Direction {
        UP, DOWN
    };

    public void grow(Direction direction) {
        stem.height += 1;
        if (direction == Direction.UP) {
            setLocation(x, y - 1);
        } else {
            setLocation(x, y);
        }
    }

    public void shrink(Direction direction) {
        stem.height -= 1;
        if (direction == Direction.DOWN) {
            setLocation(x, y + 1);
        } else {
            setLocation(x, y);
        }
    }

    public void paint(Graphics graphics) {
        parts.stream().forEach((part) -> {
            part.paint(graphics);
        });
    }

}
