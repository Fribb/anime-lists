# anime-lists

This repository is to store and provide the mapping between various anime sources like

* [MyAnimeList](https://myanimelist.net/)
* [TheTVDB](https://thetvdb.com/)
* [TheMovieDB](https://www.themoviedb.org/)
* [Anime-Planet](https://www.anime-planet.com/)
* [Kitsu.io](https://kitsu.io/)
* [Anisearch](https://www.anisearch.com/)
* [notify.moe](https://notify.moe/)
* [anilist.co](https://anilist.co/)
* [anidb.net](https://anidb.net/)
* [livechart.me](https://www.livechart.me/)

## lists

The lists are generated through the [anime-lists-generator](https://github.com/Fribb/anime-lists-generator) based on a couple of already existing lists and mappings from other repositories.

1. [anime-offline-database](https://github.com/manami-project/anime-offline-database/)
2. [ScudLee anime-lists](https://github.com/ScudLee/anime-lists/)

### anime-offline-database-reduced

This file is is the reduced version of the anime-offline-database to only include the necessary IDs.

Example:

```
   {
      "livechart_id":4721,
      "anime-planet_id":"hack-g-u-trilogy",
      "anisearch_id":4491,
      "anidb_id":5459,
      "kitsu_id":2895,
      "mal_id":3269,
      "notify.moe_id":"z5OQcKiiR",
      "anilist_id":3269
   },
```

### anime-lists-reduced

This file is the reduced version of the ScudLee anime-lists project also only including the necessary IDs.

Example:

```
   {
      "thetvdb_id":79099,
      "imdb_id":"tt1164545",
      "themoviedb_id":8864,
      "anidb_id":5459
   }
```

### anime-lists-full

This file will be merged through a JQ statement by merging the elements over the "anidb_id".

```
jq -s 'flatten | group_by(.anidb_id) | map(if .[0] | has("anidb_id") then reduce .[] as $x ({}; . * $x) else .[] end)' "<path_to_the_anime-offline-database-reduced.json>" "<path_to_the_anime-lists-reduced.json>" > "<path_to_the_anime-list-full.json>"
```

the result of this is the following:

```
  {
      "livechart_id": 4721,
      "anime-planet_id": "hack-g-u-trilogy",
      "anisearch_id": 4491,
      "anidb_id": 5459,
      "kitsu_id": 2895,
      "mal_id": 3269,
      "notify.moe_id": "z5OQcKiiR",
      "anilist_id": 3269,
      "thetvdb_id": 79099,
      "imdb_id": "tt1164545",
      "themoviedb_id": 8864
  },
```

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
As for completely missing IDs those should be added to TheMovieDB instead so that the generator is able to find those IDs and add them to the list the next time they are generated.