package plane_war.shoot;

import java.awt.image.BufferedImage;

public class IntensiveEnemyPlane extends FlyingObject {
    private int speed = 2;
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


    public IntensiveEnemyPlane(){
       super((int)(Math.random() * (Test.WIDTH - Test.intensiveEnemyPlaneImg.getWidth())), -(Test.intensiveEnemyPlaneImg.getHeight()), 4, 5, Test.intensiveEnemyPlaneImg,
               new BufferedImage[]{Test.intensiveEnemyPlane_ember0, Test.intensiveEnemyPlane_ember1, Test.intensiveEnemyPlane_ember2, Test.intensiveEnemyPlane_ember3});
//        super((int)(Math.random() * 400), 0, Test.intensiveEnemyPlaneImg);
    }
}
