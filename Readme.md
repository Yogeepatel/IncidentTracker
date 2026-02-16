# Incident Tracker Mini App

A full-stack web application to create, browse, search and manage production incidents.

This project was built as part of a full-stack engineering assignment focusing on API design, pagination, filtering, validation, and clean architecture.

---

## Features

* Create incidents with validation
* View incidents in paginated table (server-side pagination)
* Search incidents (debounced)
* Filter by severity and status
* Sort by created date
* View incident details
* Update incident status
* Seed database with 200 records
* Responsive UI (mobile + desktop)

---

## Project Structure

```
IncidentTracker/
 ├── backend/
 └── frontend/
```

---

## Backend Setup

### 1. Create Database

MySql
Run MySQL server and run schema.sql file command's , it will handle creation of database and table.


### 2. Configure `application.yml`

Update credentials:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/incident_tracker?useSSL=false
    username: {your username}
    password: {your password}
```

### 3. Run Backend

```bash
cd backend
./mvnw spring-boot:run
```

Server runs at:

```
http://localhost:8080
```

Database will auto-seed ~200 incidents when you will seed api "/seedData".

## API Endpoints

Base path: `/api/incidents`

### 1. Get Incident by ID

- `GET /api/incidents/{id}`

### 2. Get Incidents (Paginated + Filtered)

- `GET /api/incidents`
- Query params:
  - `page` (default: `0`)
  - `size` (default: `15`)
  - `sortBy` (default: `createdAt`)
  - `sortDirection` (default: `desc`, supported: `asc|desc`)
  - `service` (single enum value)
  - `severity` (multi-value enum)
  - `status` (multi-value enum)
  - `search` (title contains, case-insensitive)

Example:

```http
GET /api/incidents?page=0&size=10&sortBy=createdAt&sortDirection=desc&service=PAYMENT&severity=SEV1&severity=SEV2&status=OPEN&search=payment
```

### 3. Create Incident

- `POST /api/incidents`
- Body:

```json
{
  "title": "Checkout failing for some users",
  "service": "PAYMENT",
  "severity": "SEV2",
  "status": "OPEN",
  "owner": "alice@example.com",
  "summary": "Spike in payment failures since 10:30 UTC"
}
```

### 4. Update Incident

- `PATCH /api/incidents/{id}`
- Body (partial allowed):

```json
{
  "status": "MITIGATED",
  "summary": "Temporary mitigation deployed"
}
```

### 5. Trigger Random Seed Data (Async)

- `GET /seedData`
- Starts async seeding and returns immediately: `"Seeding started"`
- Seeding behavior:
  - Inserts up to 200 incidents
  - Waits 1 minute after each insert

## Enum Values

- `Service`: `BACKEND`, `FRONTEND`, `AUTH`, `DATABASE`, `PAYMENT`
- `Severity`: `SEV1`, `SEV2`, `SEV3`, `SEV4`
- `Status`: `OPEN`, `MITIGATED`, `RESOLVED`

## Error Response Format

Global exception handling returns JSON like:

```json
{
  "timestamp": "2026-02-16T10:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid Id",
  "path": "/api/incidents/abc"
}
```

---

## Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

App runs at:

```
http://localhost:5173
```

---

## API Endpoints

### Create Incident

```
POST /api/incidents
```

### Get Incidents (Pagination + Filters)

```
GET /api/incidents?page=0&size=10&search=&severity=&status=&sort=createdAt
```

### Get Incident By ID

```
GET /api/incidents/{id}
```

### Update Incident

```
PATCH /api/incidents/{id}
```

---

## Design Decisions

* UUID used as primary key for safer public exposure
* Server-side pagination to support large datasets
* JPA Specification used for dynamic filtering/search
* DTOs separate API contract from entity model
* Global exception handler for clean API responses
* Debounced search to reduce server load
* Responsive CSS instead of UI libraries to keep bundle lightweight

---

## Tradeoffs

* No authentication added (not required for assignment)
* Simple CSS used instead of component library

---

## Future Improvements

* Add authentication (JWT)
* Managing concurrency for multiple users 
* Add audit logs/history tracking
* WebSocket live incident updates
* Advanced filters (date range, multi-service)
* Docker deployment
* Unit & integration tests
* Dark mode UI
---

## HLD

<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/fd8878f5-66ee-4ae9-bae6-4e320e9cc161" />

## Pages  

<img width="956" height="437" alt="image" src="https://github.com/user-attachments/assets/bf6af00e-c8d2-4ff7-ab40-b6a3e1fa5d53" />

<img width="331" height="355" alt="image" src="https://github.com/user-attachments/assets/a8f5e45c-ed9e-414d-b84c-d3eff864172c" />

<img width="958" height="431" alt="image" src="https://github.com/user-attachments/assets/b575a084-b0cb-4c31-998a-cee9e5def12b" />


## Author

Yogendra Kumar Patel
GitHub: https://github.com/Yogeepatel
