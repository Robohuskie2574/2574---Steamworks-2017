package org.usfirst.frc.team2574.robot.subsystems;

import org.usfirst.frc.team2574.robot.RobotMap;
import org.usfirst.frc.team2574.robot.commands.AutoLeftGear;
import org.usfirst.frc.team2574.robot.commands.TeleDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private static AnalogGyro gyro = new AnalogGyro(RobotMap.gyro);

    private static CANTalon leftF = new CANTalon(RobotMap.leftFrontId);
    private static CANTalon leftR = new CANTalon(RobotMap.leftRearId);
    private static CANTalon rightF = new CANTalon(RobotMap.rightFrontId);
    private static CANTalon rightR = new CANTalon(RobotMap.rightRearId);
    
    private static RobotDrive robotDrive = new RobotDrive(leftF, leftR, rightF, rightR);
    
    private static final double gyroPGain = 0.05;
    
    private static double kP = .03;
    private static double kI = 0;
    private static double kD = 0;
    
    public Drive() {
    	leftF.setInverted(true);
    	leftR.setInverted(true);
    	
    	leftF.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	leftR.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	rightF.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	rightR.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	
    	leftF.configEncoderCodesPerRev(360);
    	leftR.configEncoderCodesPerRev(360);
    	rightF.configEncoderCodesPerRev(360);
    	rightR.configEncoderCodesPerRev(360); 
    	
    	
    	//leftF.reverseSensor(true);
    	leftR.reverseSensor(true);
    	//rightF.reverseSensor(true);
    	//rightR.reverseSensor(true);
    	
    	leftF.setPID(kP, kI, kD);
    	leftR.setPID(kP, kI, kD);
    	rightF.setPID(kP, kI, kD);
    	rightR.setPID(kP, kI, kD);
    	
    	zeroEncoders();
    	//speedControl();
    	vbusControl();
    }
    
    public static void cartesian(double x, double y, double rotation) {
    	robotDrive.mecanumDrive_Cartesian(x, y , rotation, 0);
    }
    
    public static void driveStraight(double y, double time) {
    	gyro.reset();
    	double start = Timer.getFPGATimestamp();
    	while (Timer.getFPGATimestamp() < ( start + time) ) {
    		cartesian(0,y,-getGyroAngle() * gyroPGain);
    		Timer.delay(.025);
    	}
    	cartesian(0,0,0);
    }
    
    public static void driveStraightLen(double rotations) {
    	zeroEncoders();
    	gyro.reset();
    	while(getLeftFPos() < rotations - 1) {
    		cartesian(0,-0.4,-getGyroAngle() * gyroPGain);
    		Timer.delay(.025);
    	}
    	while(getLeftFPos() < rotations ) {
    		cartesian(0,-0.1,-getGyroAngle() * gyroPGain);
    		Timer.delay(.025);
    	}
    	cartesian(0,0,0);
    }
    
    public static void speedControl() {
    	leftF.changeControlMode(CANTalon.TalonControlMode.Speed);
		leftR.changeControlMode(CANTalon.TalonControlMode.Speed);
		rightF.changeControlMode(CANTalon.TalonControlMode.Speed);
		rightR.changeControlMode(CANTalon.TalonControlMode.Speed);
    }
    
    public static void zeroEncoders() {
    	leftF.setEncPosition(0);
    	leftR.setEncPosition(0);
    	rightF.setEncPosition(0);
    	rightR.setEncPosition(0);
    }
    
    public static void vbusControl() {
    	leftF.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		leftR.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightF.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightR.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }
    
    public static void safety(boolean enabled) {
    	robotDrive.setSafetyEnabled(enabled);
    }
    
    public static boolean safety() {
    	return robotDrive.isSafetyEnabled();
    }
    
    public static AnalogGyro getGyro() {
    	return gyro;
    }
    
    public static double getGyroAngle() {
    	return gyro.getAngle();
    }
    
    public static void resetGyro() {
    	gyro.calibrate();
    	gyro.reset();
    }
    
    public static double[] getEncoderValues() {
    	return new double[]{(double) rightF.getPosition(), (double) rightR.getPosition(), (double) leftF.getPosition(), (double) leftR.getPosition()};
    }
    
    public static double getLeftFPos() {
    	return leftF.getPosition();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.	
    	//setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new TeleDrive());
 
     }

}

