package org.usfirst.frc.team6201.robot.commands;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.RobotMap;
import org.usfirst.frc.team6201.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This command interfaces between the Operator
 * Interface object (Robot.oi) and the DriveTrain object (Robot.dt) to provide
 * smooth intuitive joystick control with fine control at slow speeds, while
 * still being able to reach full speed. Recently added support for a third axis
 * to control the sensitivity of the joystick.
 *
 * @author David Matthews
 * @author Adriana Massie
 * @author Baxter Ellard
 *
 */

public class ArcadeDriveCmd extends Command {
	
	/**
	 * Absolute value of the maximum voltage applied to motor (max speed robot will drive at)
	 * FOR COMP: maxSpeed = 1
	 * FOR OUTREACH/EVENTS: maxSpeed = 0.5
	 */
	private final double MAX_SPEED = 1;

	/**
	 * Calibrated turning amount by throwing the X axis of the joystick through
	 * a tan function
	 */
	private double tanTurn;

	/**
	 * Calibrated forward motion by throwing the Y axis of the joystick through
	 * a tan function
	 */
	private double tanPower;

	/**
	 * Calculates how much the robot can turn based on what the processedPower
	 * is, how much room we have to play with the motors and how much turning is
	 * desired.
	 */
	private double processedTurn;

	/**
	 * reserves 5% for turning at all times.
	 */
	private double processedPower;

	/**
	 * Creates the sensitivity curve for the Y axis
	 */
	private final double TANDOMAIN_Y = 1.3; // used for sensitivity of joystick

	/**
	 * Creates the sensitivity curve for the X axis
	 */
	private final double TANDOMAIN_X = 1.3; // used for sensitivity of joystick
											// // changed after reading
											// competition

	/**
	 * 
	 * @param rawVal
	 *            Value to be processed by the tangent function
	 * @param domain
	 *            Domain of the tangent function. Will effect shape of this
	 *            mapping function curve.
	 * @return A double corresponding mapped from the input rawVal via a tangent
	 *         curve and the domain. provides high motion control at slow
	 *         speeds, and full robot speed.
	 */
	private double scaledValTan(double rawVal, double domain) {
		return Math.tan(rawVal * domain) / (Math.tan(domain));
	}

	/**
	 * Requires the Robot.dt Drivetrain subsystem to run. Used for resource
	 * allocation in WPILibj
	 */
	public ArcadeDriveCmd() {
		requires(Robot.dt);
	}

	/**
	 * Updates the DataLogger's state value, and resets the gyro to prepare for
	 * the start of the command
	 */
	protected void initialize() {

	
	}

	/**
	 * Gets the joystick position from the Driver Station, and calculates what
	 * to set the power of the motors by using a tangent curve as a mapping
	 * function, and using the gyro to correct for some natural turning of the
	 * robot.
	 * 
	 * TODO: The auto gyro correction does not work very well, look into why it
	 * is not working and try to fix it or remove it.
	 */
	protected void execute() {


		// get joystick positions
		double joystickX = Robot.oi.getXAxisOfArcade();
		double joystickY = Robot.oi.getYAxisOfArcade();
		double joystickSlider = 0.5 * (1 + (-1 * Robot.oi.getSliderAxisOfArcade()));

	
		// calculate actual ability of robot by reserving 5% of motor speed for
		// turning at all times
		if(Robot.oi.getButton1()) {
			// use mapping function and the joystick slider as a gain
			// to get a desired turn amount and a desired forward motion speed
			tanTurn = scaledValTan(joystickX , TANDOMAIN_X);
			tanPower = scaledValTan(joystickY , TANDOMAIN_Y);

			processedPower = tanPower * SmartDashboard.getNumber("TurboSpeed", 0.95);
//			Robot.dt.turboBoostEnable();
		} else {
			// use mapping function and the joystick slider as a gain
			// to get a desired turn amount and a desired forward motion speed
			tanTurn = scaledValTan(joystickX * joystickSlider * MAX_SPEED, TANDOMAIN_X);
			tanPower = scaledValTan(joystickY * joystickSlider * MAX_SPEED, TANDOMAIN_Y);

			processedPower = tanPower * 0.90;
//			Robot.dt.turboBoostDisable();
		}

		// Combine the desired turn rate with how much the motors are not using.
		processedTurn = (-1 - Math.abs(processedPower)) * tanTurn;

		// Calculates the speed of the wheels to achieve the desired turning
		// rate
		// Checks which side of the robot is considered the "front", and inverts
		// the Robot.dt.driveLR() parameters if needed
		
		Robot.dt.driveLR(-(processedPower - processedTurn), -(processedPower + processedTurn));
	}

	// This command should always run if another command is not running.
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	// stops the motors.
	protected void end() {
		Robot.dt.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		this.end();
	}
}
