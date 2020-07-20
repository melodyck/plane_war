package plane_war.shoot;

import javax.swing.*;
import java.awt.image.BufferedImage;

public abstract class FlyingObject extends JPanel {
    private int awardScore;
    private int x;
    private int y;
    private BufferedImage img;
    private int width;
    private int height;
    private int blood;
    private BufferedImage[] explosionImgList;
    public int explosionImgIndex = 0;

    public BufferedImage[] getExplosionImgList() {
        return explosionImgList;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public int getAwardScore() {
        return awardScore;
    }

    //移动方法
    public abstract void move();
    //飞行物血量减少方法
    public abstract void decreaseBlood();
    //定义父类构造方法
    FlyingObject(){};
    protected FlyingObject(int x, int y, int blood, int awardScore, BufferedImage img, BufferedImage[] explosionImgList){
        this.x = x;
        this.y = y;
        this.img = img;
        this.width = img.getWidth();
        this.height = img.getHeight();
        this.blood = blood;
        this.awardScore = awardScore;
        this.explosionImgList = explosionImgList;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
