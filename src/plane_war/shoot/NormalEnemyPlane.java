package plane_war.shoot;

import java.awt.image.BufferedImage;

public class NormalEnemyPlane extends FlyingObject {
    private int speed = 3;
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
       super((int)(Math.random() * (Test.WIDTH - Test.normalEnemyPlaneImg.getWidth() - 10)), -(Test.normalEnemyPlaneImg.getHeight()), 1, 1,Test.normalEnemyPlaneImg, new BufferedImage[]{Test.normalEnemyPlane_ember0, Test.normalEnemyPlane_ember1, Test.normalEnemyPlane_ember2, Test.normalEnemyPlane_ember3});
//        super((int)(Math.random() * 400), 0, Test.normalEnemyPlaneImg);
    }
}
