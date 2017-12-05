package org.usfirst.frc.team2574.robot.commands;

import org.usfirst.frc.team2574.robot.Robot;
import org.usfirst.frc.team2574.robot.subsystems.Drive;
import org.usfirst.frc.team2574.robot.subsystems.SmartDashboardSubsys;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SendEncoder extends Command {

    public SendEncoder() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.smartDash);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double[] values = Drive.getEncoderValues();
    	SmartDashboardSubsys.putDouble("RightF Enc", values[0]);
    	SmartDashboardSubsys.putDouble("RightR Enc", values[1]);
    	SmartDashboardSubsys.putDouble("LeftF Enc", values[2]);
    	SmartDashboardSubsys.putDouble("leftF Pos", Drive.getLeftFPos());
    	SmartDashboardSubsys.putDouble("LeftR Enc", values[3]);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
