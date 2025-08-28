# ArtifactManager

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
![Java](https://img.shields.io/badge/Java-21%2B-informational)


Enkel Java-konsollapp som lagrer og henter informasjon om historiske gjenstander.
Prosjektet demonstrerer klassisk OOP (arv, polymorfi, innkapsling) og database-tilkobling via **JDBC** til **MySQL**.

## Funksjoner
- Lagring og uthenting av gjenstander i MySQL
- Menydrevet konsollgrensesnitt
- OOP-design med abstrakt baseklasse og spesialiserte subklasser

## Mappestruktur
```
/src                    # Java-kildekode + SQL
/files/db.properties.example
.gitignore
LICENSE
README.md
```

## Forutsetninger
- **Java 21** (fungerer vanligvis med 17+)
- **MySQL 8+**
- **MySQL Connector/J** (JDBC-driver, én .jar-fil)

## Databaseoppsett
1. Opprett database (f.eks. `artifactdb`):
   ```sql
   CREATE DATABASE artifactdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```
2. Importer skjema/data:
   - I MySQL Workbench: importer `src/funn.sql`, **eller**
   - I terminal:
     ```bash
     mysql -u <bruker> -p artifactdb < src/funn.sql
     ```
3. Konfigurasjon:
   - Kopiér `files/db.properties.example` → `files/db.properties`
   - Fyll inn dine verdier:
     ```properties
     db.url=jdbc:mysql://localhost:3306/artifactdb?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true
     db.user=<din-bruker>
     db.password=<ditt-passord>
     ```

## Kjøring

### A) IntelliJ IDEA (anbefalt)
1. Åpne prosjektet i IntelliJ.
2. Legg til **MySQL Connector/J** som bibliotek:
   - *File → Project Structure → Libraries → + → Java* og velg `mysql-connector-j-<versjon>.jar`.
3. Kjør `HovedProgram` (Run-knappen i IntelliJ).

### B) Kommandolinje (Windows)
1. Lag en mappe `lib\` og legg `mysql-connector-j-<versjon>.jar` der.
2. Kompiler og kjør:
   ```powershell
   mkdir bin
   javac -d bin src\*.java
   java -cp "bin;lib\mysql-connector-j-<versjon>.jar" HovedProgram
   ```

> **Merk:** Ikke sjekk inn `files/db.properties` på GitHub. Kun `files/db.properties.example` skal ligge i repoet.

## Lisens
MIT
