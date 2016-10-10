package ru.sbt.bit.ood.solid.homework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SalaryHtmlReportNotifier {

    private Connection connection;

    public SalaryHtmlReportNotifier(Connection databaseConnection) {
        this.connection = databaseConnection;
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients) {
        try {
            SalaryReporter reporter = new SalaryReporter(connection);
            ResultSet results = reporter.report(departmentId, dateFrom, dateTo);

            HtmlMaker HtmlMaker = new HtmlMaker(results);
            String resultingHtml = HtmlMaker.make();

            MailSender sender = new MailSender("mail.google.com", "Monthly department salary report");
            sender.send(recipients, resultingHtml);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
