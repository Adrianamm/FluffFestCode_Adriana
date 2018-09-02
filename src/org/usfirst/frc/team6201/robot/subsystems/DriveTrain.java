package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.RobotMap;
import org.usfirst.frc.team6201.robot.commands.ArcadeDriveCmd;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The interface between the robot code and the actuators and sensors involved
 * in moving the robot. Right now this is just the motors and gyro, but this
 * will probably grow to include encoders.
 * 
 * TODO: add getter and setter methods for the other method of the CANTalon
 * classes.
 * 
 * @author Baxter Ellard
 * @author David Matthews
 */
public class DriveTrain extends Subsystem {

	// CANTalon refers to a motor controller, so it means a motor for all
	// intents & purposes.
	private Spark left1;
	private Spark left2;
	private Spark right1;
	private Spark right2;

	//TODO make this an enum
	public static int fowardOrReverse = -1;

	// Sensors
	private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	/**
	 * Constructor, sets up motors, prevents brownouts and minimizes pedestrian
	 * casualties.
	 */
	public DriveTrain() {
		left1 = new Spark(RobotMap.LEFT_DRIVE1);
    	left2 = new Spark(RobotMap.LEFT_DRIVE2);
    	right1 = new Spark(RobotMap.RIGHT_DRIVE1);
    	right2 = new Spark(RobotMap.RIGHT_DRIVE2);
    	
    	/*
    	left1.configOpenloopRamp(1/3, 0);
    	left2.configOpenloopRamp(1/3, 0);
    	right1.configOpenloopRamp(1/3, 0);
    	right2.configOpenloopRamp(1/3, 0);
    	
    	left1.setNeutralMode(NeutralMode.Brake);
    	left2.setNeutralMode(NeutralMode.Brake);
    	right1.setNeutralMode(NeutralMode.Brake);
    	right2.setNeutralMode(NeutralMode.Brake);
    	
left1.set
*/
		left1.setInverted(true);
		left2.setInverted(true);


	}

	/**
	 * ArcadeDriveCmd will always run when other commands are not busy. This
	 * will allow operator control when the robot is not driving itself around.
	 */
	public void initDefaultCommand() {

		setDefaultCommand(new ArcadeDriveCmd());
		
	}

	/**
	 * Updates the motors with what speed to drive at. TODO: what is the robot
	 * "Front", and what value is that? 1 or -1? TODO: Use the custom joystick
	 * that Owen made
	 * 
	 * @param leftPower
	 *            Double speed of left motors. Range -1 to 1
	 * @param rightPower
	 *            Double speed of right motors. Range -1 to 1
	 */
	
	public void driveLR(double leftPower, double rightPower) {
/*
		if(Robot.buttonDisable == false) {
			
			if (fowardOrReverse == 1) {
				left1.set(leftPower);
				left2.set(leftPower);
				right1.set(rightPower);
				right2.set(rightPower);	
				DataCollator.motorSpeedLeft.setVal(leftPower);
				DataCollator.motorSpeedRight.setVal(rightPower);
			}
			
			else {
				left1.set(-leftPower);
				left2.set(-leftPower);
				right1.set(-rightPower);
				right2.set(-rightPower);
				DataCollator.motorSpeedLeft.setVal(-leftPower);
				DataCollator.motorSpeedRight.setVal(-rightPower);
			}
			
		}
*/		
		
		if (fowardOrReverse == -1) {
			left1.set(leftPower);
			left2.set(leftPower);
			right1.set(rightPower);
			right2.set(rightPower);	
			//DataCollator.motorSpeedLeft.setVal(leftPower);
			//DataCollator.motorSpeedRight.setVal(rightPower);
		}
		
		else {
			left1.set(leftPower);
			left2.set(leftPower);
			right1.set(rightPower);
			right2.set(rightPower);
			//DataCollator.motorSpeedLeft.setVal(-leftPower);
			//DataCollator.motorSpeedRight.setVal(-rightPower);
		}

	}

	/**
	 * Sets the motors to be off.
	 */
	public void stop() {

		this.driveLR(0, 0);

	}

	/**
	 * Calibrates gyro (takes 5 seconds while robot does nothing) Do this when
	 * robot first turns on.
	 */
	public void calibrateGyro() {

		gyro.calibrate();

	}

	/**
	 * Sets the drivetrain gyro back to 0 degrees
	 */
	public void resetGyro() {

		gyro.reset();

	}

	/**
	 * @return the current rate of turning in degrees per second
	 */
	public double getGyroRate() {

		return gyro.getRate();

	}

	/**
	 * 
	 * @return gets an approximation of the gyro angle since reset was last
	 *         called from an accumulation using the FPGA. Will accumulate error
	 *         over time.
	 * 
	 */
	public double getGyroAngle() {

		return gyro.getAngle();

	}
/*	
	public void turboBoostEnable() {
		
		right1.setVoltageRampRate(55);
		right2.setVoltageRampRate(55);
		left1.setVoltageRampRate(55);
		left2.setVoltageRampRate(55);
		
		
	}
	
	public void turboBoostDisable() {
		
		right1.setVoltageRampRate(36);
		right2.setVoltageRampRate(36);
		left1.setVoltageRampRate(36);
		left2.setVoltageRampRate(36);
		
		
	}
*/
}
