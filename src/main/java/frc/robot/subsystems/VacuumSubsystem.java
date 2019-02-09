package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveManualWithJoystick;
// import jdk.vm.ci.code.ValueUtil;

public class VacuumSubsystem extends Subsystem
{
    public VictorSP vacuum = new VictorSP(RobotMap.vacuumMotor);

    public void initDefaultCommand()
    {
        setDefaultCommand(new DriveManualWithJoystick());
    }

    //runVacuum()
    {
        if (Robot.oi.driverStick2.getRawButton(5) == true)
        {
            vacuum.set(1);
        }
        else if (Robot.oi.driverStick2.getRawButton(5) == false)
        {
            vacuum.set(0);

        }
    }
}