# credit-offer

Steps to Run:
1. Install Postgres
   1. Install postgres sql in local or use open source db links. Make sure to update ip in properties file (Not required if installed in local)
   2. Create user postgres with password postgres or update other credentials in properties file
   3. Create Tables using create_tables.sql in src>main>resources>dbScripts and make sure to grant access to user (Hibernate will create tables itself if not manually created)
2. Install all spring and related dependencies using mvn install and mvn dependency:resolve.
3. Run the main class CreditOfferService.java

Dependencies:
1. Java - 1.8
2. Postgres
3. SpringBoot - 2.2.6.RELEASE
4. Swagger - SpringFox
5. Lombok
6. Spring-Jpa
7. Spring-started-web

Swagger Documentation: Swagger is added as part to easy api documentation. The same can be found at http://localhost:8080/creditofferservice/api/swagger-ui/index.html when service is up

Postman collections: https://api.postman.com/collections/20123823-ef35cb80-58b0-48ca-affb-60eab16a7834?access_key=PMAT-01H7D4YDMC6DZ59MJ3CQKYXE81

