package ylsin5.scm.tul09;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by JessSin on 7/11/16.
 */
class Player extends Character{
    int CoolDown;
    int Counter; //for Cool down

    Player(int x,int y){
        Hp=100;
        ATP=5;
        Speed=10;
        CurrentState=State.Idle;
        Position=new Rect(x,y,x+200,y+200);
        BeingTouch=false;
        CoolDown=20;
        Counter=x/10;
        paint=new Paint();
        paint.setColor(Color.GREEN);

    }
    void updateState(Character target,int Touchx,int Touchy){
        CheckTouch(Touchx, Touchy);

        switch (CurrentState){
            case Idle:
                paint.setColor(Color.GREEN);
                Counter+=1;
                if(Counter>=CoolDown){
                    CurrentState=State.ReadyForAttack;
                    Counter=0;
                }
                //check cooldown
                if(Hp<=0){this.CurrentState=State.Die;}
                //Die if HP==0
                break;

            case ReadyForAttack:
                paint.setColor(Color.CYAN);
                if(BeingTouch){
                    Attack(target);
                    this.CurrentState=State.Idle;
                }
                if(Hp<=0){this.CurrentState=State.Die;}

                break;
            case Die:
                Hp=0;
                paint.setColor(Color.YELLOW);
                break;
        }
    }
}
