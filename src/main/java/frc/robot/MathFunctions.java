/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class MathFunctions {
    
    public double LeftSet = 0.0;
    public double RightSet = 0.0;

/*
Here's a solution for arcade drive that doesn't require complicated if/else chains, doesn't reduce the power 
when moving full forward or rotating in place, and allows for smooth curves and transitions from moving to spinning.
The idea is simple. Assume the (x,y) joystick values are cartesian coordinates on a square plane. Now imagine a 
smaller square plane rotated 45º inside it.

The joystick coordinates give you a point in the larger square, and the same point superimposed in the smaller 
square gives you the motor values. You just need to convert coordinates from one square to the other, limiting 
the new (x,y) values to the sides of the smaller square.

There are many ways to do the conversion. My favorite method is:

Convert the initial (x,y) coordinates to polar coordinates.
Rotate them by 45 degrees.
Convert the polar coordinates back to cartesian.
Rescale the new coordinates to -1.0/+1.0.
Clamp the new values to -1.0/+1.0.
This assumes the initial (x,y) coordinates are in the -1.0/+1.0 range. The side of the inner square will always be 
equal to l * sqrt(2)/2, so step 4 is just about multiplying the values by sqrt(2).

  */
    public void mercyArcadeDrive(double joystickX, double joystickY)
    {
        // convert to polar
        double radius = Math.hypot(joystickX, joystickY);
        double theta = Math.atan2(joystickX, joystickY);
        // rotate 45 degrees
        theta = theta + Math.PI /4; 
        // back to cartesian
        LeftSet = radius * Math.cos(theta);
        RightSet = radius * Math.sin(theta);
        // Rescale to new coordinate system - multiply by square root of 2
        LeftSet = LeftSet * 1.414;
        RightSet = RightSet * 1.414;
        // limit to 1 -1
        LeftSet = limit (LeftSet);
        RightSet = limit (RightSet); 
        
    }

    public double limit(double value) {
        return Math.max(-1, Math.min(value, 1));
    }

    public double handleDeadband(double val, double deadband){
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }
}
