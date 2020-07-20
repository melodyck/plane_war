package plane_war.shoot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

public class Test extends JPanel {
    public static BufferedImage normalEnemyPlaneImg;
    public static BufferedImage normalEnemyPlane_ember0;
    public static BufferedImage normalEnemyPlane_ember1;
    public static BufferedImage normalEnemyPlane_ember2;
    public static BufferedImage normalEnemyPlane_ember3;

    public static BufferedImage backGroundImg;


    public static BufferedImage beeImg;
    public static BufferedImage bee_ember0;
    public static BufferedImage bee_ember1;
    public static BufferedImage bee_ember2;
    public static BufferedImage bee_ember3;

    public static BufferedImage intensiveEnemyPlaneImg;
    public static BufferedImage intensiveEnemyPlane_ember0;
    public static BufferedImage intensiveEnemyPlane_ember1;
    public static BufferedImage intensiveEnemyPlane_ember2;
    public static BufferedImage intensiveEnemyPlane_ember3;

    public static BufferedImage bulletImg;

    public static BufferedImage bossPlane;
    public static BufferedImage bossPlane_ember0;
    public static BufferedImage bossPlane_ember1;
    public static BufferedImage bossPlane_ember2;
    public static BufferedImage bossPlane_ember3;
    public static BufferedImage bossPlane_ember4;
    public static BufferedImage bossPlane_ember5;
    public static BufferedImage bossPlane_hit;
    public static BufferedImage bossPlane_n1;
    public static BufferedImage bossPlane_n2;

    public static BufferedImage gameOverImg;

    public static BufferedImage playerPlane0Img;
    public static BufferedImage playerPlane1Img;
    public static BufferedImage playerPlane_ember0;
    public static BufferedImage playerPlane_ember1;
    public static BufferedImage playerPlane_ember2;
    public static BufferedImage playerPlane_ember3;

    public static BufferedImage pauseImg;
    public static BufferedImage startImg;

    private PlayerPlane playerPlane = new PlayerPlane();
    private ArrayList<FlyingObject> flyingObjectArrayList = new ArrayList();//定义飞行物列表
    private ArrayList<Bullet[]> bullets = new ArrayList<>();//定义子弹列表
    public static ArrayList<FlyingObject> explosionNormalEnemyPlane = new ArrayList<>();//定义普通敌机灰烬列表
    public static ArrayList<FlyingObject> explosionIntensiveEnemyPlane = new ArrayList<>();//定义强化飞机灰烬列表
    public static ArrayList<FlyingObject> deadBee = new ArrayList<>();//定义蜜蜂尸体列表

    //定义游戏状态常量
    public final int START = 0;
    public final int RUNING = 1;
    public final int PAUSE = 2;
    public final int GAMEOVER = 3;
    //定义游戏状态存储对象
    private int state = 0;

    static {
        //一次性读取图片
        try {
            normalEnemyPlaneImg = ImageIO.read(Test.class.getResourceAsStream("pic/airplane.png"));
            normalEnemyPlane_ember0 = ImageIO.read(Test.class.getResourceAsStream("pic/airplane_ember0.png"));
            normalEnemyPlane_ember1 = ImageIO.read(Test.class.getResourceAsStream("pic/airplane_ember1.png"));
            normalEnemyPlane_ember2 = ImageIO.read(Test.class.getResourceAsStream("pic/airplane_ember2.png"));
            normalEnemyPlane_ember3 = ImageIO.read(Test.class.getResourceAsStream("pic/airplane_ember3.png"));


            intensiveEnemyPlaneImg = ImageIO.read(Test.class.getResourceAsStream("pic/bigplane.png"));
            intensiveEnemyPlane_ember0 = ImageIO.read(Test.class.getResourceAsStream("pic/bigplane_ember0.png"));
            intensiveEnemyPlane_ember1 = ImageIO.read(Test.class.getResourceAsStream("pic/bigplane_ember1.png"));
            intensiveEnemyPlane_ember2 = ImageIO.read(Test.class.getResourceAsStream("pic/bigplane_ember2.png"));
            intensiveEnemyPlane_ember3 = ImageIO.read(Test.class.getResourceAsStream("pic/bigplane_ember3.png"));

            beeImg = ImageIO.read(Test.class.getResourceAsStream("pic/bee.png"));
            bee_ember0 = ImageIO.read(Test.class.getResourceAsStream("pic/bee_ember0.png"));
            bee_ember1 = ImageIO.read(Test.class.getResourceAsStream("pic/bee_ember1.png"));
            bee_ember2 = ImageIO.read(Test.class.getResourceAsStream("pic/bee_ember2.png"));
            bee_ember3 = ImageIO.read(Test.class.getResourceAsStream("pic/bee_ember3.png"));

            playerPlane0Img = ImageIO.read(Test.class.getResourceAsStream("pic/hero0.png"));
            playerPlane1Img = ImageIO.read(Test.class.getResourceAsStream("pic/hero1.png"));
            playerPlane_ember0 = ImageIO.read(Test.class.getResourceAsStream("pic/hero_ember0.png"));
            playerPlane_ember1 = ImageIO.read(Test.class.getResourceAsStream("pic/hero_ember1.png"));
            playerPlane_ember2 = ImageIO.read(Test.class.getResourceAsStream("pic/hero_ember2.png"));
            playerPlane_ember3 = ImageIO.read(Test.class.getResourceAsStream("pic/hero_ember3.png"));

            backGroundImg = ImageIO.read(Test.class.getResourceAsStream("pic/background.png"));
            bulletImg = ImageIO.read(Test.class.getResourceAsStream("pic/bullet.png"));
            gameOverImg = ImageIO.read(Test.class.getResourceAsStream("pic/gameover.png"));
            pauseImg = ImageIO.read(Test.class.getResourceAsStream("pic/pause.png"));
            startImg = ImageIO.read(Test.class.getResourceAsStream("pic/start.png"));
            //将每种飞行物的爆炸图片放入列表中的一个图片数组元素中
//            bufferedImages.add(new BufferedImage[]{playerPlane_ember0, playerPlane_ember1, playerPlane_ember2, playerPlane_ember3});
//            bufferedImages.add(new BufferedImage[]{normalEnemyPlane_ember0, normalEnemyPlane_ember1, normalEnemyPlane_ember2, normalEnemyPlane_ember3});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void action() {
        //定时且重复的代码
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (state == RUNING) {
                    //创建飞行物对象
                    createFlyingObject();
                    //飞行物移动
                    move();
                    //玩家机图片刷新
                    playerPlane.move();
                    //发射子弹
                    shootBullets();
                    //判断飞行物是否越界
                    outOfBoundsAction();
                    //判断相撞
                    HitJudge();
                    //刷新画板
                    repaint();
                } else if (state == START) {
                    flyingObjectArrayList.clear();
                    bullets.clear();
                    repaint();
                } else if (state == GAMEOVER) {
                    playerPlane.move();
                    repaint();
                }
            }
        }, 100, 10);
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (state == GAMEOVER) {
                    PlayerPlane.imgIndex = 0;//将爆炸起始图片重设为0
                    playerPlane.setImg(playerPlane0Img);
                    playerPlane.setBlood(10);
                    playerPlane.setX(150);
                    playerPlane.setY(300);
                    playerPlane.setScore(0);
                    state = START;
                } else if (state == START) {
                    state = RUNING;
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (state == PAUSE) {
                    state = RUNING;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (state == RUNING) {
                    state = PAUSE;
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                if (state == RUNING) {
                    playerPlane.setX(e.getX() - playerPlane0Img.getWidth() / 2);
                    playerPlane.setY(e.getY() - playerPlane0Img.getHeight() / 2);
                    repaint();
                }
            }
        };
        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
    }

    public static final int WIDTH = 400;
    public static final int HEIGHT = 650;

    public static void main(String[] args) {
        JFrame window = new JFrame("飞机大战");
        window.setSize(WIDTH, HEIGHT);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(3);
        window.setAlwaysOnTop(true);
        Test g = new Test();
        g.action();
        window.add(g);
        window.setVisible(true);//同时调用了paint方法
    }

    //    判断飞行物是否越界
    private void outOfBoundsAction() {
        for (int i = 0; i < flyingObjectArrayList.size(); i++) {
            if (flyingObjectArrayList.get(i).getY() >= Test.HEIGHT) {
                flyingObjectArrayList.remove(i);
                i--;//列表成员删除后 前面的元素自动前移 所以下标要保持不变才不会漏掉后面的一个元素
            }
        }
        //判断子弹是否越界
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i)[0].getY() < -Test.bulletImg.getHeight()) {
                bullets.remove(i);
                i--;
            }
        }
    }

    private int createInterim = 0;

    private void createFlyingObject() {
        //随机产生3种飞行物
        int count = (int) (Math.random() * 20);
        if (++createInterim % 50 == 0) {
            if (count == 0) {
                flyingObjectArrayList.add(new Bee());
            } else if (count == 2 || count == 3) {
                flyingObjectArrayList.add(new IntensiveEnemyPlane());
            } else {
                flyingObjectArrayList.add(new NormalEnemyPlane());
            }
        }

    }

    //调用列表中所有对象的move方法
    private void move() {
        for (int i = 0; i < flyingObjectArrayList.size(); i++) {
            flyingObjectArrayList.get(i).move();
        }
        //调用子弹的move方法
        for (int i = 0; i < bullets.size(); i++) {
            Bullet[] bullet = bullets.get(i);
            if (bullet.length == 1) {
                bullet[0].move();
            } else if (bullet.length == 2) {
                bullet[0].move();
                bullet[1].move();
            }
        }
    }

    //画出飞行物对象表中的所有对象
    private void paintFlyingObject(Graphics g) {
        for (int i = 0; i < flyingObjectArrayList.size(); i++) {
            FlyingObject flyingObject = flyingObjectArrayList.get(i);
            g.drawImage(flyingObject.getImg(), flyingObject.getX(), flyingObject.getY(), this);
        }
    }

    //飞机发射子弹
    public static int shootingMode = 1;
    private int bulletInterim = 0;

    private void shootBullets() {
        if (++bulletInterim % 20 == 0) {
            this.bullets.add(playerPlane.shoot(shootingMode));
        }
    }

    //画出子弹
    private void paintBullets(Graphics g) {
        for (int i = 0; i < bullets.size(); i++) {
            FlyingObject[] bullet = bullets.get(i);
            if (bullet.length == 1) {
                g.drawImage(bullet[0].getImg(), bullet[0].getX(), bullet[0].getY(), this);
            } else if (bullet.length == 2) {
                if (bullet[0].getBlood() == 1) {
                    g.drawImage(bullet[0].getImg(), bullet[0].getX(), bullet[0].getY(), this);
                }
                if (bullet[1].getBlood() == 1) {
                    g.drawImage(bullet[1].getImg(), bullet[1].getX(), bullet[1].getY(), this);
                }
            }
        }
    }

    //判断子弹是否击中飞行物和飞行物与玩家家机是否相撞
    private void HitJudge() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet[] bullet = bullets.get(i);
            if (bullet.length == 1) {
                for (int j = 0; j < flyingObjectArrayList.size(); j++) {
                    FlyingObject flyingObject = flyingObjectArrayList.get(j);
                    if (bullet[0].getX() - flyingObject.getX() > -bullet[0].getWidth() && bullet[0].getX() - flyingObject.getX() < flyingObject.getWidth()
                            && bullet[0].getY() - flyingObject.getY() < flyingObject.getHeight() && bullet[0].getY() - flyingObject.getY() > 0) {
                        bullet[0].setBlood(0);
                        flyingObject.decreaseBlood();
                        playerPlane.increaseScore();
                        if (flyingObject instanceof Bee) {
                            //如果Bee奖励类型为1 玩家飞机加一点生命值
                            if (((Bee) flyingObject).bonus == 1) {
                                playerPlane.increaseBlood();
                            }
                            //如果Bee奖励类型为2 切换射击模式2
                            else if (((Bee) flyingObject).bonus == 2) {
                                shootingMode = 2;
                            }
                        }
                    }
                }
            } else if (bullet.length == 2) {
                for (int j = 0; j < flyingObjectArrayList.size(); j++) {
                    FlyingObject flyingObject = flyingObjectArrayList.get(j);
                    if (bullet[0].getX() - flyingObject.getX() > -bullet[0].getWidth() && bullet[0].getX() - flyingObject.getX() < flyingObject.getWidth()
                            && bullet[0].getY() - flyingObject.getY() < flyingObject.getHeight() && bullet[0].getY() - flyingObject.getY() > 0) {
                        bullet[0].setBlood(0);
                        flyingObject.decreaseBlood();
                        playerPlane.increaseScore();
                        if (flyingObject instanceof Bee) {
                            //如果Bee奖励类型为1 玩家飞机加一点生命值
                            if (((Bee) flyingObject).bonus == 1) {
                                playerPlane.increaseBlood();
                            }
                            //如果Bee奖励类型为2 切换射击模式2
                            else if (((Bee) flyingObject).bonus == 2) {
                                shootingMode = 2;
                            }
                        }
                    }
                    if (bullet[1].getX() - flyingObject.getX() > -bullet[1].getWidth() && bullet[1].getX() - flyingObject.getX() < flyingObject.getWidth()
                            && bullet[1].getY() - flyingObject.getY() < flyingObject.getHeight() && bullet[1].getY() - flyingObject.getY() > 0) {
                        bullet[1].setBlood(0);
                        flyingObject.decreaseBlood();
                        playerPlane.increaseScore();
                        if (flyingObject instanceof Bee) {
                            //如果Bee奖励类型为1 玩家飞机加一点生命值
                            if (((Bee) flyingObject).bonus == 1) {
                                playerPlane.increaseBlood();
                            }
                            //如果Bee奖励类型为2 切换射击模式2
                            else if (((Bee) flyingObject).bonus == 2) {
                                shootingMode = 2;
                            }
                        }
                    }
                }
            }
        }
        //判断飞行物是否与玩家飞机碰撞
        for (int i = 0; i < flyingObjectArrayList.size(); i++) {
            FlyingObject flyingObject = flyingObjectArrayList.get(i);
            if (playerPlane.getX() - flyingObject.getX() < flyingObject.getWidth() && playerPlane.getX() - flyingObject.getX() > -playerPlane.getWidth()
                    && playerPlane.getY() - flyingObject.getY() < flyingObject.getHeight() - 40 && playerPlane.getY() - flyingObject.getY() > -playerPlane.getHeight()) {
                playerPlane.decreaseBlood();
                flyingObject.decreaseBlood();
            }
        }
        //判断玩家飞机血量是否为零
        if (playerPlane.getBlood() <= 0) {
            state = GAMEOVER;
        }
        //判断飞行物血量是否归零
        for (int i = 0; i < flyingObjectArrayList.size(); i++) {
            if (flyingObjectArrayList.get(i).getBlood() <= 0) {
                //获取爆炸飞行物坐标和类型
                if (flyingObjectArrayList.get(i) instanceof NormalEnemyPlane) {
                    explosionNormalEnemyPlane.add(flyingObjectArrayList.get(i));
                }
                if (flyingObjectArrayList.get(i) instanceof IntensiveEnemyPlane) {
                    explosionIntensiveEnemyPlane.add(flyingObjectArrayList.get(i));
                }
                if (flyingObjectArrayList.get(i) instanceof Bee) {
                    deadBee.add(flyingObjectArrayList.get(i));
                }
                flyingObjectArrayList.remove(i);
                i--;//列表成员删除后 前面的元素自动前移 所以下标要保持不变才不会漏掉后面的一个元素
            }
        }
        //判断子弹是否打中
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).length == 1) {
                if (bullets.get(i)[0].getBlood() == 0) {
                    bullets.remove(i);
                    i--;
                }
            } else if (bullets.get(i).length == 2) {
                if (bullets.get(i)[0].getBlood() == 0 && bullets.get(i)[1].getBlood() == 0) {
                    bullets.remove(i);
                    i--;
                }
            }

        }
    }

    //实现飞行物爆炸效果
    private int explosionInterim = 0;//爆炸图片播放间歇

    //    private int mark = 0;
//    private ArrayList<Integer> explosionImgIndex = new ArrayList<>();//播放图片下标
    private void FlyingObjectExplosion(Graphics g) {
        //普通敌机爆炸
        for (int i = 0; i < explosionNormalEnemyPlane.size(); i++) {
            NormalEnemyPlane normalEnemyPlane = (NormalEnemyPlane) (explosionNormalEnemyPlane.get(i));
            g.drawImage(normalEnemyPlane.explosionImg[normalEnemyPlane.explosionImgIndex], normalEnemyPlane.getX(), normalEnemyPlane.getY(), this);
            if(explosionInterim % 20 == 0){
                normalEnemyPlane.explosionImgIndex++;
            }
            if (normalEnemyPlane.explosionImgIndex >= 4) {
                explosionNormalEnemyPlane.remove(i);
                i--;
            }
        }
        //强化敌机爆炸
      for (int i = 0; i < explosionIntensiveEnemyPlane.size(); i++) {
            IntensiveEnemyPlane intensiveEnemyPlane = (IntensiveEnemyPlane) (explosionIntensiveEnemyPlane.get(i));
            g.drawImage(intensiveEnemyPlane.explosionImg[intensiveEnemyPlane.explosionImgIndex], intensiveEnemyPlane.getX(), intensiveEnemyPlane.getY(), this);
            if(explosionInterim % 30 == 0){
                intensiveEnemyPlane.explosionImgIndex++;
            }
            if (intensiveEnemyPlane.explosionImgIndex >= 4) {
                explosionIntensiveEnemyPlane.remove(i);
                i--;
            }
        }
       //蜜蜂消失
        for (int i = 0; i < deadBee.size(); i++) {
            Bee bee = (Bee) (deadBee.get(i));
            g.drawImage(bee.explosionImg[bee.explosionImgIndex], bee.getX(), bee.getY(), this);
            if(explosionInterim % 30 == 0){
                bee.explosionImgIndex++;
            }
            if (bee.explosionImgIndex >= 4) {
                deadBee.remove(i);
                i--;
            }
        }
        explosionInterim++;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //绘画方法自定义增强
        //在画板中加入对象图片  按图片原来大小显示
        g.drawImage(backGroundImg, 0, 0, this);//背景
        g.drawImage(playerPlane.getImg(), playerPlane.getX(), playerPlane.getY(), this);//玩家飞机
        paintFlyingObject(g);//画出飞行物
        paintBullets(g);//画出子弹

        FlyingObjectExplosion(g);//实现爆炸效果
        //1.设置字体
        Font font = new Font("微软雅黑", 1, 20);
        //2.将字体添加进画板
        g.setFont(font);
        //3.设置字体颜色
        Color c = new Color(0, 0, 0, 255);
        g.setColor(c);
        //输入字符
        g.drawString("Score:" + playerPlane.getScore(), 10, 30);
        g.drawString("Life:" + playerPlane.getBlood(), 10, 60);
        if (state == START) {
            g.drawImage(startImg, 0, 0, this);
        } else if (state == PAUSE) {
            g.drawImage(pauseImg, 0, 0, this);
        } else if (state == GAMEOVER) {
            g.drawImage(gameOverImg, 0, 0, this);
        }
    }


}
