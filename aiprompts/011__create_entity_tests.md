Follow the guidelines defined @/.github/copilot-instructions.md

Your task is to create automated tests using Cucumber for all existing entities in the `src/main/java/com/apexlone/auraskill_api/entity` directory, with the exception of `Profile.java`, which already has tests.

Here are the detailed instructions:

1.  **Identify Entities for Testing:**
    *   List: 
             src/main/java/com/apexlone/auraskill_api/entity/Professional.java

2.  **Create Automated Tests with Cucumber for Each Entity:**
    *   For each entity identified in step 1, create the necessary Cucumber test files.
    *   **Feature Files:**
        *   Create a `.feature` file for each entity to describe its test scenarios (e.g., `entity_name.feature`).
        *   These files should be placed in `src/test/resources/features/`.
        *   The feature files should define scenarios for basic CRUD (Create, Read, Update, Delete) operations relevant to the entity.
        *   Refer to `@/src/test/resources/features/profile.feature` as an example for structure and content.
    *   **Step Definitions:**
        *   Create a Java class for the Step Definitions for each entity (e.g., `EntityNameSteps.java`).
        *   These files should be placed in `src/test/java/com/apexlone/auraskill_api/steps/`.
        *   Implement the step definitions to interact with the API endpoints for the respective entity.
        *   Refer to `@/src/test/java/com/apexlone/auraskill_api/steps/ProfileSteps.java` as an example for structure and implementation.

3.  **Update Test Documentation (Optional but Recommended):**
    *   Consider updating `@/src/test/README.md` to include information about the newly added tests, if applicable.

Execute each step independently and wait for confirmation before proceeding to the next one. Start by identifying the entities that need testing.