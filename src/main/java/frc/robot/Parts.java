/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Arrays;
import java.util.List;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * The parts on the robot
 */
public class Parts {

    public static SpeedController leftMotor, rightMotor;
    public static Gyro gyro;
    public static List<Encoder> driveEncoders;

    public static void initialize(){
        leftMotor = new SpeedControllerGroup(
            new WPI_TalonSRX(RobotMap.DRIVE_LEFT_1),
            new WPI_TalonSRX(RobotMap.DRIVE_LEFT_2)
        );
        rightMotor = new SpeedControllerGroup(
            new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_1),
            new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_2)
        );

        // TODO: verify
        leftMotor.setInverted(true);
        rightMotor.setInverted(true);

        gyro = new AnalogGyro(RobotMap.GYRO);
        gyro.calibrate();
        driveEncoders = Arrays.asList(
            new Encoder(RobotMap.ENCODER_A, RobotMap.ENCODER_B)
        );
        driveEncoders.get(0).setDistancePerPulse(1 / 8443.0);
    }

}
