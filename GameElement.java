// GameElement.java
// Represents a rectangle-bounded game element
package com.deitel.cannongame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class GameElement {
   protected CannonView view; // the view that contains this GameElement
   protected Paint paint = new Paint(); // Paint to draw this GameElement
   protected Rect shape; // the GameElement's rectangular bounds
   private float velocityY; // the vertical velocity of this GameElement
   private int soundId; // the sound associated with this GameElement
    private int newSoundId;

   // public constructor
   public GameElement(CannonView view, int color, int soundId, int newSoundID, int x,
                      int y, int width, int length, float velocityY) {
      this.view = view;
      paint.setColor(color);
      shape = new Rect(x, y, x + width, y + length); // set bounds
      this.soundId = soundId;
       this.newSoundId = newSoundID;
      this.velocityY = velocityY;
   }

   // update GameElement position and check for wall collisions
   public void update(double interval, double offset) {
      // update vertical position
      shape.offset(0, (int) ((velocityY  * interval) + offset));

      // if this GameElement collides with the wall, reverse direction
      if (shape.top < 0 && velocityY < 0 ||
              shape.bottom > view.getScreenHeight() && velocityY > 0) {
          velocityY *= -1; // reverse this GameElement's velocity
          view.playSound(3);
      }
   }

   // draws this GameElement on the given Canvas
   public void draw(Canvas canvas) {
      canvas.drawRect(shape, paint);
   }

   // plays the sound that corresponds to this type of GameElement
   public void playSound() {
      view.playSound(soundId);
   }
}

