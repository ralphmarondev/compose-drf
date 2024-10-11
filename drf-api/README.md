# 🦸‍♂️ Django DRF API 🦸‍♀️

Welcome to the **Django DRF API** for the **Compose DRF** project! This API is built using **Django Rest Framework (DRF)** and is designed to manage person records through simple, clean, and efficient endpoints.

## 🌈 Features

- **CRUD Operations**: Create, Read, Update, and Delete person records via RESTful endpoints.
- **JSON Responses**: Data is exchanged in JSON format.
- **PostgreSQL**: Stores all data in a PostgreSQL database.
- **Secure**: Standard Django security features like CSRF protection and token authentication (if required).

## ⚙️ Tech Stack

- **Django**: Python-based web framework for rapid development.
- **Django Rest Framework**: Toolkit for building Web APIs in Django.
- **PostgreSQL**: Database system for managing records.

## 📦 Setup

### 1. **Clone the repository**:

```bash
git clone https://github.com/ralphmarondev/compose-drf.git
```

### 2. **Navigate to the API folder**:

```bash
cd drf-api
```

### 3. **Set up a virtual environment** (optional but recommended):

```bash
python -m venv .venv
source .venv/bin/activate  # For Linux/macOS
.venv\Scripts\activate  # For Windows
source .venv/Scripts/activate # For Windows with git bash
```

### 4. **Install the required dependencies**:

```bash
pip install -r requirements.txt
```

### 5. **Set up PostgreSQL database**:

#### a. **Install PostgreSQL**:

- Download and install PostgreSQL from [here](https://www.postgresql.org/download/).

#### b. **Create a new database**:

- Open **pgAdmin**.
- Create a new database (e.g., `compose_drf`).

#### c. **Update Django settings**:

- Open `settings.py` and update the `DATABASES` configuration to match your PostgreSQL setup:
  ```python
  DATABASES = {
      'default': {
          'ENGINE': 'django.db.backends.postgresql',
          'NAME': 'compose_drf',  # Your database name
          'USER': 'your-username',  # Your PostgreSQL username
          'PASSWORD': 'your-password',  # Your PostgreSQL password
          'HOST': 'localhost',
          'PORT': '5432',
      }
  }
  ```

### 6. **Set up environment variables** (if needed):

If your project uses environment variables, create a `.env` file in the root directory and add any required settings, such as database credentials.

### 7. **Apply migrations**:

- Create the necessary database tables by applying migrations:
  ```bash
  python manage.py makemigrations
  python manage.py migrate
  ```

### 8. **Create a superuser** (for admin access):

- Create a superuser for accessing the Django admin panel:
  ```bash
  python manage.py createsuperuser
  ```

### 9. **Run the development server**:

- Start the Django server:
  ```bash
  python manage.py runserver
  ```

The API will be accessible at `http://127.0.0.1:8000/` by default.

### 10. **Update allowed hosts**:

- In your `settings.py`, ensure that your IP address or localhost is added to `ALLOWED_HOSTS`:
  ```python
  ALLOWED_HOSTS = ['127.0.0.1', 'localhost']
  ```

---

## 🛠️ API Endpoints

Here’s a list of available API endpoints:

### **1. Create a Person (POST)**

- **URL**: `/api/persons/`
- **Method**: `POST`
- **Request Body** (example):
  ```json
  {
    "name": "Ralph Maron Eda",
    "age": 21
  }
  ```
- **Response** (example):
  ```json
  {
    "id": 1,
    "name": "Ralph Maron Eda",
    "age": 21
  }
  ```

### **2. Retrieve All Persons (GET)**

- **URL**: `/api/persons/`
- **Method**: `GET`
- **Response** (example):
  ```json
  [
    {
      "id": 1,
      "name": "Ralph Maron Eda",
      "age": 21
    },
    {
      "id": 2,
      "name": "Kira",
      "age": 22
    }
  ]
  ```

### **3. Retrieve a Single Person by ID (GET)**

- **URL**: `/api/persons/{id}/`
- **Method**: `GET`
- **Response** (example):
  ```json
  {
    "id": 1,
    "name": "Ralph Maron Eda",
    "age": 21
  }
  ```

### **4. Update a Person by ID (PUT)**

- **URL**: `/api/persons/{id}/`
- **Method**: `PUT`
- **Request Body** (example):
  ```json
  {
    "name": "Ralph Maron Eda",
    "age": 21
  }
  ```
- **Response** (example):
  ```json
  {
    "id": 1,
    "name": "Ralph Maron Eda",
    "age": 21
  }
  ```

### **5. Delete a Person by ID (DELETE)**

- **URL**: `/api/persons/{id}/`
- **Method**: `DELETE`
- **Response**: Status `204 No Content`

---

## 🧪 Testing

You can test the API using tools like **Postman** or **cURL**.

### Example with Postman:

1. Open Postman and create a new request.
2. Set the request type (e.g., `GET`, `POST`, etc.).
3. Enter the API endpoint URL.
4. For `POST` and `PUT` requests, add the necessary request body in JSON format.
5. Send the request and observe the response.

### Example with cURL:

```bash
curl -X GET http://127.0.0.1:8000/api/persons/
```

---

## 🙌 Acknowledgements

Thanks to the [Django Rest Framework](https://www.django-rest-framework.org/) community for providing such an amazing toolkit for building APIs!

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE.txt) file for details.

## 📧 Contact

If you have any questions or suggestions, feel free to reach out to me at [edaralphmaron@gmail.com](mailto:edaralphmaron@gmail.com).
