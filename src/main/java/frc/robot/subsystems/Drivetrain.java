/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.thegongoliers.output.drivetrain.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Parts;
import frc.robot.commands.OperateDrivetrain;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  
  private ModularDrivetrain modularDrivetrain;

  public Drivetrain(){
    DifferentialDrive differentialDrive = new DifferentialDrive(Parts.leftMotor, Parts.rightMotor);
    modularDrivetrain = ModularDrivetrain.from(differentialDrive);

    StabilityModule stability = new StabilityModule(Parts.gyro, 0.02, 0);
    stability.setValue(StabilityModule.VALUE_THRESHOLD, 0.05);


    modularDrivetrain.setModules(
      new PrecisionModule(0.5),
      stability,
      new PowerEfficiencyModule(0.5),
      new SpeedConstraintModule(1, false)
    );
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new OperateDrivetrain());
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Heading", Parts.gyro.getAngle());
    SmartDashboard.putNumber("Distance", Parts.driveEncoders.get(0).getDistance());
    SmartDashboard.putNumber("Speed", Parts.driveEncoders.get(0).getRate());
  }

  public void drive(double forward, double turn){
    modularDrivetrain.arcade(forward, turn);
  }

  public void stop(){
    modularDrivetrain.stop();
  }

  public ModularDrivetrain getModularDrivetrain(){
    return modularDrivetrain;
  }
}
