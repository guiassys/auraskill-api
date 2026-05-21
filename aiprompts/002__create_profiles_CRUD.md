Follow the guidelines defined @/.github/copilot-instructions.md

Your task is to implement a complete CRUD for the "Profile" entity.

Here are the detailed instructions:

1.  **Analyze the Table Structure:**
    *   Use the SQL file @/src/main/resources/db/migration/V2__create_profiles_table.sql to understand the fields and data types of the `profiles` table.

2.  **Create the Entity Layer:**
    *   Use @/src/main/java/com/apexlone/auraskill_api/entity/Professional.java as an example to create the `Profile.java` entity.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/entity/Profile.java`.
    *   The entity must map to the `profiles` table.

3.  **Create the DTOs (Data Transfer Objects):**
    *   Use @/src/main/java/com/apexlone/auraskill_api/dto/ProfessionalRequest.java as an example to create `ProfileRequest.java`.
    *   Use @/src/main/java/com/apexlone/auraskill_api/dto/ProfessionalResponse.java as an example to create `ProfileResponse.java`.
    *   The files should be created in: `src/main/java/com/apexlone/auraskill_api/dto/`.

4.  **Create the Repository Layer:**
    *   Use @/src/main/java/com/apexlone/auraskill_api/repository/ProfessionalRepository.java as an example to create the `ProfileRepository.java` interface.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/repository/ProfileRepository.java`.

5.  **Create the Service Layer:**
    *   Use @/src/main/java/com/apexlone/auraskill_api/service/ProfessionalService.java as an example to create the `ProfileService.java` class.
    *   Implement the methods to create, read (all and by ID), update, and delete a `Profile`.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/service/ProfileService.java`.

6.  **Create the Controller Layer:**
    *   Use @/src/main/java/com/apexlone/auraskill_api/controller/ProfessionalController.java as an example to create the `ProfileController.java` class.
    *   Expose the RESTful endpoints for the CRUD operations.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/controller/ProfileController.java`.

7.  **Create Automated Tests with Cucumber:**
    *   Create the `.feature` files to describe the test scenarios for the `Profile` CRUD.
    *   Implement the `Step Definitions` for the defined scenarios.
    *   The tests must cover the creation, reading, updating, and deletion of profiles.
    *   The test files should be created in the `src/test/java` directory, mirroring the package structure of the source code (`src/main/java`).

Execute each step independently and wait for confirmation before proceeding to the next one. Start by creating the `Profile` entity.