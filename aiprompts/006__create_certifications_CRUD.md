Follow the guidelines defined @/.github/copilot-instructions.md

Your task is to implement a complete CRUD for the "Certification" entity.

Here are the detailed instructions:

1.  **Analyze the Table Structure:**
    *   Use the SQL file @/src/main/resources/db/migration/V6__create_certifications_table.sql to understand the fields and data types of the `certifications` table.

2.  **Create the Entity Layer:**
    *   Create the `Certification.java` entity.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/entity/Certification.java`.
    *   The entity must map to the `certifications` table.

3.  **Create the DTOs (Data Transfer Objects):**
    *   Create `CertificationRequest.java`.
    *   Create `CertificationResponse.java`.
    *   The files should be created in: `src/main/java/com/apexlone/auraskill_api/dto/`.

4.  **Create the Repository Layer:**
    *   Create the `CertificationRepository.java` interface.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/repository/CertificationRepository.java`.

5.  **Create the Service Layer:**
    *   Create the `CertificationService.java` class.
    *   Implement the methods to create, read (all and by ID), update, and delete a `Certification`.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/service/CertificationService.java`.

6.  **Create the Controller Layer:**
    *   Create the `CertificationController.java` class.
    *   Expose the RESTful endpoints for the CRUD operations.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/controller/CertificationController.java`.

7.  **Create Automated Tests with Cucumber:**
    *   Create the `.feature` files to describe the test scenarios for the `Certification` CRUD.
    *   Implement the `Step Definitions` for the defined scenarios.
    *   The tests must cover the creation, reading, updating, and deletion of certifications.
    *   The test files should be created in the `src/test/java` directory, mirroring the package structure of the source code (`src/main/java`).

Execute each step independently and wait for confirmation before proceeding to the next one. Start by creating the `Certification` entity.