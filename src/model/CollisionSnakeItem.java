package model;

import java.util.ArrayList;
import java.util.Random;
import agent.*;
import item.*;
import utils.*;

public class CollisionSnakeItem {
	
	ArrayList<Snake> listSnakes;
	ArrayList<Item> items;
	InputMap map;
	SnakeGame snakeGame;
	
	public CollisionSnakeItem(SnakeGame snakeGame) {
		this.listSnakes = snakeGame.listSnakes;
		this.items = snakeGame.Items;
		this.map = snakeGame.map;
		this.snakeGame = snakeGame;
	}
	
	/*	Check si dans la liste des serpents il ont une position en commun avec la pomme */
	public boolean checkCollision() {
		if(!listSnakes.isEmpty()) {
			for(Snake s: listSnakes) {
				for(Position p: s.getPosition()) {
					for(Item item: items) {
						if(p.getX() == item.getX() && p.getY() == item.getY()) {
							whenCollide(s, item);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public void whenCollide(Snake s, Item item) {
		
		ItemType itemType = item.getFeatureItem().getItemType();
		
	    if(itemType == ItemType.APPLE) {
			if(!s.getSick()) {
				s.growSnake();
				item.setRandomPosition(map, listSnakes);
				spawnRandomItem();
			}
		}
		else if(itemType == ItemType.SICK_BALL) {
			if(!s.getInvincible()) s.setSick(true);
			items.remove(item);
			item.setPosition(100, 100);
		}
		else if(itemType == ItemType.INVINCIBILITY_BALL) {
			s.setInvincible(true);
			items.remove(item);
			item.setPosition(100, 100);
		}
		else if(itemType == ItemType.BOX) {
			Random random = new Random();
			int r = random.nextInt(2);
			if(r == 0) s.setInvincible(true);
			else if(r == 1) s.setSick(true);
			items.remove(item);
			item.setPosition(100, 100);
		}
	}
	
	public void spawnRandomItem() {
		int probaSpawn = 30; //(30%)
		
		Random random = new Random();
		int r = random.nextInt(100);
		if(r <= probaSpawn) {
			int randomItem = random.nextInt(3);
			if(randomItem == 0) {
				FeaturesItem fItem = new FeaturesItem(0, 0, ItemType.SICK_BALL);
				this.map.addItem(fItem);
				SickBall s = new SickBall(fItem);
				s.setRandomPosition(map, listSnakes);
				items.add(s);
			}
			else if(randomItem == 1) {
				FeaturesItem fItem = new FeaturesItem(0, 0, ItemType.INVINCIBILITY_BALL);
				this.map.addItem(fItem);
				InvincibilityBall s = new InvincibilityBall(fItem);
				s.setRandomPosition(map, listSnakes);
				items.add(s);
			}
			else if(randomItem == 2) {
				FeaturesItem fItem = new FeaturesItem(0, 0, ItemType.BOX);
				this.map.addItem(fItem);
				Box s = new Box(fItem);
				s.setRandomPosition(map, listSnakes);
				items.add(s);
			}
			
		}
	}
}
