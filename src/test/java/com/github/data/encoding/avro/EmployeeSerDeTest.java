package com.github.data.encoding.avro;

import com.github.data.encoding.avro.serde.AbstractSerDe;
import com.github.data.encoding.avro.serde.EmployeeSerDe;
import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSerDeTest{
    private static final String resourceFolder = "./src/test/destination";
    private static final String employeeAvroFile = "/employees.avro";
    private AbstractSerDe<Employee> employeeSerDe = new EmployeeSerDe();
    @BeforeClass
    public static void setUpDirectory() throws Exception{
        File file = new File(resourceFolder);
        file.mkdir();
    }


    @Test
    public void Given_Employees_SerializeToFile_Then_EmployeesShouldBePersisted() throws Exception{
        employeeSerDe.serializeToFile(createDummyEmployees(), resourceFolder + employeeAvroFile);
    }

    @Test
    public void Given_DestinationPath_DeSerializeFromFile_Then_EmployeeListShouldBeReturned() throws Exception{
        List<Employee> employees = employeeSerDe.deserializeFromFile(resourceFolder + employeeAvroFile);
        assertEquals(2, employees.size());
        assertEquals("F1", employees.get(0).getFirstName().toString());
        assertEquals("L1", employees.get(0).getLastName().toString());
    }
    private List<Employee> createDummyEmployees(){
        List<Employee> employees = new ArrayList<>();
        employees.add(createDummyEmployee(1, "F1", "L1"));
        employees.add(createDummyEmployee(2, "F2", "L2"));
        return employees;
    }

    private Employee createDummyEmployee(Integer id, String firstName, String lastName){
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        return employee;
    }
    @AfterClass
    public static void cleanUpDirectory() throws Exception{
        File file = new File(resourceFolder);
        file.delete();
    }
}