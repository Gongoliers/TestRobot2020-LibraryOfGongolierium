/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.Arrays;
import java.util.List;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * The parts on the robot
 */
public class Parts {

    public static SpeedController leftMotor, rightMotor;
    public static Gyro gyro;
    public static Encoder leftEncoder, rightEncoder;

    public static void initialize(){
        leftMotor = new SpeedControllerGroup(
            new VictorSP(RobotMap.DRIVE_LEFT_1)//,
            // new WPI_TalonSRX(RobotMap.DRIVE_LEFT_2)
        );
        rightMotor = new SpeedControllerGroup(
            new VictorSP(RobotMap.DRIVE_RIGHT_1)//,
            // new WPI_TalonSRX(RobotMap.DRIVE_RIGHT_2)
        );

        gyro = new AnalogGyro(RobotMap.GYRO);
        gyro.calibrate();
        leftEncoder = new Encoder(RobotMap.ENCODER_B, RobotMap.ENCODER_A);
        rightEncoder = new Encoder(RobotMap.ENCODER2_B, RobotMap.ENCODER2_A);

        // Feet
        leftEncoder.setDistancePerPulse(1/1024.0 * 12/50.0 * 24/50.0 * 6 * Math.PI / 12.0);
        rightEncoder.setDistancePerPulse(1/1024.0 * 12/50.0 * 24/50.0 * 6 * Math.PI / 12.0);
    }

}
