package ru.sbt.bit.ood.solid.homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SalaryReporter {
    private final PreparedStatement ps;

    public SalaryReporter(Connection connection) throws SQLException {
        ps = connection.prepareStatement("select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
                "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                " sp.date >= ? and sp.date <= ? group by emp.id, emp.name");
    }

    public ResultSet report(String departmentId, LocalDate dateFrom, LocalDate dateTo) throws SQLException {
        ps.setString(0, departmentId);
        setDate(dateFrom, dateTo);
        return ps.executeQuery();
    }

    private void setDate(LocalDate dateFrom, LocalDate dateTo) throws SQLException {
        ps.setDate(1, new java.sql.Date(dateFrom.toEpochDay()));
        ps.setDate(2, new java.sql.Date(dateTo.toEpochDay()));
    }

}
