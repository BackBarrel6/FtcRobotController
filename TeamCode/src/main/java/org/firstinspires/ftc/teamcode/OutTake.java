package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class OutTake {

    public HardwareMapping mapping;

    public OutTake(HardwareMapping mapping)
    {
        this.mapping = mapping;
    }
    enum Pos {
        down,midlow,midhigh,up
    }
    Pos position = Pos.down;
    public void oninit(){
        //mapping.motorLift=
        //TODO: add hardware mapping for lift(ma doare capul)
    }
    public void update(Gamepad gamepad){

    }
}
