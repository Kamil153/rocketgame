package dev.kamilpolak.rocketgame;

import com.badlogic.gdx.math.Vector2;

public class Util {

    private static Vector2 calculateRelativePosition(Vector2 position, float rotation, Vector2 offset) {
        float radius = offset.len();
        float angle = offset.angleRad();
        Vector2 result = new Vector2();
        result.x = position.x + radius*(float)Math.cos(rotation + angle);
        result.y = position.y + radius*(float)Math.sin(rotation + angle);
        return result;
    }
}
