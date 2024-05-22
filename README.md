# Medimate Server

## Description
Medimate Server is a project developed for drug stores, designed and implemented by students at Saigon University. Please note that as an ongoing educational project, there may be some issues that we are working to resolve.

## Participating Members
- Trương Văn Công
- Võ Văn Huấn
- Phạm Đăng Khoa

## Current Issues and Future Improvements
### Auto Update Coupon Status
One of the current challenges is that the system does not automatically update the status of coupons when they expire. We plan to address this by implementing a cron job or a similar mechanism to check the expiration dates and update the statuses accordingly.

## Client UI
- Link: https://github.com/phamdangkhoagh/ClientSellingMedicine
- The client ui described, guided and wrote by phamdangkhoagh

## Getting Started
To get a copy of the project up and running on your local machine, follow these steps.

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Maven
- MySQL

### Installation

1. **Clone the Repository**
   ```sh
   git clone https://github.com/vancong2305/medimateserver.git
   cd medimateserver
2. **Configure the Database**
   - Ensure you have MySQL installed and running.
   - Update the database configuration in the `application.properties` file with your MySQL credentials.
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/medimate
     spring.datasource.username=yourusername
     spring.datasource.password=yourpassword
     ```

3. **Build and Run**
   - Build the project using Maven:
     ```sh
     mvn clean install
     ```
   - Run the application:
     ```sh
     mvn spring-boot:run
     ```

## Contributing
Contributions are welcome! Feel free to submit a pull request to suggest improvements or fixes.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

