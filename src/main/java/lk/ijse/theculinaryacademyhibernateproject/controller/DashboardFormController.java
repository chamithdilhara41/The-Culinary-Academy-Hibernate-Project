package lk.ijse.theculinaryacademyhibernateproject.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import lk.ijse.theculinaryacademyhibernateproject.config.FactoryConfiguration;
import org.hibernate.Session;

public class DashboardFormController {

    @FXML
    private Label lblPaymentCount;

    @FXML
    private Label lblProgramCount;

    @FXML
    private Label lblStudentCount;

    @FXML
    private LineChart<?, ?> lineChart;

    public void initialize() {
        loadDashboardCounts();
    }

    private void loadDashboardCounts() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            // Fetch payment count
            Long paymentCount = session.createQuery("SELECT COUNT(p) FROM Payment p", Long.class).uniqueResult();

            // Fetch program count
            Long programCount = session.createQuery("SELECT COUNT(p) FROM Program p", Long.class).uniqueResult();

            // Fetch student count
            Long studentCount = session.createQuery("SELECT COUNT(s) FROM Student s", Long.class).uniqueResult();

            // Update labels with the counts
            lblPaymentCount.setText(String.valueOf(paymentCount));
            lblProgramCount.setText(String.valueOf(programCount));
            lblStudentCount.setText(String.valueOf(studentCount));

        } catch (Exception e) {
            e.printStackTrace();

            // Display error in labels if an exception occurs
            lblPaymentCount.setText("Error");
            lblProgramCount.setText("Error");
            lblStudentCount.setText("Error");
        }
    }
}
