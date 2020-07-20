package plane_war.shoot;

import java.awt.image.BufferedImage;

public class Bee extends FlyingObject implements Award{
    private int x_speed = 3;
    private int y_speed = 1;
    public int bonus;//定义奖励变量 为Bee的特殊变量
    @Override
    public void move() {
        //x先变成最大在变成最小,x_speed从+3变为-3
        this.setX(this.getX() + x_speed);
        this.setY(this.getY() + y_speed);
        //如果x坐标到达临界值速度变为负数
        if(this.getX() >= Test.WIDTH - this.getWidth() - 10){
            x_speed = -3;
        }
        else if(this.getX() <= 0){
            x_speed = 3;
        }
    }

    @Override
    public void decreaseBlood() {
        setBlood(getBlood() - 1);
    }

    public Bee(){
       super((int)(Math.random() * (Test.WIDTH - Test.beeImg.getWidth())), -(Test.beeImg.getHeight()), 1, 10, Test.beeImg,
               new BufferedImage[]{Test.bee_ember0, Test.bee_ember1, Test.bee_ember2, Test.bee_ember3});
       bonus = (int)Math.round(Math.random() + 1);//随机生成奖励:加一点血或者双倍子弹
//        super((int)(Math.random() * 400), 0, Test.beeImg);
    }

    @Override
    public void award(PlayerPlane playerPlane) {
        //如果Bee奖励类型为1 玩家飞机加一点生命值
        if(bonus == AWARD_OF_BLOOD){
            playerPlane.increaseBlood();
        }
        else if(bonus == AWARD_OF_FIRE){
            Test.shootingMode = 2;
        }
    }
}
