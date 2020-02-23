package physics;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import tosco.evanus.GameObject;

public class Collider {
	
	{
		
	}
	
	public static final int STATIC = 0;
	public static final int DYNAMIC_SOLID = 1;
	public static final int DYNAMIC = 2;
	public static final int TRIGGER = 3;
	
	private GameObject gameObject;
	private Rectangle hitbox;
	private float[] raysX, raysY;
	private float lastX, lastY;
	private float hv, vv;
	private int type;
	private boolean hasMoved;
	private boolean grounded;
	private boolean active;
	
	private Color color = Color.GREEN;
	
	public Collider(float x, float y, float width, float height, GameObject gameObject, int type) {
		this.hv = 0;
		this.vv = 0;
		this.gameObject = gameObject;
		this.type = type;
		hitbox = new Rectangle(x, y, width, height);
		this.lastX = hitbox.x;
		this.lastY = hitbox.y;
		this.raysX = new float[4];
		this.raysY = new float[4];
	}
	
	/* NOTE: Do not modify the values of 'other' */
	public void collisionLeft(Collider other) {
		
	}
	
	public void collisionRight(Collider other) {
		
	}
	
	public void collisionTop(Collider other) {
		
	}
	
	public void collisionBottom(Collider other) {
		
	}
	
	public boolean collides(Collider other) {
		return hitbox.overlaps(other.getHitbox());
	}
	
	public boolean betterCollides(Collider other) {
		
		float lower = hitbox.y;
		float upper = hitbox.y + hitbox.height;
		float right = hitbox.x + hitbox.width;
		float left = hitbox.x;
		
		float otherLower = other.getY();
		float otherUpper = other.getY() + other.getHeight();
		float otherRight = other.getX() + other.getHeight();
		float otherLeft = other.getX();
		
		//this/other
		if (lower < otherUpper && left < otherRight && right > otherLeft && upper > otherLower) {
			//translateY(otherUpper - lower);
			return true;
		}
		return false;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public float getX() {
		return hitbox.x;
	}
	
	public float getY() {
		return hitbox.y;
	}
	
	public float getLastX() {
		return lastX;
	}
	
	public float getLastY() {
		return lastY;
	}
	
	public float getWidth() {
		return hitbox.width;
	}
	
	public float getHeight() {
		return hitbox.height;
	}
	
	public int getType() {
		return type;
	}
	
	public float getVelocityX() {
		return hv;
	}
	
	public float getVelocityY() {
		return vv;
	}

	public boolean isGrounded() {
		return grounded;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void draw(ShapeRenderer renderer) {
		renderer.setColor(color);
		renderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
	}
	
	public void move() {
		if(vv < Physics.TERMINAL_VELOCITY) {
			vv = Physics.TERMINAL_VELOCITY;
		}
		lastX = hitbox.x;
		lastY = hitbox.y;
		hitbox.x += hv;
		hitbox.y += vv;
	}

	public void setX(float x) {
		this.hitbox.x = x;
	}
	
	public void setY(float y) {
		this.hitbox.y = y;
	}
	
	public void translateX(float dx) {
		this.hitbox.x += dx;
	}
	
	public void translateY(float dy) {
		this.hitbox.y += dy;
	}
	
	public void setVelocityX(float hv) {
		this.hv = hv;
	}
	
	public void setVelocityY(float vv) {
		this.vv = vv;
	}

	// Up is negative
	public void accelerateY(float a) {
		vv += a;
	}
	
	public void accelerateX(float a) {
		hv += a;
	}
}
