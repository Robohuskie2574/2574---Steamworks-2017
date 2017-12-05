package org.usfirst.frc.team2574.robot.subsystems;

import org.usfirst.frc.team2574.robot.RobotMap;
import org.usfirst.frc.team2574.robot.commands.StopBalls;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Balls extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private static Spark ballDump = new Spark(RobotMap.BallsPWM);

	public Balls() { }
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new StopBalls());
    }
    
    public static void set(double speed){
    	ballDump.setSpeed(speed);
    }
    
}

