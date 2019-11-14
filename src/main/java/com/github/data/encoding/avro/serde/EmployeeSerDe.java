package com.github.data.encoding.avro.serde;

import com.github.data.encoding.avro.Employee;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSerDe extends AbstractSerDe<Employee> {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeSerDe.class);

    @Override
    public void serializeToFile(Employee employee, String destination) throws Exception {
        logger.info("Serializing employee to {}", destination);
        schema = employee.getSchema();
        DataFileWriter<Employee> dataFileWriter = constructDataFileWriter(constructDatumWriter(Employee.class), destination);
        dataFileWriter.append(employee);
        dataFileWriter.close();
    }

    @Override
    public List<Employee> deserializeFromFile(String source) throws Exception {
        logger.info("Reading employees from {}", source);
        DataFileReader<Employee> dataFileReader = constructDataFileReader(constructDatumReader(Employee.class), source);
        List<Employee> employees = new ArrayList<>();
        while(dataFileReader.hasNext()){
            employees.add(dataFileReader.next());
        }
        return employees;
    }
}
