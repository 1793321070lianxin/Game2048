import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//面板 展示内容
public class GamePanel extends JPanel {
    Random rnd = new Random();
    //创建4*4的卡片  二维数组
    Card[][] allCards = new Card[4][4];

    //1.初始化游戏界面信息
    public GamePanel(){

        //创建卡片的类
        //1.创建卡片对象，存入allCards数组
        for (int i = 0; i < 4; i++) {   //行
            for (int j = 0; j < 4; j++) {   //列
                Card card = new Card(i, j);
                allCards[i][j] = card;
            }
        }
        newCard();
        //添加一个监听键盘窗体程序 键盘按键事件
        GameMain.gameFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                switch (code){
                    case KeyEvent.VK_UP://上
                    case KeyEvent.VK_W://上
                        moveCardUp();
                        break;
                    case KeyEvent.VK_DOWN://下
                    case KeyEvent.VK_S://下
                        moveCardDown();
                        break;
                    case KeyEvent.VK_LEFT://左
                    case KeyEvent.VK_A://左
                        moveCardLeft();
                        break;
                    case KeyEvent.VK_RIGHT://右
                    case KeyEvent.VK_D://右
                        moveCardRight();
                        break;
                }
                gameEndCheck();
                newCard();
                repaint();
            }
        });
    }

    //键盘点击——上
    public void moveCardUp(){
        for (int i = 1; i < 4; i++) {   //第0行卡片不用移动
            for (int j = 0; j < 4; j++) {
                Card card = allCards[i][j];
                if (card.num != 0){     //不为0卡片才需要移动
                    //控制每个卡片向上移动
                    card.moveUp(allCards);
                }
            }
        }
    }

    //键盘点击——下
    public void moveCardDown(){
        for (int i = 0; i < 3; i++) {   //第4行卡片不用移动
            for (int j = 0; j < 4; j++) {
                Card card = allCards[i][j];
                if (card.num != 0){     //不为0卡片才需要移动
                    //控制每个卡片向上移动
                    card.moveDown(allCards);
                }
            }
        }
    }

    //键盘点击——左
    public void moveCardLeft(){
        for (int i = 0; i < 4; i++) {   //第4行卡片不用移动
            for (int j = 1; j < 4; j++) {
                Card card = allCards[i][j];
                if (card.num != 0){     //不为0卡片才需要移动
                    //控制每个卡片向上移动
                    card.moveLeft(allCards);
                }
            }
        }
    }

    //键盘点击——右
    public void moveCardRight(){
        for (int i = 0; i < 4; i++) {   //第4行卡片不用移动
            for (int j = 0; j < 3; j++) {
                Card card = allCards[i][j];
                if (card.num != 0){     //不为0卡片才需要移动
                    //控制每个卡片向上移动
                    card.moveRight(allCards);
                }
            }
        }
    }

    //游戏结束判断
    public void gameEndCheck(){
        boolean EndGame = true;
        boolean Victory = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Card card = allCards[i][j];
                if (card.num == 1028){
                    Victory = true;
                    break;
                }
                if (card.num == 0){
                    EndGame = false;
                }
            }
        }

        if (EndGame){
            System.out.println("游戏结束");
            if (Victory){
                JOptionPane.showMessageDialog(null, "您胜利了");
            }else{
                JOptionPane.showMessageDialog(null, "您失败了");
            }
        }
    }

    //随机产生卡片以及数字
    public void newCard(){
        //数字2 0.8 数字4 0.2
        int num;
        int id = rnd.nextInt(5);
        num = id == 4 ? 4 : 2;
        //产生随机卡片位置 递归检查位置是否有数字
        Card card = rndCard();
        card.num = num;
    }

    //获取随机卡片的位置【注意卡片是否已经被占用】
    public Card rndCard(){
        int i = rnd.nextInt(4);
        int j = rnd.nextInt(4);
        Card card = allCards[i][j];
        if (card.num == 0){     //判断卡片是否为空
            return card;
        }else{
            return rndCard();
        }
    }

    //绘图功能  画笔工具 Graphics
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //画矩形
        for (int i = 0; i < 4; i++) {   //行
            for (int j = 0; j < 4; j++) {   //列
                Card card = allCards[i][j];
                g.setColor(getCardColor(card.num));   //根据卡片数字决定
                g.fillRect(card.x, card.y, 80, 80);

                if (card.num != 0) {
                    //设置卡片的数字
                    g.setColor(new Color(125, 78, 51));
                    g.setFont(new Font("楷体", Font.BOLD, 36));

                    //根据数字长度设置坐标
                    int OffSet = 30;
                    if (card.num < 10) {
                        OffSet = 30;
                    } else if (card.num < 100) {
                        OffSet = 20;
                    } else if (card.num < 1000) {
                        OffSet = 8;
                    } else {
                        OffSet = -2;
                    }

                    g.drawString(card.num + "", card.x + OffSet, card.y + 50);
                }
            }
        }
    }

    public Color getCardColor(int num){
        Color color;
        switch (num){
            case 0:
                color = new Color(92, 151, 117);
                break;
            case 2:
                color = new Color(242, 230, 56);
                break;
            case 4:
                color = new Color(55, 236, 200);
                break;
            case 8:
                color = new Color(174, 213, 130);
                break;
            case 16:
                color = new Color(142, 201, 75);
                break;
            case 32:
                color = new Color(111, 148, 48);
                break;
            case 64:
                color = new Color(76, 174, 124);
                break;
            case 128:
                color = new Color(60, 180, 144);
                break;
            case 256:
                color = new Color(45, 130, 120);
                break;
            case 512:
                color = new Color(9, 97, 26);
                break;
            case 1024:
                color = new Color(242, 177, 121);
                break;
            case 2048:
                color = new Color(223, 185, 220);
                break;
            default:
                color = null;
                break;
        }
        return color;
    }
}
