import javax.swing.*;

public class GameMain {

    //1.显示窗体 swing技术 JFrame类
    public static JFrame gameFrame = new JFrame();
    public static void main(String[] args) {
        //大小 默认为0
        gameFrame.setSize(370, 400);
        //标题
        gameFrame.setTitle("2048游戏");
        //窗口居中
        gameFrame.setLocationRelativeTo(null);

        //2.窗体上的内容 创建一个面板JPanel（容器，画纸）
        GamePanel gamePanel = new GamePanel();
        gameFrame.add(gamePanel);

        //显示 放在最后
        gameFrame.setVisible(true);
    }
}
