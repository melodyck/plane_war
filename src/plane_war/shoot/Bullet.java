package plane_war.shoot;

public class Bullet extends FlyingObject {
    private int speed = 3;
    @Override
    public void move() {
        //子弹从初始位置向上直线移动,直到移出窗口边缘
        int y = super.getY();
        this.setY(y - speed);
    }

    @Override
    public void decreaseBlood() {
        setBlood(getBlood() - 1);
    }

    Bullet(){};
    //根据玩家机的x,y计算子弹的坐标
    public Bullet(int x, int y){
        super(x, y, 1,Test.bulletImg);
    }
}
