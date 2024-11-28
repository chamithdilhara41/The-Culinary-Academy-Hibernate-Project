package lk.ijse.theculinaryacademyhibernateproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Registration {
    @Id
    private String RegistrationID;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    private String registrationDate;
    private double paymentAmount;

    public Registration(String registrationID, Student student, Program program, String registrationDate, double paymentAmount) {
        RegistrationID = registrationID;
        this.student = student;
        this.program = program;
        this.registrationDate = registrationDate;
        this.paymentAmount = paymentAmount;
    }

    public Registration(String registrationId, Student studentId, Program programId, String registrationDate, String paymentAmount) {
        RegistrationID = registrationId;
        this.student = studentId;
        this.program = programId;
        this.registrationDate = registrationDate;
        this.paymentAmount = Double.parseDouble(paymentAmount);
    }

    public Registration() {

    }


    public String getRegistrationID() {
        return RegistrationID;
    }

    public void setRegistrationID(String registrationID) {
        RegistrationID = registrationID;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
