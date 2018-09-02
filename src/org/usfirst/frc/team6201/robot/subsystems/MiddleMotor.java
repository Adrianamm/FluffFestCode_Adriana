package org.usfirst.frc.team6201.robot.subsystems;

import org.usfirst.frc.team6201.robot.Robot;
import org.usfirst.frc.team6201.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class MiddleMotor extends Subsystem {

	private Spark m1 = new Spark(RobotMap.MIDDLE);
	/**
	 * Sets motors to full power
	 */
	public void climb() {

		if(!Robot.buttonDisable) {
			m1.set(0.5);
		}
	}

	/**
	 * Unspools at half power
	 */

	public void fall() {
		if(!Robot.buttonDisable) { 
			m1.set(-0.5);
		}
	}

	/**
	 *  Stops motors, generally when the robot has reached the touchpad
	 */

	public void stop() {
			if(!Robot.buttonDisable) {
				m1.set(0);
	}
	
}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


