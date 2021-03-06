package com.example.gravity.objects;

import android.graphics.Rect;

import com.example.gravity.Utilits.UtilResource;
import com.example.my_framework.AnimationFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.utilits.UtilRandomFW;

public class Enemy extends ObjectFW {

    AnimationFW animEnemy;

    public Enemy(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {
        this.maxScreenX = maxScreenX - UtilResource.spriteEnemy.get(0).getWidth();
        this.maxScreenY = maxScreenY;
        this.minScreenY = 0;
        this.minScreenX = minScreenX;
        radius = UtilResource.spriteEnemy.get(0).getWidth()/5;

        x = UtilRandomFW.getGap(minScreenX, maxScreenX);
        y = maxScreenY;
        switch (enemyType) {
            case 1:
                speed = UtilRandomFW.getGap(1, 6);
                animEnemy = new AnimationFW(3,
                        UtilResource.spriteEnemy.get(0),
                        UtilResource.spriteEnemy.get(1),
                        UtilResource.spriteEnemy.get(2),
                        UtilResource.spriteEnemy.get(3));
                break;

            case 2:
                speed = UtilRandomFW.getGap(4, 9);
                break;
        }

    }

    public void update(double speedPlayer) {
        y += speed;
        if (y > maxScreenY) {
            y = minScreenY;
            x = UtilRandomFW.getGap(minScreenX, maxScreenX);
        }
        animEnemy.runAnimation();

        hitBox = new Rect(x, y, UtilResource.spriteEnemy.get(0).getWidth(),UtilResource.spriteEnemy.get(0).getHeight());

    }
    public void drawing(GraphicsFW graphicsFW){
        animEnemy.drawingAnimation(graphicsFW,x,y);
    }

}
