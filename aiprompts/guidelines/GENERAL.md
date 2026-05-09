# General Development Guidelines

This document outlines the general best practices to be followed by any AI agent working on this project.

## Core Principles

1.  **Propose Before Changing:** Always request authorization before modifying the source code. Present your proposed changes and wait for developer approval.
2.  **Simplicity and Maintenance:** Keep the code simple to facilitate maintenance. Make the minimum changes necessary to achieve the desired goal.
3.  **Don't Break What Works:** Avoid breaking existing functionality. Your changes should be incremental improvements.
4.  **No Code Repetition (DRY):** Follow the "Don't Repeat Yourself" principle.
5.  **Default to English:** All source code, comments, and logs must be in English. English should be the default and fallback language for all user-facing text.

## Technical Implementation

6.  **Design Patterns:** Apply relevant Design Patterns where applicable.
7.  **Clean Code & SOLID:** Adhere to Clean Code principles and the SOLID paradigm.
8.  **Defensive & Secure Implementation:**
    *   Implement defensive coding practices to handle unexpected states and inputs.
    *   Ensure security measures are in place to prevent common vulnerabilities (e.g., SQL Injection, XSS).
9.  **Configuration Files:** Do not use hardcoded parameters. All system configurations must be stored in dedicated configuration files.
10. **Compatibility Check:** Before proposing a solution, analyze the compatibility of the libraries defined in the `@/pom.xml` file.

## Environment

All proposed solutions must consider the following environment:

-   **Framework**: Spring-boot
-   **Operational System**: WSL Ubuntu
-   **Java Version**: openjdk 21.0.10 2026-01-20
-   **Apache Maven **: 3.8.7
