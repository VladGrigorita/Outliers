# Stock Exchange Outlier Detection

## Setup
1. Clone the repository.
2. Navigate to the project directory.
3. Build the project using Maven:
    ```sh
    mvn clean install
    ```
4. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Usage
- API to get random 30 data points from up to 2 files:
    ```
    GET /random-data-points?directoryPath=<path_to_directory>&numberOfFiles=<number_of_files>
    ```
- API to get outliers from up to 2 files:
    ```
    GET /outliers?directoryPath=<path_to_directory>&numberOfFiles=<number_of_files>
    ```
Note: If using Intellij Idea you can nagivate to getDataPoints.http file and run tests already prepared for both API-s

## Example
Place your CSV files in the resources/data/STOCK directory and use the APIs as shown above.

## Error Handling
The application handles the following errors:
- File not found
- Invalid CSV format
- Insufficient data points in CSV file
