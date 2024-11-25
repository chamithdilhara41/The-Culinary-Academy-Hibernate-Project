module lk.ijse.theculinaryacademyhibernateproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires java.mail;
    requires jbcrypt;
    requires com.jfoenix;
    requires com.google.protobuf;
    requires static lombok;
    requires javafx.base;

    opens lk.ijse.theculinaryacademyhibernateproject.controller to javafx.fxml;
    opens lk.ijse.theculinaryacademyhibernateproject.dao.Custom to org.hibernate.orm.core;
    opens lk.ijse.theculinaryacademyhibernateproject.entity to org.hibernate.orm.core;// Open package for ORM core
    opens lk.ijse.theculinaryacademyhibernateproject.tdm to javafx.base;

    exports lk.ijse.theculinaryacademyhibernateproject;
    exports lk.ijse.theculinaryacademyhibernateproject.dao.Custom;
    opens lk.ijse.theculinaryacademyhibernateproject.util to javafx.fxml; // Export package containing UserDAO
}
