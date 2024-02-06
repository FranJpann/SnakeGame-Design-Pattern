package model;

public class Collisions {
	
	public CollisionSnakeItem collisionSnakeItem;
	public CollisionSnakeHimself collisionSnakeHimself;
	
	public Collisions(SnakeGame snakeGame) {
		this.collisionSnakeItem = new CollisionSnakeItem(snakeGame);
		this.collisionSnakeHimself = new CollisionSnakeHimself(snakeGame);
	}
	
	public boolean checkCollisionSnakeItem() {
		return this.collisionSnakeItem.checkCollision();
	}
	
	public boolean checkCollisionSnakeHimself() {
		return this.collisionSnakeHimself.checkCollision();
	}
}
