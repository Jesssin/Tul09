package ylsin5.scm.tul09;

import android.app.usage.UsageEvents;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.lang.annotation.Target;

/**
 * Created by JessSin on 2/11/16.
 */
//enum State{
//    Idle,
//    ReadyForAttack,
//    Die,
//    hiden,
//}
//class Character{
//    Character target;
//    int Hp;
//    int ATP;
//    int Speed;//probability of miss
//    State CurrentState; //ready for click or not
//    Rect Position;
//    boolean BeingTouch;
//    Paint paint;
//
//    public Character(){}
//    void Attack(){
//        paint.setColor(Color.RED);
//        //target.Hp-=this.ATP;
//        // do damage to Target
//    }
//    void CheckTouch(float x,float y){
//            BeingTouch=x>Position.left&&x<Position.right
//                &&y>Position.top&&y<Position.bottom;}
//    }

//class Player extends Character{
//    int CoolDown;
//    int Counter; //for Cool down
//    Player(int x,int y){
//        Hp=100;
//        ATP=20;
//        Speed=20;
//        CurrentState=State.Idle;
//        Position=new Rect(x,y,x+200,y+200);
//        BeingTouch=false;
//        CoolDown=100;
//        Counter=0;
//        paint=new Paint();
//        paint.setColor(Color.GREEN);
//
//    }
//    void updateState(){
//
//        switch (CurrentState){
//            case Idle:
//                paint.setColor(Color.GREEN);
//                Counter+=1;
//                if(Counter>=CoolDown){
//                    CurrentState=State.ReadyForAttack;
//                    Counter=0;
//                }
//                //select&select target
//                //check cooldown
//                //Die if HP==0
//                break;
//
//            case ReadyForAttack:
//                paint.setColor(Color.CYAN);
//                if(BeingTouch){
//                    Attack();
//                    this.CurrentState=State.Idle;
//                }
//
//                break;
//            case Die:
//                break;
//        }
//    }
//}

//class Enermy extends Character{
//    Player[] TargetList;
//    int AttackCoolDown;
//    int AttackCounter;
//    Enermy(int x,int y){
//        Hp=100;
//        ATP=20;
//        Speed=20;
//        CurrentState=State.Idle;
//        Position=new Rect(x,y,x+200,y+200);
//        BeingTouch=false;
//        AttackCoolDown=100;
//        AttackCounter=0;
//        paint=new Paint();
//        paint.setColor(Color.GRAY);
//        TargetList=new Player[3];
//
//    }
//    void updateState(){
//        switch (CurrentState){
//            case Idle:
//                paint.setColor(Color.GRAY);
//                AttackCounter+=1;
//                if(AttackCounter>=AttackCoolDown){
//                    CurrentState=State.ReadyForAttack;
//                    AttackCounter=0;
//                }
//                //select&select target
//                //check cooldown
//                //Die if HP==0
//
//                break;
//
//            case ReadyForAttack:
//                Attack();
//                this.CurrentState=State.Idle;
//                break;
//
//            case Die:
//                break;
//        }
//    }
//}


public class MyView extends View {
    boolean gameLoop=true;
    public MyView(Context ctx){
        super(ctx);
        init();

    }
    Enermy E1=new Enermy(400,100);
    Player[] P1=new Player[3];

    float Touchx,Touchy;


    public void init(){
        for(int i=0;i<=2;i++){
            P1[i]=new Player(i*300+100,500);
        }
        //E1.TargetList=P1;

    }
    public void update(){
        E1.updateState(P1);
        for(int i=0;i<=2;i++) {
            P1[i].updateState(E1,(int) Touchx,(int) Touchy);
        }
        Log.d("ADebugTag", "P1.Hp=" + Float.toString(P1[0].Hp)+Float.toString(P1[1].Hp)+Float.toString(P1[2].Hp));
        Log.d("ADebugTag", "E1.Hp=" + Float.toString(E1.Hp));

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Touchx=event.getX();
        Touchy=event.getY();

        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN:

                break;

            case MotionEvent.ACTION_MOVE:
                for(int i=0;i<=2;i++){
                    if(P1[i].BeingTouch){
                        P1[i].Position.set((int)Touchx-100, (int)Touchy-100, (int)Touchx + 100, (int)Touchy + 100);
                    }
                }

                break;

            case MotionEvent.ACTION_UP:
               // P1[i].BeingTouch=false;
                //ABC
                Touchx=-1;
                Touchy=-1;


                break;
        }
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(E1.Position, E1.paint);
        for(int i=0;i<=2;i++){
            canvas.drawRect(P1[i].Position, P1[i].paint);
        }

    }

}
