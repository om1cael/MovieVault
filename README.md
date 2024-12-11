# 🎥 Movie Vault

Manage your movie collection effortlessly! With this application, you can organize the movies you've watched and plan to watch in the future. Take advantage of advanced features like API integrations, data persistence, and dynamic filters to enhance your movie management experience.

> This project was developed as a Java practice project to enhance my understanding of key Java concepts such as file handling, API integration, Java Streams, and object-oriented design. It serves as a hands-on way to improve my Java skills by applying them to real-world scenarios. 

## 🔧 Features

### 1. **Movie Management**
- Add, edit, and delete movies with detailed information such as title, genre, year, rating, and status.
- List movies with filters for genre, year, or rating.

### 2. **File Storage**
- Save and load your movie collection to/from files in JSON format.
- Ensure your collection is safely backed up and shareable.

### 3. **API Integration**
- Fetch detailed information about movies (e.g., synopsis, ratings) via the OMDb API.

### 4. **Streams & Reports**
- Use powerful stream operations to filter, sort, and generate reports by genre, year, or rating.

### 5. **Search & Reports**
- Search movies by title, genre, or year.
- Generate detailed reports tailored to your collection’s data.

---

## 🛠️ Technologies Used
- **Java**: The core programming language used to develop the application.
- **File Handling**: JSON for data persistence.
- **API Integration**: OMDb API for fetching movie data.
- **Java Streams**: For filtering, sorting, and generating reports.

---

## 📗 How to Use

1. **Clone the Repository**
   ```bash
   git clone https://github.com/om1cael/MovieVault.git
   ```

2. **Build the Project**
   Compile the project using Gradle.

3. **Run the Application**
   Launch the application through your IDE or Gradle and start managing your movies!

4. **API Key Setup**
   - Obtain an API key from [OMDb API](https://www.omdbapi.com/).
   - Add your API key in your system's envinronment variable (OMDB_API_KEY).

---

## 📊 Sample JSON Structure
```json
[
  {
    "title": "Game of Thrones",
    "genre": "Medieval Fantasy",
    "year": 2011,
    "rating": 5,
    "status": "Watched"
  },
  {
    "title": "Interstellar",
    "genre": "Science Fiction",
    "year": 2014,
    "rating": 5,
    "status": "Watched"
  }
]
```

---

## ✨ Contributions
Feel free to fork this repository, open issues, or submit pull requests. Contributions are always welcome!

---

## 🔗 Links
- [OMDb API](https://www.omdbapi.com/)
