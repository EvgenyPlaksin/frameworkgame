package com.example.gravity.clases;

import com.example.gravity.Utilits.UtilResource;
import com.example.gravity.generator.GeneratorBackground;
import com.example.gravity.generator.GeneratorEnemy;
import com.example.gravity.objects.HUD;
import com.example.gravity.objects.MainPlayer;
import com.example.my_framework.CollisionDetect;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;

public class GameManager {
    private int maxScreenY;
    private int maxScreenX;
    private int minScreenY;
    private int minScreenX;

    private int passedDistance;
    private int currentSpeedPlayer;
    private int currentShieldsPlayer;

    public static boolean gameOver;

    public int getPassedDistance(){

        return passedDistance;

    }

    MainPlayer mainPlayer;
    GeneratorBackground generatorBackground;
    GeneratorEnemy generatorEnemy;
    HUD hud;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        hud = new HUD(coreFW);
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        minScreenY = 0;
        minScreenX = hud.getHEIGHT_HUG();
        mainPlayer = new MainPlayer(coreFW, maxScreenX, maxScreenY, minScreenY);
        generatorBackground = new GeneratorBackground(sceneWidth, sceneHeight, minScreenY);
        generatorEnemy = new GeneratorEnemy(sceneWidth, sceneHeight,minScreenY);
        gameOver = false;
    }

    public void update() {
        generatorBackground.update(mainPlayer.getSpeedPlayer());
        mainPlayer.update();
        generatorEnemy.update(mainPlayer.getSpeedPlayer());

        passedDistance+=mainPlayer.getSpeedPlayer();
        currentSpeedPlayer =(int) mainPlayer.getSpeedPlayer()*60;
        currentShieldsPlayer = mainPlayer.getShieldsPlayer();

        hud.update(passedDistance, currentSpeedPlayer, currentShieldsPlayer);

        chekHint();

    }

    private void chekHint() {

        for (int i = 0; i < generatorEnemy.enemyArrayList.size(); i++) {

            if (CollisionDetect.collisionDetect(mainPlayer, generatorEnemy.enemyArrayList.get(i))) {
                UtilResource.hit.play(1);
                mainPlayer.hitEnemy();
                generatorEnemy.hitPlayer(generatorEnemy.enemyArrayList.get(i));

            }

        }
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW) {

        mainPlayer.drawing(graphicsFW);
        generatorBackground.drawing(graphicsFW);
        generatorEnemy.drawing(graphicsFW);
        hud.drawing(graphicsFW);
    }


}

