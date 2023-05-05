package course.java.project.dreamnotebook.utils;

import javafx.scene.paint.Color;

import java.util.Random;

public class RandomColor {
    public static Color generateSimilarColor(Color baseColor, double range) {
        Random random = new Random();
        double h = baseColor.getHue();
        double s = baseColor.getSaturation();
        double b = baseColor.getBrightness();
        double hDelta = range * (random.nextDouble() * 2 - 1);
        double sDelta = range * (random.nextDouble() * 2 - 1);
        double bDelta = range * (random.nextDouble() * 2 - 1);
        double newH = (h  +hDelta+ 360) % 360;
        double newS = clamp(s + sDelta, 0, 1);
        double newB = clamp(b + bDelta, 0, 1);
        return Color.hsb(newH, newS, newB);
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
