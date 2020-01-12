/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.List;

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

    var stability = new StabilityModule(Parts.gyro, 0.02, 0);
    stability.setTurnThreshold(0.05);

    var pathFollow = new PathFollowerModule(Parts.gyro, List.of(Parts.leftEncoder, Parts.rightEncoder), 0.1, 0.02);
    pathFollow.setForwardTolerance(0.5); // 1/4 feet
    pathFollow.setTurnTolerance(1); // 1 degree

    // You can use the voltage control module in place of the speed constraint module if you know the max voltage you should drive at
    var voltageControl = new VoltageControlModule(12);

    var tractionControl = new TractionControlModule(Parts.leftEncoder, Parts.rightEncoder, 0.1, 0.1);

    // Experiment with the order of these for the best driving
    modularDrivetrain.setModules(
      stability,
      new PowerEfficiencyModule(0.1),
      pathFollow,
      tractionControl,
      voltageControl
    );
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new OperateDrivetrain());
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Heading", Parts.gyro.getAngle());
    SmartDashboard.putNumber("Distance", (Parts.leftEncoder.getDistance() + Parts.rightEncoder.getDistance()) / 2);
    SmartDashboard.putNumber("Speed", (Parts.leftEncoder.getRate() + Parts.rightEncoder.getRate()) / 2);
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
