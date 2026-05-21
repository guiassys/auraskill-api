Use: \\wsl.localhost\Ubuntu\home\guiassys\devtools\repos\aura-api\.github\copilot-instructions.md

Your task is to create automated tests using Cucumber for all existing entities in the `\\wsl.localhost\Ubuntu\home\guiassys\devtools\repos\aura-api\src\main\java\com\apexlone\auraskill_api\entity`.

Here are the detailed instructions:

1.  **Identify Entities for Testing:**
    *   Professional: `\\wsl.localhost\Ubuntu\home\guiassys\devtools\repos\aura-api\src\main\java\com\apexlone\auraskill_api\entity\Professional.java`

2.  **Create Automated Tests with Cucumber for Each Entity:**
    *   For each entity identified in step 1, create the necessary Cucumber test files.
    *   **Feature Files:**
        *   Create a `.feature` file for each entity to describe its test scenarios (e.g., `entity_name.feature`).
        *   These files should be placed in `\\wsl.localhost\Ubuntu\home\guiassys\devtools\repos\aura-api\src\test\resources\features`.
        *   The feature files should define scenarios for basic CRUD (Create, Read, Update, Delete) operations relevant to the entity.
        *   Refer to `\\wsl.localhost\Ubuntu\home\guiassys\devtools\repos\aura-api\src\test\resources\features\profile.feature` as an example for structure and content.
    *   **Step Definitions:**
        *   Create a Java class for the Step Definitions for each entity (e.g., `EntityNameSteps.java`).
        *   These files should be placed in `\\wsl.localhost\Ubuntu\home\guiassys\devtools\repos\aura-api\src\test\java\com\apexlone\auraskill_api\steps`.
        *   Implement the step definitions to interact with the API endpoints for the respective entity.
        *   Refer to `\\wsl.localhost\Ubuntu\home\guiassys\devtools\repos\aura-api\src\test\java\com\apexlone\auraskill_api\steps\ProfileSteps.java` as an example for structure and implementation.
*  **Expected result:**
    *   As expected, I hope to see the shell command to create the complete suggested source code in the appropriate directory.