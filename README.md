# Project IVH11B4
**This is only the api part of the application**
---
Meant to be used with the javascript view project part

found at:
- https://github.com/idsvanderzee/Project_IVH11B4_js
- https://github.com/idsvanderzee/Project_IVH11B4_js.git
---
To get started 
- Import hhuser.sql to your local mysql and run it
- Then simply build the project and run the spring application
---
**Adding more movies**

Either add them manually to the database

Or go to MovieApiController to the fillMovieDb method
then add the movie title and the release year to the hashmap

**This does require either a full rebuild of the database or you can force the MovieService.checkForMovies method to return false**
