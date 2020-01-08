/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.thegongoliers.paths.FollowPathCommand;
import com.thegongoliers.paths.SimplePath;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.OperateDrivetrain;

/**
 * Add your docs here.
 */
public class Controls {

    public static XboxController driveStick;

    public static double getForwardAxis(){
        if (driveStick == null) return 0;
        return driveStick.getY(Hand.kLeft);
    }

    public static double getRotationAxis(){
        if (driveStick == null) return 0;
        return driveStick.getX(Hand.kLeft);
    }

    public static void initialize(){
        driveStick = new XboxController(0);


        SimplePath path = new SimplePath();
        path.addRotation(90);

        // SmartDashboard.putData("Rotate 90", new FollowPathCommand(Robot.drivetrain, Robot.drivetrain.getModularDrivetrain(), path));
    
        Trigger joystickMoved = new Trigger(){
        
            @Override
            public boolean get() {
                return driveStick.getY(Hand.kLeft) > 0.1 || driveStick.getX(Hand.kLeft) > 0.1;
            }
        };
    
        joystickMoved.whenActive(new OperateDrivetrain());

    }

}