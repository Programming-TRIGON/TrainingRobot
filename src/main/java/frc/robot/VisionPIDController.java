package frc.robot;

import edu.wpi.first.wpilibj.Controller;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

public class VisionPIDController extends PIDController implements Controller {
    Notifier m_controlLoop = new Notifier(this::calculate);

    public VisionPIDController(double Kp, double Ki, double Kd, PIDSource source, PIDOutput output) {
        this(Kp, Ki, Kd, source, output, kDefaultPeriod);

    }

    public VisionPIDController(double Kp, double Ki, double Kd, PIDSource source, PIDOutput output, double period) {
        this(Kp, Ki, Kd, 0.0, source, output, period);
    }

    public VisionPIDController(double Kp, double Ki, double Kd, double Kf, PIDSource source, PIDOutput output,
            double period) {
        super(Kp, Ki, Kd, Kf, source, output);
    }

    public VisionPIDController(double Kp, double Ki, double Kd, double Kf, PIDSource source, PIDOutput output) {
        this(Kp, Ki, Kd, Kf, source, output, kDefaultPeriod);
    }

    @Override
    protected void calculate() {
        double output = 0;
        this.m_thisMutex.lock();
        try {
            output = m_pidInput.pidGet();
        } finally {
            this.m_thisMutex.unlock();
        }
        if (output != 9999)
            super.calculate();
        else {
            this.m_thisMutex.lock();
            try {
                m_pidOutput.pidWrite(9999);
            } finally {
                this.m_thisMutex.unlock();
            }
        }
    }
}