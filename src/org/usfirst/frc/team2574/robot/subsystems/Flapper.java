package org.usfirst.frc.team2574.robot.subsystems;


import org.usfirst.frc.team2574.robot.RobotMap;
import org.usfirst.frc.team2574.robot.commands.CloseFlap;
import org.usfirst.frc.team2574.robot.commands.OpenFlap;
import org.usfirst.frc.team2574.robot.commands.StopFlap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Flapper extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private static Spark flapperLeft = new Spark(RobotMap.flapperLeftPWM);
	private static Spark flapperRight = new Spark(RobotMap.flapperRightPWM);
	
	public Flapper() { }
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new StopFlap());
    	
    }
    
    public static void set(double speed){
    	flapperLeft.setSpeed(speed);
    	flapperRight.setSpeed(-speed);
    
    }
    
}

