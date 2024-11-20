# CMRS Paradigm in the Plant Care App Project

The **CMRS paradigm**—Controllers, Models, Repositories, and Services—is a structural approach we are using in our Spring Boot application to create a clear separation of concerns. This paradigm organizes the layers of the application to simplify development, testing, and maintenance. It also ensures smooth integration with a Vaadin-based front-end.

## 1. Controllers
### Role
- Controllers act as the entry point for incoming client requests or interactions from the Vaadin front-end.
- They are responsible for handling HTTP requests or UI events, delegating tasks to the service layer, and returning results.

### Responsibilities in the Plant Care App
- **Event Handling:** Handle events triggered by the front-end, such as adding a plant or updating its details.
- **Delegation:** Forward these requests to the appropriate service layer (e.g., `PlantService`).
- **Response Handling:** Return the processed data back to the front-end for display or further interaction.

### Example
In our project, a `PlantController` might:
- Process user actions such as "Add Plant" or "View Plant Details."
- Expose endpoints like:
   - `GET /plants` to retrieve all plants.
   - `POST /plants` to add a new plant using data from the front-end form.

---

## 2. Models
### Role
- Models represent the core domain objects of our application.
- They define the structure of entities stored in the database and serve as the primary data structure exchanged between layers.

### Responsibilities in the Plant Care App
- **Entity Representation:** Map to database tables using annotations like `@Entity`.
- **Data Encapsulation:** Define fields, getters, setters, and relationships between entities (e.g., One-to-Many or Many-to-Many).
- **Validation:** Include constraints like `@NotNull`, `@Size`, or `@Pattern` for entity validation.

### Example
A `Plant` model might include:
- **Attributes:** `id`, `name`, `type`, `wateringInterval`, `lastWateredDate`.
- **Relationships:** A `Room` model may reference a list of plants through a `@OneToMany` relationship.

---

## 3. Repositories
### Role
- Repositories handle direct interactions with the database, abstracting CRUD operations.
- They utilize Spring Data JPA to reduce boilerplate code and streamline database queries.

### Responsibilities in the Plant Care App
- **Database Access:** Perform SQL queries to retrieve, save, update, or delete data.
- **Data Transformation:** Translate raw database entities into domain objects.
- **Custom Queries:** Provide specialized query methods where needed.

### Example
The `PlantRepository` interface defines methods such as:
- `List<Plant> findByType(String type);`
- `Optional<Plant> findById(Long id);`

---

## 4. Services
### Role
- Services contain the core business logic of the application.
- They act as intermediaries between controllers and repositories, orchestrating workflows and ensuring data consistency.

### Responsibilities in the Plant Care App
- **Business Logic:** Implement rules such as setting plant watering reminders or checking room conditions.
- **Data Integration:** Combine data from repositories and external APIs like Perenual.
- **Error Handling:** Handle exceptions, such as invalid API responses or database failures.

### Example
The `PlantService` class:
- Retrieves plant data from the database using `PlantRepository`.
- Adds new plants using data fetched from the Perenual API.
- Validates and saves updates to plant information.

---

## Interaction with the Vaadin Front-End

### How CMRS Integrates with Vaadin
Vaadin allows us to build a modern front-end directly in Java, tightly coupled with the Spring Boot back-end. The CMRS paradigm ensures clean separation between the UI logic and back-end processes while facilitating smooth communication.

1. **Controllers** interact with Vaadin's UI components by responding to user-triggered events.
   - Example: A user clicks a "Save Plant" button in the Vaadin UI, which triggers an event handled by `PlantController`.

2. **Services** process the logic behind these actions.
   - Example: The `PlantService` validates the input and saves the new plant data.

3. **Repositories** interact with the database to persist or retrieve data required by the Vaadin views.
   - Example: A Vaadin grid displaying a list of plants fetches the data via the `PlantRepository`.

4. **Models** provide the structure for data exchanged between the layers and rendered in the Vaadin UI.
   - Example: A `Plant` object fetched from the repository is displayed in a Vaadin form or grid.

### Front-End and Back-End Flow
- **Vaadin Components:** Handle user interactions (e.g., form submissions, button clicks) and bind directly to models.
- **Service Calls:** Vaadin views directly or indirectly call service methods via Spring's dependency injection.
- **Data Updates:** Models returned from the service layer are bound to UI components, enabling dynamic updates.

---

## Advantages of CMRS with Vaadin
1. **Seamless Integration:** Vaadin's ability to directly use Java objects and Spring beans ensures smooth communication between the front-end and back-end.
2. **Reusability:** Service and repository layers can be reused for non-Vaadin clients (e.g., REST API).
3. **Real-Time UI Updates:** Vaadin's component binding complements the CMRS structure, providing real-time updates when the back-end modifies data.
4. **Separation of Concerns:** Clearly defined roles in CMRS prevent the UI logic from interfering with core business logic or database operations.

---

By adhering to the CMRS paradigm, we ensure a scalable, maintainable, and clean architecture for our project. This structure allows us to deliver a robust back-end while providing a modern, responsive user interface with Vaadin.