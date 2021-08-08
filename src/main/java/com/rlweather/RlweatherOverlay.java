package com.rlweather;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import java.awt.Color;
import java.util.Random;

public class RlweatherOverlay extends Overlay
{
    private Client client;
    private final RlweatherPlugin plugin;
    private final RlweatherConfig config;

    // collections
    private final LinkedList<Drop> rain = new LinkedList<Drop>();
    private final LinkedList<Drop> snow = new LinkedList<Drop>();

    // misc
    private double chanceOfSpawn = 0.8;

    // garbage collectors
    private final List<Drop> spentRain = new ArrayList<Drop>();
    private final List<Drop> spentSnow = new ArrayList<Drop>();


    @Inject
    public RlweatherOverlay(Client client, RlweatherPlugin plugin, RlweatherConfig config) {
        super(plugin);
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
        this.plugin = plugin;
        this.client = client;
        this.config = config;
    }

    @Override
    public Dimension render(Graphics2D graphics) {

        BufferedImage image = new BufferedImage(client.getCanvasWidth(), client.getCanvasHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();

        // LIGHTNING
        if(plugin.PERFORM_LIGHTNING) {
            plugin.PERFORM_LIGHTNING = false; // we only want this lasting 1fr
            g.setColor(config.lightningColor());
            g.fillRect(0, 0, client.getCanvasWidth(), client.getCanvasHeight());
        }


        // RAIN
        if(plugin.PERFORM_RAIN) {

            // maybe add new rain this frame
            if (Math.random() < chanceOfSpawn) {
                addDrop(rain, image.getWidth(), config.rainColor(), config.rainWind(), config.rainGravity(), config.rainDiv());
            }

            // loop existing rain
            for (Drop rainStreak : rain) {

                // queue remove spent rain (gone offscreen height or width)
                if (rainStreak.y1 >= image.getHeight() || rainStreak.x1 >= image.getWidth()) {
                    spentRain.add(rainStreak);
                    continue;
                }

                g.setColor(rainStreak.color);
                for (int i = 0; i <= config.rainThickness(); i++) {
                    g.drawLine(rainStreak.x1 + i, rainStreak.y1 + i, rainStreak.x2 + i, rainStreak.y2 + i);
                }

                // update positions
                rainStreak.update();
            }
        }

        // SNOW
        if(plugin.PERFORM_SNOW) {
            // add new snow every tick if chanced
            if (Math.random() < chanceOfSpawn) {
                addDrop(snow, image.getWidth(), config.snowColor(), config.snowWind(), config.snowGravity(), config.snowDiv());
            }

            // loop existing snow
            for (Drop snowStreak : snow) {

                // queue remove spent rain (gone offscreen height or width)
                if (snowStreak.y1 >= image.getHeight() || snowStreak.x1 >= image.getWidth()) {
                    spentSnow.add(snowStreak);
                    continue;
                }

                g.setColor(snowStreak.color);
                for (int i = 0; i <= config.snowThickness(); i++) {
                    int radius = config.snowThickness() / 2;
                    g.fillOval(snowStreak.x1 - radius, snowStreak.y1 - radius, config.snowThickness(), config.snowThickness());
                }

                // update positions
                snowStreak.update();
            }
        }

        // garbage collect spent rain & snow Drops
        rain.removeAll(spentRain);
        snow.removeAll(spentSnow);

        // render everything
        graphics.drawImage(image, 0, 0, null);

        return null;
    }

    private void addDrop(List list, int width, Color color, int wind, int gravity, int div) {
        list.add(new Drop(width, color, wind, gravity, div));
    }
}
