package plane_war.shoot;

import java.awt.*;
import java.awt.image.BufferedImage;

//玩家飞机
public class PlayerPlane extends FlyingObject {
    private int score;
    private Bullet[] bullets;
    private double count = 0.0;
    private int countImg = 0;
    public static int imgIndex = 0;
    private int modeTwoCount = 0;//模式二子弹数
    private BufferedImage[] liveImg = new BufferedImage[]{Test.playerPlane0Img, Test.playerPlane1Img};
    private BufferedImage[] explosionImg = new BufferedImage[]{Test.playerPlane_ember0, Test.playerPlane_ember1, Test.playerPlane_ember2, Test.playerPlane_ember3};
    public PlayerPlane(){
        super(150, 300, 10, Test.playerPlane0Img);
    }
    @Override
    public void decreaseBlood(){
        setBlood(getBlood() - 1);
    }
    @Override
    public void move() {
        //切换图片
        if(this.getBlood() > 0){
            count += 0.1;
            this.setImg(liveImg[(int)count % 2]);
        }
        else{
            this.setImg(explosionImg[imgIndex]);
            if(countImg % 30 == 0){
                imgIndex++;
            }
            countImg++;
            if(imgIndex >= 4){
                imgIndex = 3;
            }
        }
    }

    //飞机射出子弹数组
    public Bullet[] shoot(int type){
        if(type == 1){
            Bullet[] bullets = new Bullet[type];//局部对象
            bullets[0] = new Bullet(this.getX() + this.getWidth() / 2 - 3, this.getY() - 10);
            return bullets;
        }
        else if(type == 2){
            //设定mode2子弹数为20发
            if(++modeTwoCount == 20){
                Test.shootingMode = 1;
            }
            Bullet[] bullets = new Bullet[type];
            bullets[0] = new Bullet(this.getX() + this.getWidth() / 2 - 24, this.getY() - 10);
            bullets[1] = new Bullet(this.getX() + this.getWidth() / 2 + 20, this.getY() - 10);
            return bullets;
        }
        return null;
    }
    //增加生命值
    public void increaseBlood(){
        int blood = getBlood();
        setBlood(blood + 1);
    }
    //增加分数
    public void increaseScore(){
        int score = getScore();
        setScore(score + 1);
    }
    private void gifSimulation(){
        //凭借多张图片的循环播放实现动态效果
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Bullet[] getBullets() {
        return bullets;
    }

    public void setBullets(Bullet[] bullets) {
        this.bullets = bullets;
    }
}
