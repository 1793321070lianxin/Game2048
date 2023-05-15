public class Card {
    //存储每张卡片的信息
    int x = 0, y = 0;   //窗体上坐标
    int num = 0;    //卡片上显示的数字
    int i, j;   //数组中的下标

    public Card(int i, int j) {
        this.i = i;
        this.j = j;

        //计算
        this.x = 5 + 80 * j + 5 * j;
        this.y = 5 + 80 * i + 5 * i;
    }

    //卡片向上移动
    public void moveUp(Card[][] allCards){
        if (this.i == 0){
            return;
        }

        //上一个卡片 i - 1   j
        Card preCard = allCards[i - 1][j];
        //当前卡片的数字赋值给上一个数字，当前卡片变成0
        if (preCard.num == 0){
            preCard.num = this.num;
            this.num = 0;
            preCard.moveUp(allCards);
        }else if (preCard.num == this.num){
            preCard.num = this.num + preCard.num;
            this.num = 0;
            preCard.moveUp(allCards);
        }
    }

    //卡片向下移动
    public void moveDown(Card[][] allCards){
        if (this.i == 3){
            return;
        }

        //下一个卡片 i + 1   j
        Card laterCard = allCards[i + 1][j];
        //当前卡片的数字赋值给下一个数字，当前卡片变成0
        if (laterCard.num == 0){
            laterCard.num = this.num;
            this.num = 0;
            laterCard.moveDown(allCards);
        }else if (laterCard.num == this.num){
            laterCard.num = this.num + laterCard.num;
            this.num = 0;
            laterCard.moveDown(allCards);
        }
    }

    public void moveLeft(Card[][] allCards){
        if (this.j == 0){
            return;
        }

        //上一个卡片 i j - 1
        Card preCard = allCards[i][j - 1];
        //当前卡片的数字赋值给下一个数字，当前卡片变成0
        if (preCard.num == 0){
            preCard.num = this.num;
            this.num = 0;
            preCard.moveLeft(allCards);
        }else if (preCard.num == this.num){
            preCard.num = this.num + preCard.num;
            this.num = 0;
            preCard.moveLeft(allCards);
        }
    }

    public void moveRight(Card[][] allCards){
        if (this.j == 3){
            return;
        }

        //下一个卡片 i j + 1
        Card laterCard = allCards[i][j + 1];
        //当前卡片的数字赋值给下一个数字，当前卡片变成0
        if (laterCard.num == 0){
            laterCard.num = this.num;
            this.num = 0;
            laterCard.moveRight(allCards);
        }else if (laterCard.num == this.num){
            laterCard.num = this.num + laterCard.num;
            this.num = 0;
            laterCard.moveRight(allCards);
        }
    }
}
