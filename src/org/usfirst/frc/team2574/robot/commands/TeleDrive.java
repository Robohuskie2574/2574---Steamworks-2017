package org.usfirst.frc.team2574.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2574.robot.OI;
import org.usfirst.frc.team2574.robot.Robot;
import org.usfirst.frc.team2574.robot.subsystems.*;

import com.ctre.CANTalon;


/**
 *
 */

public class TeleDrive extends Command {

	private static double deadzone = 0.2;
	
    public TeleDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Drive.zeroEncoders();
    	//Drive.speedControl();
    	if (!Drive.safety()) {
    		Drive.safety(true);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	double x = OI.getDriveAxes()[0];
    	double y = OI.getDriveAxes()[1];
    	double rotation = OI.getDriveAxes()[2];
    	
    	if (Math.abs(OI.getDriveAxes()[0]) < deadzone) {
    		x = 0;
    	}
    	if (Math.abs(OI.getDriveAxes()[1]) < deadzone) {
    		y = 0;
    	}
    	
    	if (Math.abs(OI.getDriveAxes()[2]) < deadzone*.5) {
    		rotation = 0;
    	}
    	
    	Drive.cartesian(x, y, rotation*.75);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
