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
        setAction();
        spriteCounter++;
        if (spriteCounter > 12) {      // ~5 FPS animation nếu game 60 FPS
            spriteNum = (spriteNum == 1 ? 2 : 1);
            spriteCounter = 0;
        }
        int deltaMoveX = 0;
        int deltaMoveY = 0;
        double length = Math.sqrt(deltaMoveX * deltaMoveX + deltaMoveY * deltaMoveY);
        if (length != 0) {
            deltaMoveX = (int) Math.round(deltaMoveX / length * actualSpeed);
            deltaMoveY = (int) Math.round(deltaMoveY / length * actualSpeed);
        }
        switch (direction) {
            case "up":    deltaMoveY = -actualSpeed; break;
            case "down":  deltaMoveY =  actualSpeed; break;
            case "left":  deltaMoveX = -actualSpeed; break;
            case "right": deltaMoveX =  actualSpeed; break;
        }
        /*
        there is a problem , If the position touches the boundary, it is impossible
        to press 2 keys to move diagonally and the result is to move in a
        normal direction. therefore, valriable collisionOn is for static object and tiles.
        if we use that val for both coordinates x and y , we can not move in the most
        possible direction. I use 2 val called collisionXOn collisionYOn for the movement
        of player so i can calculate the posible future move.

        -> we have to predict the path that the player can move.
        */
        // collide with X
        collisionOn = false;
        collisionXOn = false;


        int nextX = worldX + deltaMoveX; // posible move X
        int nextY = worldY; // posible move Y
        gp.cChecker.checkTile(this, nextX, nextY);
        if (!collisionXOn && !collisionOn) {
            worldX = nextX;
            //System.out.print(worldX / 16 + " ");
        }


        //collide with Y
        collisionOn = false;
        collisionYOn = false;

        nextX = worldX;
        nextY = worldY + deltaMoveY;
        gp.cChecker.checkTile(this, nextX, nextY);
        if (!collisionYOn && !collisionOn) {
            worldY = nextY;
            //System.out.println(worldY / 16);
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
