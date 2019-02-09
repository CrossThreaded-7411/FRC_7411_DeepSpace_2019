package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveManualWithJoystick;

public class VacuumSubsystem extends Subsystem
{
    public Compressor vacuum = new Compressor();
    public Solenoid solenoid = new Solenoid(RobotMap.solenoid);

    public void initDefaultCommand()
    {
        setDefaultCommand(new DriveManualWithJoystick());
        init();
    }

    public void init()
    
    {
        vacuum.setClosedLoopControl(true);
    }

    public void runVacuum()
    {
    }
}