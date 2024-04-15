package cl.dechallenge.apirest.backend.helper;

import cl.dechallenge.apirest.backend.model.Departments;
import cl.dechallenge.apirest.backend.model.HiredEmployees;
import cl.dechallenge.apirest.backend.model.Jobs;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Departments> csvToDepartments(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withIgnoreHeaderCase().withTrim());) {

            List<Departments> departments = new ArrayList<Departments>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Departments department = new Departments(
                        Integer.parseInt(csvRecord.get(0)),
                        csvRecord.get(1)
                );

                departments.add(department);
            }

            return departments;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static List<Jobs> csvToJobs(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withIgnoreHeaderCase().withTrim());) {

            List<Jobs> jobs = new ArrayList<Jobs>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Jobs job = new Jobs(
                        Integer.parseInt(csvRecord.get(0)),
                        csvRecord.get(1)
                );

                jobs.add(job);
            }

            return jobs;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static List<HiredEmployees> csvToHiredEmployees(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withIgnoreHeaderCase().withTrim());) {

            List<HiredEmployees> hiredEmployees = new ArrayList<HiredEmployees>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                System.out.println(csvRecord);
                HiredEmployees hiredEmployee = new HiredEmployees(
                        Integer.parseInt(csvRecord.get(0)),
                        (csvRecord.get(1).isEmpty() ) ? null: csvRecord.get(1),
                        (csvRecord.get(2).isEmpty() ) ? null: Date.from(Instant.parse(csvRecord.get(2))),
                        (csvRecord.get(3).isEmpty() ) ? null: Integer.parseInt(csvRecord.get(3)),
                        (csvRecord.get(4).isEmpty() ) ? null: Integer.parseInt(csvRecord.get(4))
                );
                hiredEmployees.add(hiredEmployee);
            }

            return hiredEmployees;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}