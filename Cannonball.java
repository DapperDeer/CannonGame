// Cannonball.java
// Represents the Cannonball that the Cannon fires
package com.deitel.cannongame;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Cannonball extends GameElement {
   private float velocityX;
   private boolean onScreen;
   private String filepath = "F:/School/CMPS 481/CannonGame/app/src/main/res/drawable/cannonball.gif";

   // constructor
   public Cannonball(CannonView view, int color, int soundId, int x,
                     int y, int radius, float velocityX, float velocityY) {
      super(view, color, soundId, -1, x, y,
              2 * radius, 2 * radius, velocityY);
      this.velocityX = velocityX;
      onScreen = true;
   }

   // get Cannonball's radius
   private int getRadius() {
      return (shape.right - shape.left) / 2;
   }

   // test whether Cannonball collides with the given GameElement
   public boolean collidesWith(GameElement element) {
      return (Rect.intersects(shape, element.shape) && velocityX > 0);
   }

   // returns true if this Cannonball is on the screen
   public boolean isOnScreen() {
      return onScreen;
   }

   // reverses the Cannonball's horizontal velocity
   public void reverseVelocityX() {
      velocityX *= -1;
   }

   // updates the Cannonball's position
   @Override
   public void update(double interval, double offset) {
      super.update(interval, 0); // updates Cannonball's vertical position

      // update horizontal position
      shape.offset((int) (velocityX * interval), 0);

      // if Cannonball goes off the screen
      if (shape.top < 0 || shape.left < 0 ||
              shape.bottom > view.getScreenHeight() ||
              shape.right > view.getScreenWidth())
         onScreen = false; // set it to be removed
   }

   // draws the Cannonball on the given canvas
   @Override
   public void draw(Canvas canvas) {
      canvas.drawCircle(shape.left + getRadius(),
              shape.top + getRadius(), getRadius(), paint);
   }
}
