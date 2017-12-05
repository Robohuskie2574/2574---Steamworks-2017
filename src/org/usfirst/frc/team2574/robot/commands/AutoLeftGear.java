package org.usfirst.frc.team2574.robot.commands;

import org.usfirst.frc.team2574.robot.Robot;
import org.usfirst.frc.team2574.robot.RobotMap;
import org.usfirst.frc.team2574.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLeftGear extends Command {

	
	private boolean finished = false;
	
    public AutoLeftGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Drive.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Drive.safety(false);
    	Timer.delay(.2);
    	Drive.driveStraight(-.39, 1.25);
    	Drive.cartesian(0, 0, 0);
    	Timer.delay(.2);
    	while (Drive.getGyroAngle() < 44) {
    		Drive.cartesian(0, 0, .38);
    		Timer.delay(.05);
    	}
    	Drive.cartesian(0, 0, 0);
    	//Timer.delay(.2);
    	Timer.delay(.2);
    	Drive.driveStraight(-.32, 1.2);
    	Drive.cartesian(0, 0, 0);
    	Timer.delay(.2);
    	Drive.driveStraight(-.15, 2);
    	
    	finished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
