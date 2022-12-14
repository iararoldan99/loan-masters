# 'Loan Masters' 
Java SpringBoot application to grant money loans as a Bank entity to clients. 

# Functionalities π

As a Bank (admin):<br><br>
β Grant money loans to clients <br>
β Validate if client is eligible for loan (validate profile: is client a debtor? does the client have any criminal record? etc) <br>
β Reject clients if they don't pass the validation check <br>
β Seize clients who don't pay 3 consecutive dues <br>
β Check all money balance available to grant <br>
β List all my clients <br>
β List all loans granted <br>
β Filter the best clients <br>
β Filter blacklisted clients <br>
β Filter unpaid loans <br>
β Filter fully payed loans <br>

As a Client:<br><br>
β Apply for a loan, if validation is successful you will get the money, otherwise rejected <br>
β Choose how you wish to pay for your loan: once a month or one time full payment <br>
β List all my loans taken <br>
β Filter my unpaid loans <br>
β Filter my overdues <br>
β Filter my fully payed loans <br>
β List all loans granted <br>

# Build Tools π§
π BACKEND: Java 17, Maven, SpringBoot, SQL, JPA, Hibernate<br>

# DDBB π
MySQL

# Deploy π
Heroku

# Requests  π‘ 
Postman

## BACKEND Get Started (localhost:8080)

Requirements: Java 17, Maven, MySQL database, preferred IDE: IntelliJ IDEA 

### Please make sure you set up your database configuration (user, password and schema) in application.properties
