package ylsin5.scm.tul09;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by JessSin on 7/11/16.
 */
class Enermy extends Character{
    Player[] TargetList;
    int AttackCoolDown;
    int AttackCounter;
    Enermy(int x,int y){
        Hp=150;
        ATP=30;
        Speed=30;
        CurrentState=State.Idle;
        Position=new Rect(x,y,x+200,y+200);
        BeingTouch=false;
        AttackCoolDown=30;
        AttackCounter=1;
        paint=new Paint();
        paint.setColor(Color.GRAY);
        TargetList=new Player[3];

    }
    Player SelectTarget(Player[] target){
        int Largest=800;
        int A=0;
        for(int i=0;i<=2;i++){
            if(Distance(target[i])<=Largest){
                Largest=Distance(target[i]);
                A=i;
            }
        }
        return target[A];
    }
    void updateState(Player[] target){

        switch (CurrentState){
            case Idle:
                paint.setColor(Color.GRAY);
                AttackCounter+=1;
                if(AttackCounter>=AttackCoolDown){
                    CurrentState=State.ReadyForAttack;
                    AttackCounter=0;
                }
                //check cooldown
                if(Hp<=0){this.CurrentState=State.Die;}
                //Die if HP==0

                break;

            case ReadyForAttack:
                //select target
                Attack(SelectTarget(target));
                this.CurrentState=State.Idle;
                break;

            case Die:
                Hp=0;
                paint.setColor(Color.YELLOW);
                break;
        }
    }
}
