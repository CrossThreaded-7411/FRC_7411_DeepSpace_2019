package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Robot;
import frc.robot.commands.DriveManualWithJoystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;

public class LiftSubsystem extends Subsystem
{
    public WPI_TalonSRX liftMotor1 = new WPI_TalonSRX(30);
    public WPI_TalonSRX liftMotor2 = new WPI_TalonSRX(31);

    SpeedControllerGroup liftMotors = new SpeedControllerGroup(liftMotor1, liftMotor2);

    public Encoder liftEncoder = new Encoder(0,1);
    public int intCount = liftEncoder.get();


    public void initDefaultCommand()
    {
        setDefaultCommand(new DriveManualWithJoystick());
        initEncoder();
    }

    public void initEncoder()
    {
        liftEncoder.reset();
    }

    //need to get encoder values and in auto set encoder values.
    public void driveLift()
    {
        if (Math.abs(Robot.oi.driverStick2.getRawAxis(5)) <= 0.1)
        {
            liftMotors.set(0);
        }
        else 
        {
            liftMotors.set(Robot.oi.driverStick2.getRawAxis(5) * 0.5);
        }

        SmartDashboard.putNumber("Encoder Counts ", intCount);
    }

    public void stopLift()
    {
        liftMotors.set(0);
    }
}