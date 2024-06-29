
# Movies App

![Logo](https://raw.githubusercontent.com/saliiimm/Movies-App/master/app/src/main/res/mipmap-hdpi/ic_launcher.webp)

## Screenshots

<img src="https://github.com/saliiimm/Movies-App/assets/111188518/d9380753-fa42-4d46-82fc-c874d2d491fe" width="248px">
<img src="https://github.com/saliiimm/Movies-App/assets/111188518/6b4b040b-5d3b-41ed-8a71-774b110c5c33" width="248px">


## Acknowledgements

 - [Kotlin](https://kotlinlang.org/docs/android-overview.html)
 - [Android](https://www.android.com/intl/fr_fr/)
 - [XML](https://www.w3schools.com/xml/xml_whatis.asp)

## Libraries used

 - [Lottie files](https://kotlinlang.org/docs/android-overview.html)
 - [retrofit](https://square.github.io/retrofit/)
 - [Picasso](https://square.github.io/picasso/)


## Features

- Infinite Scroll
- Beautiful UI
- Icons to tell if the movie is -18 friendly or for adult only
- Country Producters Flags Displayed
- Reloading with SwipeRefreshLayout
- Clean Code and Structure

  
## Documentation

### Project Structure

#### Activities

1. **SplashScreenActivity:**
   - Animated splash screen using Lottie Files.
   - Ensures smooth transition to the main activity.

2. **MainActivity:**
   - Displays a RecyclerView to list fetched movies.
   - Implements swipe-to-refresh and infinite scrolling for a seamless user experience.

3. **MovieDetailActivity:**
   - Detailed view of a selected movie.
   - Loads and displays detailed movie information including images and production details.

#### Folders

- **utils:** Contains utility functions such as image loading and formatting utilities.
- **libs:** Includes third-party libraries like Retrofit and Picasso.
- **models:** Data classes representing API responses (e.g., Movie, MovieDetailResponse).
- **api:** Defines API interfaces for Retrofit.

#### Libraries

| Library      | Description                                     |
| :----------- | :---------------------------------------------- |
| **Lottie Files** | Used for creating animated splash screens.      |
| **Retrofit**     | Facilitates clean data fetching from remote APIs. |
| **Picasso**      | Efficiently handles image loading and caching.  |


## Security

### Storing API Keys

To securely store API keys and other sensitive information in this Android project, they are stored using `gradle.properties` and `local.properties` files.

---

## Open The Project

With Android studio

Read their Documentation to clone the repository:
[Link](https://source.android.com/docs/setup/reference/repo?hl=fr)


## API Reference

# Movies API

This API provides endpoints to discover movies and fetch detailed information about specific movies.

## Endpoints

### Discover Movies

Retrieve a list of movies.


```http
  GET https://api.themoviedb.org/3/discover/movie
```

- **URL Params**:
  | Parameter | Type     | Description                |
  | :-------- | :------- | :------------------------- |
  | `api_key` | `string` | **Required**. Your API key |

#### Response

| Field           | Type            | Description                                    |
| :-------------- | :-------------- | :--------------------------------------------- |
| `page`          | `int`           | The current page of results.                   |
| `results`       | `List<Movie>`   | List of movies returned.                       |
| `results.id`    | `int`           | Movie ID.                                      |
| `results.title` | `string`        | Movie title.                                   |
| `results.overview` | `string`     | Summary of the movie.                          |
| `results.poster_path` | `string`   | Path to the movie poster.                      |
| `results.release_date` | `string`  | Release date of the movie.                     |
| `results.vote_average` | `double`  | Average rating of the movie.                   |
| `results.vote_count` | `int`       | Number of votes for the movie.                 |
| `results.backdrop_path` | `string` | Path to the backdrop image.                    |

### Get Movie Details

Fetch detailed information about a specific movie.


```http
  GET https://api.themoviedb.org/3/movies/:id
```
- **URL Params**:
  | Parameter | Type     | Description                       |
  | :-------- | :------- | :-------------------------------- |
  | `id`      | `string` | **Required**. The ID of the movie to fetch |

#### Response

| Field                        | Type                  | Description                                    |
| :--------------------------- | :-------------------- | :--------------------------------------------- |
| `adult`                      | `boolean`             | Indicates if the movie is for adults.          |
| `backdrop_path`              | `string?`             | Path to the backdrop image.                    |
| `belongs_to_collection`      | `any?`                | Collection information.                        |
| `budget`                     | `int`                 | Movie budget.                                  |
| `genres`                     | `List<Genre>`         | Genres associated with the movie.              |
| `genres.id`                  | `int`                 | Genre ID.                                      |
| `genres.name`                | `string`              | Genre name.                                    |
| `homepage`                   | `string?`             | Movie homepage URL.                            |
| `id`                         | `int`                 | Movie ID.                                      |
| `imdb_id`                    | `string?`             | IMDb ID.                                       |
| `origin_country`             | `List<string>`        | Countries of origin.                           |
| `original_language`          | `string`              | Original language of the movie.                |
| `original_title`             | `string`              | Original title of the movie.                   |
| `overview`                   | `string`              | Summary of the movie.                          |
| `popularity`                 | `double`              | Popularity score of the movie.                 |
| `poster_path`                | `string?`             | Path to the poster image.                      |
| `production_companies`       | `List<ProductionCompany>` | Production companies involved in the movie.  |
| `production_companies.id`    | `int`                 | Production company ID.                         |
| `production_companies.logo_path` | `string?`         | Path to the production company's logo.         |
| `production_companies.name`  | `string`              | Production company name.                       |
| `production_companies.origin_country` | `string`      | Production company's origin country.           |
| `production_countries`       | `List<ProductionCountry>` | Production countries involved in the movie. |
| `production_countries.iso_3166_1` | `string`         | ISO 3166-1 code of the production country.     |
| `production_countries.name`  | `string`              | Production country name.                       |
| `release_date`               | `string`              | Release date of the movie.                     |
| `revenue`                    | `int`                 | Revenue generated by the movie.                |
| `runtime`                    | `int`                 | Runtime of the movie in minutes.               |
| `spoken_languages`           | `List<SpokenLanguage>` | Languages spoken in the movie.              |
| `spoken_languages.english_name` | `string`            | English name of the spoken language.           |
| `spoken_languages.iso_639_1` | `string`              | ISO 639-1 code of the spoken language.         |
| `spoken_languages.name`      | `string`              | Name of the spoken language.                   |
| `status`                     | `string`              | Status of the movie (e.g., "Released").        |
| `tagline`                    | `string?`             | Tagline of the movie.                          |
| `title`                      | `string`              | Movie title.                                   |
| `video`                      | `boolean`             | Indicates if there is a video available.       |
| `vote_average`               | `double`              | Average rating of the movie.                   |
| `vote_count`                 | `int`                 | Number of votes for the movie.                 |

## API Usage

To use the endpoints, include your API key for authentication and put it in the `local.properties` file.

Replace `{id}` in the URL with the actual movie ID when fetching details.

## Licenses


[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)



