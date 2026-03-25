# SpotBugsSASTScan

A simple static analysis walkthrough using **SpotBugs** to identify and fix common Java issues, including unused fields, reliance on default encoding, and patterns relevant to secure coding.

## Overview

This project demonstrates how to:

- Run a SpotBugs SAST scan on a Java application  
- Interpret the findings  
- Modify the source code to address the issues  
- Re‑scan to verify the fixes  

The scanned application is the **SecureLoginApp** from the previous project, with one intentionally added insecure pattern to trigger SpotBugs alerts.

## Tools Used

- Java 21+  
- SpotBugs (command‑line, `-textui` mode)  
- Ubuntu terminal  

## How to Run the Scan

Compile the Java file:
```bash
javac SecureLoginApp.java
```

Run SpotBugs:
```bash
spotbugs -textui .
```

## Intentional Issue Added

To demonstrate how SpotBugs detects unused or sensitive fields, the following line was added:
```java
String dbPassword = "P@ssw0rd!";
```

SpotBugs correctly flags this as an Unread Field (UrF) and a potential security concern.

## Initial Findings
SpotBugs reported two issues:
- Dm – Reliance on default encoding (Scanner without charset)
- UrF – Unread field (dbPassword)
<img width="1452" height="85" alt="Bugs_Screenshot" src="https://github.com/user-attachments/assets/5f956db9-587e-4b1f-a24a-1dfcd0f991c4" />

*Screenshot of initial SpotBugs findings.*

## Fixes Applied
### 1. Default Encoding Warning (Dm)
SpotBugs warns when Scanner is created without specifying a charset.

**Before:**
```java
Scanner scanner = new Scanner(System.in);
```

**After:**
```java
import java.nio.charset.StandardCharsets;
Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
```

### 2. Unread Field (UrF)
The intentionally added dbPassword field was unused.

**Before:**
```java
String dbPassword = "P@ssw0rd!";
```

**After:**

*The variable was removed.*

## Final Clean Scan
After applying the fixes, SpotBugs reports no remaining issues.

<img width="586" height="65" alt="CleanCode_Screenshot" src="https://github.com/user-attachments/assets/34915db7-ca9e-4a8d-9dfa-81a8962856f4" />

*Screenshot of clean SpotBugs scan.*

## Learning Goals
- Understand how to run a SAST scan using SpotBugs
- Interpret common SpotBugs warnings
- Apply secure coding fixes based on tool findings
- Validate fixes by re‑scanning the code
