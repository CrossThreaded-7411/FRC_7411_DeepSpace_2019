package frc.robot.subsystems;

import edu.wpi.first.hal.sim.mockdata.PCMDataJNI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.RobotMap;
import frc.robot.Robot;
import frc.robot.commands.DriveManualWithJoystick;

public class PneumaticSubsystem extends Subsystem
{
    public Solenoid pressureRelease = new Solenoid(3, 0);
    public VictorSP vacuum = new VictorSP(RobotMap.vacuumMotorPort);

    public void initDefaultCommand()
    {
        setDefaultCommand(new DriveManualWithJoystick());
    }

    public void runPneumatics()
    {
        boolean bumperValue = Robot.oi.driverStick2.getRawButton(5);
        pressureRelease.set(bumperValue);

        if (bumperValue == true)
        {
            vacuum.set(0);
        }
        else
        {
            vacuum.set(1);
        }
    }

    public void stopPneumatics()
    {
        pressureRelease.set(false);
        vacuum.set(0);
    }
}   