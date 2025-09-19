package Monster;

import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GreenSlime extends Entity {
    public GreenSlime(GamePanel gp){
         super(gp) ;
        direction = "down";
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        name = "Green Slime";
        actualSpeed = 1 ;
        maxHealth = 4 ;
        health = maxHealth ;
        solidArea.width = 42 ;
        solidArea.height = 30 ;
        solidArea.x = 3 ;
        solidArea.y = 18 ;
        solidAreaDefaultX = solidArea.x ;
        solidAreaDefaultY = solidArea.y ;
        width = gp.tileSize;
        height = gp.tileSize;
        animationON = true ;
        getImage();
        setAction();
    }
    public void getImage(){
        up1 = setup("/monster/greenslime_down_1" , width , height) ;
        up2 = setup("/monster/greenslime_down_2" , width ,  height) ;
        down1 = setup("/monster/greenslime_down_1" , width, height) ;
        down2 = setup("/monster/greenslime_down_2" , width, height) ;
        right1 = setup("/monster/greenslime_down_1", width , height) ;
        right2 = setup("/monster/greenslime_down_2" , width, height) ;
        left1 = setup("/monster/greenslime_down_1" , width, height) ;
        left2 = setup("/monster/greenslime_down_2" , width , height) ;
    }
    public void update() {
        // no time to finish monster moving
        spriteCounter++;
        if (spriteCounter > 12) {      // ~5 FPS animation nếu game 60 FPS
            spriteNum = (spriteNum == 1 ? 2 : 1);
            spriteCounter = 0;
        }
    }
    public void setAction(){
        actionLockCounter++ ;
        if(actionLockCounter >= 120){
            Random rand = new Random() ;
            int i = rand.nextInt(100) ;
            if(i <= 25){
                direction = "up" ;
            } else if (i <= 50) {
                direction = "down" ;
            } else if (i <= 75) {
                direction = "right" ;
            }else if (i <= 100) {
                direction = "left" ;
            }
            actionLockCounter = 0;
        }
    }
}
