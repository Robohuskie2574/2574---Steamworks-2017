package org.usfirst.frc.team2574.robot.subsystems;

import org.usfirst.frc.team2574.robot.commands.SendAll;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartDashboardSubsys extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	
	public static void putData(String key, Sendable data) {
		SmartDashboard.putData(key, data);
	}
	
	public static void putDouble(String key, double value) {
		SmartDashboard.putNumber(key, value);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new SendAll());
    }
}

