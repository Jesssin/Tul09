package ylsin5.scm.tul09;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

/**
 * Created by JessSin on 7/11/16.
 */
enum State{
    Idle,
    ReadyForAttack,
    Die,
}
class Character{
    int Hp;
    int ATP;
    int Speed;//probability of miss
    State CurrentState; //ready for click or not
    Rect Position;
    boolean BeingTouch;
    Paint paint;

    public Character(){}
    int Distance(Character target){
        return Math.abs(target.Position.top-this.Position.top);
    }
    void Attack(Character target){
        Random rand = new Random();
        int M=rand.nextInt(100)+1;
        //Check miss
        if(target.Speed+Distance(target)/20<=M){
            paint.setColor(Color.RED);
            if(target.CurrentState==State.Idle){target.Hp-=this.ATP;}
            if(target.CurrentState==State.ReadyForAttack){target.Hp-=this.ATP/2;}
            // do damage to Target
        }else {
            paint.setColor(Color.BLUE);
            Log.d("ADebugTag", this+"Attack_Miss");
        }

    }
    void CheckTouch(float x,float y){
        BeingTouch=x>Position.left&&x<Position.right
                &&y>Position.top&&y<Position.bottom;}
}
