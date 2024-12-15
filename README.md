# Introduction  
**BookHub** is a web application designed to facilitate the purchase of e-books and provide a complete user experience for managing personal digital libraries. With BookHub, users can create an account, log in, and securely make payments through PayPal to purchase their favorite books. The application allows administrators to perform CRUD (Create, Read, Update, Delete) operations on books, categories, and authors, efficiently managing the book catalog. Users can also organize their purchased books into personalized collections.

The purpose of BookHub is to offer an integrated platform that combines easy e-book purchasing, catalog management for administrators, and personalized digital library organization for users, all in a secure and user-friendly environment.

## Track the Progress of the BookHub Project  

| Column        | Description                                                                                                                     |
|---------------|---------------------------------------------------------------------------------------------------------------------------------|
| Backlog       | Contains all user stories, tasks, and features to be developed. It serves as a list of all pending work.                       |
| In Progress   | Includes tasks currently being developed. It visualizes ongoing work to ensure a continuous workflow.                         |
| Review        | After completing a task, it is moved here for code review and peer review. This phase includes the creation of pull requests. |
| Testing       | Contains tasks that have passed code review and require extensive testing (unit, integration, and acceptance) to ensure quality. |
| Done          | Fully developed, reviewed, and tested tasks are moved here, indicating they are complete and finalized.                        |

Follow our progress by visiting the following link: [Trello Board].

## BookHub Application Features  

### User Management Module  
**User Registration and Login:**  
- Allow users to register on the platform.  
- Enable login to access personal accounts.  
- Ensure credential security.  

### Purchasing Module  
**E-Book Purchase:**  
- Integration with PayPal for fast and secure payments.  
- Transaction processing for e-book purchases.  
- Confirmation of purchase and delivery of the digital book to the user.  

### Content Management Module  
#### Book Management:  
- Add new books to the catalog.  
- Edit details of existing books.  
- Delete books from the catalog.  
- List all books available to users.  

#### Book Categories:  
- Classify books into different categories.  
- Facilitate navigation and book search by category.  
- Improve catalog organization.  

#### Author Management:  
- Add new authors to the database.  
- Edit information about existing authors.  
- Delete authors from the database.  
- Keep author information updated.  

### Personal Library Module  
**Personal Book Library:**  
- Allow users to organize their purchased books into personalized collections or "virtual shelves."  
- Facilitate the creation, editing, and deletion of book collections.  
- Enhance access to and management of the user's personal library.  

### Reporting Module  
**Activity and Sales Reports:**  
- Generate reports on purchases made by users.  
- Display book sales statistics.  
- Provide detailed information about user activity, such as most purchased books or popular authors.  

## Project Layer Descriptions  

| Layer          | Description                                                                                     |
|----------------|-------------------------------------------------------------------------------------------------|
| api            | Contains REST controllers that handle HTTP requests and responses.                              |
| entity         | Defines the data model entities mapped to database tables.                                      |
| repository     | Provides the interface for CRUD operations and database interaction.                            |
| service        | Declares business logic and operations to be performed on entities.                             |
| service impl   | Implements the business logic defined in services, using the necessary repositories.            |
