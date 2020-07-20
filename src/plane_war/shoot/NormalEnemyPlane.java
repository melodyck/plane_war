package plane_war.shoot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NormalEnemyPlane extends FlyingObject{
    private int speed = 3;
    public int explosionImgIndex = 0;
    public BufferedImage[] explosionImg = new BufferedImage[]{Test.normalEnemyPlane_ember0, Test.normalEnemyPlane_ember1, Test.normalEnemyPlane_ember2, Test.normalEnemyPlane_ember3};
    @Override
    public void move() {
        int y;
        y = this.getY();
        this.setY(y + speed);

    }

    @Override
    public void decreaseBlood() {
        setBlood(getBlood() - 1);
    }

    public NormalEnemyPlane(){
       super((int)(Math.random() * (Test.WIDTH - Test.normalEnemyPlaneImg.getWidth() - 10)), -(Test.normalEnemyPlaneImg.getHeight()), 1, Test.normalEnemyPlaneImg);
//        super((int)(Math.random() * 400), 0, Test.normalEnemyPlaneImg);
    }
}
