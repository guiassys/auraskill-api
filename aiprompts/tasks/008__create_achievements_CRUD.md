You are an AI agent specializing in software development.

Follow the guidelines defined in @/aiprompts/guidelines/GENERAL.md and the persona in @/aiprompts/personas/EXPERT_FRONTEND_DEVELOPER.md.

Your task is to implement a complete CRUD for the "Achievement" entity.

Here are the detailed instructions:

1.  **Analyze the Table Structure:**
    *   Use the SQL file @/src/main/resources/db/migration/V8__create_achievements_table.sql to understand the fields and data types of the `achievements` table.

2.  **Create the Entity Layer:**
    *   Create the `Achievement.java` entity.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/entity/Achievement.java`.
    *   The entity must map to the `achievements` table.

3.  **Create the DTOs (Data Transfer Objects):**
    *   Create `AchievementRequest.java`.
    *   Create `AchievementResponse.java`.
    *   The files should be created in: `src/main/java/com/apexlone/auraskill_api/dto/`.

4.  **Create the Repository Layer:**
    *   Create the `AchievementRepository.java` interface.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/repository/AchievementRepository.java`.

5.  **Create the Service Layer:**
    *   Create the `AchievementService.java` class.
    *   Implement the methods to create, read (all and by ID), update, and delete a `Achievement`.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/service/AchievementService.java`.

6.  **Create the Controller Layer:**
    *   Create the `AchievementController.java` class.
    *   Expose the RESTful endpoints for the CRUD operations.
    *   The file should be created at: `src/main/java/com/apexlone/auraskill_api/controller/AchievementController.java`.

7.  **Create Automated Tests with Cucumber:**
    *   Create the `.feature` files to describe the test scenarios for the `Achievement` CRUD.
    *   Implement the `Step Definitions` for the defined scenarios.
    *   The tests must cover the creation, reading, updating, and deletion of achievements.
    *   The test files should be created in the `src/test/java` directory, mirroring the package structure of the source code (`src/main/java`).

Execute each step independently and wait for confirmation before proceeding to the next one. Start by creating the `Achievement` entity.