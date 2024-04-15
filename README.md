# Project

The following project documents much of the Rest API solution for Data engineer sample api code.

## Owners

| Rol               |  Description           |
|---                |---                     |
| Developer         |  David Almendras       |
| Product Owner     |  None                  |

# Dependencies & Requirements

- Spring Boots

# Usage

- Java 11

## Section_01 Endpoints

Check folder /instructions/task/section_01

Run ddl over MySql/MariaDb

You can use Postman:

- Load data (POST)
  http://localhost:8080/de-challenge/v1/departments/upload
  http://localhost:8080/de-challenge/v1/jobs/upload
  http://localhost:8080/de-challenge/v1/hiredemployees/upload

- Read data (GET)
  http://localhost:8080/de-challenge/v1/jobs
  http://localhost:8080/de-challenge/v1/hiredemployees
  http://localhost:8080/de-challenge/v1/departments

![image get_department] ([instructions/task/section_01/get_department.JPG](https://github.com/dalmendras/de_challenge/blob/main/instructions/task/section_01/get_department.JPG))
![image get_jobs] (https://github.com/dalmendras/de_challenge/blob/main/instructions/task/section_01/get_jobs.JPG)
![ihired_employees] (https://github.com/dalmendras/de_challenge/blob/main/instructions/task/section_01/get_hired_employess.JPG)


## Section_02 Sql

Check folder /instructions/task/section_02


