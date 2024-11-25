package lk.ijse.theculinaryacademyhibernateproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Program {
    @Id
    private String programId;
    private String programName;
    private String Duration;
    private double Fee;

    public Program() {
    }

    public Program(String programId, String programName, String duration, double fee) {
        this.programId = programId;
        this.programName = programName;
        Duration = duration;
        Fee = fee;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public double getFee() {
        return Fee;
    }

    public void setFee(double fee) {
        Fee = fee;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programId='" + programId + '\'' +
                ", programName='" + programName + '\'' +
                ", Duration='" + Duration + '\'' +
                ", Fee=" + Fee +
                '}';
    }
}
