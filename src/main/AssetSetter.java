/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Monster.GreenSlime;
import object.ObjectKey;
import object.ObjectPortal;


public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new ObjectKey(gp);
        gp.obj[0].worldX = 20 * gp.tileSize;
        gp.obj[0].worldY = 20 * gp.tileSize;
        
        gp.obj[1] = new ObjectPortal(gp);
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 23 * gp.tileSize;
    }
    public void setMonster(){
        gp.monster[0] = new GreenSlime(gp);
        gp.monster[0].worldX = 20 * gp.tileSize;
        gp.monster[0].worldY = 26 * gp.tileSize;

        gp.monster[1] = new GreenSlime(gp);
        gp.monster[1].worldX = 23 * gp.tileSize;
        gp.monster[1].worldY = 29 * gp.tileSize;
    }
}
