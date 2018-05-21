# Project Title

Bank payments system.



## Project description

Bank payments system. User owns one or more accounts(deposit, credit). 
Access to your Account can be obtained after entering 
your login and password. user can make bank transfers, pay bills, show
general information (account balance, last transactions,
validity period).

For Credit Accounts is also available information about the credit limit,
current debt, the amount of accrued interest, the loan rate.

For Deposit Accounts - the amount of the deposit, the rate, the history of 
the replenishment.

The user can do a request for opening a credit account, if
there is none. The administrator confirms the opening of the account with
the size of the deposit and the validity period.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

##Requirements to run

* MySQL
* Java JDK 1.8 
* Apache Maven
* Apache Tomcat

##Run project
* Go to the file  \src\main\resources\dao.properties
and change db.user and db.password to your MySQL login and password.
* Execute createTables.sql (located in the root of the project)
* Install maven http://www.apache-maven.ru/install.html
* Enter command "mvn tomcat7:run"
* Open browser and follow the link http://localhost:8080/
* Create new users or use pre-created admin user: login - roydgar, password - VS824pass98

## Authors

* **Gritsaenko V.P** - *Initial work* - [Roydgar](https://github.com/Roydgar)

## Acknowledgments

* Lecturer Maksim Liashenko
* Classmates
* Hat tip to anyone who's code was used
* Inspiration

