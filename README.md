# anime-lists

This repository is to store and provide the mapping between various anime sources like

* [MyAnimeList](https://myanimelist.net/)
* [TheTVDB](https://thetvdb.com/)
* [TheMovieDB](https://www.themoviedb.org/)
* [Anime-Planet](https://www.anime-planet.com/)
* [Kitsu.io](https://kitsu.io/)
* [Anisearch](https://www.anisearch.com/)
* [anilist.co](https://anilist.co/)
* [anidb.net](https://anidb.net/)
* [livechart.me](https://www.livechart.me/)
* [Anime News Network](https://www.animenewsnetwork.com/)

## lists

The lists are generated through the [anime-lists-generator](https://github.com/Fribb/anime-lists-generator) based on a couple of already existing lists and mappings from other repositories.

1. [anime-offline-database](https://github.com/manami-project/anime-offline-database/)
2. [Anime-Lists/anime-lists](https://github.com/Anime-Lists/anime-lists/)

### anime-offline-database-reduced

This file is is the reduced version of the anime-offline-database to only include the necessary IDs.

Example:

```json
{
  "type" : "MOVIE",
  "anidb_id" : 5459,
  "anilist_id" : 3269,
  "animecountdown_id" : 41283,
  "animenewsnetwork_id" : 8719,
  "anime-planet_id" : "hack-g-u-trilogy",
  "anisearch_id" : 4491,
  "kitsu_id" : 2895,
  "livechart_id" : 4721,
  "mal_id" : 3269,
  "simkl_id" : 41283
},
```

### anime-lists-reduced

This file is the reduced version of the ScudLee anime-lists project also only including the necessary IDs.

Example:

```json
{
  "anidb_id" : 5458,
  "themoviedb_id" : 44298,
  "tvdb_id" : 91021,
  "season" : {
    "tvdb" : 1,
    "tmdb" : 1
  }
}, {
  "anidb_id" : 5459,
  "imdb_id" : "tt1164545",
  "themoviedb_id" : 8864,
  "tvdb_id" : 79099
}
```

### anime-list-full

This file will merge the elements over the "anidb_id".

Example:

```json
{
  "type" : "OVA",
  "anidb_id" : 5458,
  "anilist_id" : 3390,
  "animecountdown_id" : 40868,
  "animenewsnetwork_id" : 8720,
  "anime-planet_id" : "amuri-in-star-ocean",
  "anisearch_id" : 4445,
  "kitsu_id" : 2977,
  "livechart_id" : 7110,
  "mal_id" : 3390,
  "simkl_id" : 40868,
  "themoviedb_id" : 44298,
  "tvdb_id" : 91021,
  "season" : {
    "tvdb" : 1,
    "tmdb" : 1
  }
}, {
  "type" : "MOVIE",
  "anidb_id" : 5459,
  "anilist_id" : 3269,
  "animecountdown_id" : 41283,
  "animenewsnetwork_id" : 8719,
  "anime-planet_id" : "hack-g-u-trilogy",
  "anisearch_id" : 4491,
  "imdb_id" : "tt1164545",
  "kitsu_id" : 2895,
  "livechart_id" : 4721,
  "mal_id" : 3269,
  "simkl_id" : 41283,
  "themoviedb_id" : 8864,
  "tvdb_id" : 79099
},
```

### anime-list-mini

Same as `anime-list-full` but minified (no new-lines nor indenting)

To use the IDs for requests on the websites the following "endpoints" can be used by replacing the {id} part in the URL:

* https://anidb.net/anime/{id}
* https://anilist.co/anime/{id}
* https://anime-planet.com/anime/{id}
* https://anisearch.com/anime/{id}
* https://kitsu.io/anime/{id}
* https://myanimelist.net/anime/{id}
* https://notify.moe/anime/{id}
* https://www.thetvdb.com/dereferrer/series/{id}
* https://www.thetvdb.com/dereferrer/movie/{id}
* https://www.themoviedb.org/movie/{id}
* https://www.themoviedb.org/tv/{id}

---
**NOTE**

Both TheTVDB and TheMovieDB share IDs across Movies and TV-Shows.

Requesting ID 25 from TheMovieDB with endpoint TV will return "[Star Wars Droids](https://www.themoviedb.org/tv/25)"
while ID 25 from the movie endpoint will return "[Jarhead](https://www.themoviedb.org/movie/25-jarhead)"

You would have to make sure that you use the correct endpoint depending on what kind of media you have.

---

### Generation and Corrections

The anime-lists-generator Project only reduces the anime-offline-database so that only the IDs are available.

In regards to the ScudLee anime-lists, this is, however, not fully the case.
The anime-lists provided includes some unwanted information (like TheTVDB ID being set to "movie" etc) which are just not viable for a list of mappings.
For that reason, the generator will lookup the missing IDs for TheMovieDB, TheTVDB and IMDB, depending on the available IDs.

Those lookups happen through the TheMovieDB API endpoints for external IDs and/or by searching for such an external ID.

This means that this project cannot provide a way for corrections anymore and that corrections have to be brought to those source projects.
As for completely missing IDs, those should be added to TheMovieDB instead so that the generator is able to find those IDs and add them to the list the next time they are generated.
