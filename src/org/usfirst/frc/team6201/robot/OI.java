package org.usfirst.frc.team6201.robot;


import org.usfirst.frc.team6201.robot.commands.MiddleMotorDownCmd;
import org.usfirst.frc.team6201.robot.commands.MiddleMotorStopCmd;
import org.usfirst.frc.team6201.robot.commands.MiddleMotorUpCmd;
import org.usfirst.frc.team6201.robot.commands.SolenoidStuff;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This provides the framework to connect the DriverStation to the Robot both for getting values from the joystick(s), 
 * and for starting commands when buttons are pressed.
 * 
 * 
 * @author Baxter Ellard
 * @author David Matthews
 * Adriana 
 */
public class OI {

	/**
	 * Create an object out of our logitech arcade joystick.
	 * This allows us to get the  current position of the joystick, and the state of all the buttons.
	 * Initialized with the USB devices plugged into the robot
	 */
	private Joystick logitech = new Joystick(RobotMap.LOGITECH);
	
	private Button b1 = new JoystickButton(logitech, 1);
	private Button b2 = new JoystickButton(logitech, 1);
	//pneumatics 
	private Button bc4 = new JoystickButton(logitech,4);
	private Button bc3 = new JoystickButton(logitech,3);
	private Button bc5 = new JoystickButton(logitech,5);
	//middle motor
	private Button bc11 = new JoystickButton(logitech,11);
	private Button bc12 = new JoystickButton(logitech,12);
	
	/**
	 * @return  a double corresponding to how much the joystick's handle is rotated.
	 * This has a range of -1 to 1. All the way to the right is +1.
	 */
	public double getRotationAxisOfArcade() {
		
		return logitech.getRawAxis(RobotMap.LOGITECH_ROTATE_AXIS);
	}
	
	/**
	 * @return a double corresponding to the position of the joystick in the side to side direction (X axis).
	 * Range of -1 to 1. All the way to the right is +1.
	 */
	public double getXAxisOfArcade() {
		
		return logitech.getRawAxis(RobotMap.LOGITECH_X_AXIS);
	}

	/**
	 * @return a double corresponding to the position of the joystick in the Y axis (front and back).
	 * range of -1 to 1, with all the way forward being 1
	 */
	public double getYAxisOfArcade() {
		
		return logitech.getRawAxis(RobotMap.LOGITECH_Y_AXIS);
		
	}
	
	
	/**
	 * @return a double corresponding to the slider on the joystick roughly under the wrist of someone if they are holding it.
	 * range of -1 or 1, where -1 is pointing the slider up and 1 is pointing it down
	 */
	public double getSliderAxisOfArcade() {
		
		return logitech.getRawAxis(RobotMap.LOGITECH_SLIDER_AXIS);
		
	}
	
	/**
	 * @return True if button 1 is pressed, false otherwise.
	 */
	public boolean getButton1() {	
		return b1.get();		
	}
	public boolean getButtonbc2() {
		return b2.get();
	}
	
	public boolean getButtonbc3() {
		return bc3.get();
	}
	
	public boolean getButtonbc4() {
		return bc4.get();
	}

	public boolean getButtonbc5() {
		return bc5.get();
	}
	public boolean getButtonbc11() {
		return bc11.get();
	}
	public boolean getButtonbc12() {
		return bc12.get();
	}
	
	
	public OI() {	
		/*
		if(!Robot.buttonDisable) {
					
			
			
		}	
		*/
		
		//using bc# intean of b# when using the control board as an input.
		Button bc12 = new JoystickButton(logitech,12);
		bc12.whileHeld(new MiddleMotorUpCmd());
		
		Button bc11 = new JoystickButton(logitech,11);
		bc11.whileHeld(new MiddleMotorDownCmd());
		
		Button bc2 = new JoystickButton(logitech,2);
		bc2.whileHeld(new MiddleMotorStopCmd());
		
		//controls solenoid 1
		Button bc4 = new JoystickButton(logitech, 4);
		bc4.whenPressed(new SolenoidStuff(3, Robot.pn.solenoid0));

		//controls solenoid 2
		Button bc3 = new JoystickButton(logitech, 3);
		bc3.whenPressed(new SolenoidStuff(1, Robot.pn.solenoid1));
		
		//controls solenoid 2
		Button bc5 = new JoystickButton(logitech, 5);
		bc5.whenPressed(new SolenoidStuff(2, Robot.pn.solenoid2));
	
	}
	
}


