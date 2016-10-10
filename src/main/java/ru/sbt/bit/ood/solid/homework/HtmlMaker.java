package ru.sbt.bit.ood.solid.homework;


import java.sql.ResultSet;
import java.sql.SQLException;

public class HtmlMaker {
    private final ResultSet results;
    private final StringBuilder resultingHtml = new StringBuilder();

    public HtmlMaker(ResultSet results) {
        this.results = results;
        resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
    }

    public String make() {
        double totals = 0;
        try {
            while (results.next()) {
                resultingHtml.append("<tr>");
                resultingHtml.append("<td>").append(results.getString("emp_name")).append("</td>");
                resultingHtml.append("<td>").append(results.getDouble("salary")).append("</td>");
                resultingHtml.append("</tr>");
                totals += results.getDouble("salary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
        resultingHtml.append("</table></body></html>");
        return resultingHtml.toString();
    }
}

