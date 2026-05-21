Follow the guidelines defined @/.github/copilot-instructions.md

Your task is to implement a complete CRUD for the "Skill" entity.

Here are the detailed instructions:

1.  **Analyze the Table Structure:**
    *   Use the SQL file @/src/main/resources/db/migration/V3__create_skills_table.sql to understand the fields and data types of the `skills` table.

2.  **Create the Entity Layer:**
    *   Create the `Skill.java` entity.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/entity/Skill.java`.
    *   The entity must map to the `skills` table.

3.  **Create the DTOs (Data Transfer Objects):**
    *   Create `SkillRequest.java`.
    *   Create `SkillResponse.java`.
    *   The files should be created in: `src/main/java/com/apexlone/auraskill_api/dto/`.

4.  **Create the Repository Layer:**
    *   Create the `SkillRepository.java` interface.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/repository/SkillRepository.java`.

5.  **Create the Service Layer:**
    *   Create the `SkillService.java` class.
    *   Implement the methods to create, read (all and by ID), update, and delete a `Skill`.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/service/SkillService.java`.

6.  **Create the Controller Layer:**
    *   Create the `SkillController.java` class.
    *   Expose the RESTful endpoints for the CRUD operations.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/controller/SkillController.java`.

7.  **Create Automated Tests with Cucumber:**
    *   Create the `.feature` files to describe the test scenarios for the `Skill` CRUD.
    *   Implement the `Step Definitions` for the defined scenarios.
    *   The tests must cover the creation, reading, updating, and deletion of skills.
    *   The test files should be created in the `src/test/java` directory, mirroring the package structure of the source code (`src/main/java`).

Execute each step independently and wait for confirmation before proceeding to the next one. Start by creating the `Skill` entity.