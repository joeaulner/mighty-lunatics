package game;

import game.util.SimpleFramework;

import java.awt.*;
import java.awt.event.ComponentEvent;

public class ChessGame extends SimpleFramework {

    private ChessGame() {

    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void onComponentResized(ComponentEvent e) {
        super.onComponentResized(e);
    }

    @Override
    protected void processInput(float delta) {
        super.processInput(delta);
    }

    @Override
    protected void updateObjects(float delta) {
        super.updateObjects(delta);
    }

    @Override
    protected void render(Graphics g) {
        super.render(g);
    }

    @Override
    protected void terminate() {
        super.terminate();
    }

    public static void main(String[] args) {
        launchApp(new ChessGame());
    }
}
