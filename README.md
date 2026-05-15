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

This file is the reduced version of the anime-offline-database to only include the necessary IDs.

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

This file is the reduced version of the Anime-Lists anime-lists project, also only including the necessary IDs.

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

### Collections

The Collections folder contains the JSON files for the Collections for each Source (mal, tvdb, anibd, etc).
This Collection is generated based on the "relatedAnime" Element from the `anime-offline-database` and should only contain bidirectional related Anime.
This means that a Collection should only contain Anime that reference each other. This is to prevent Anime that have a relation to another Anime (possibly through a crossover episode) that would produce a huge Collection.

A collection looks like this (example from AniDB):
```json
{
  "name" : "Seikai no Monshou",
  "ids" : [ 1, 4, 5, 6, 884, 1623, 2673 ]
},
```

### Indices

The indices folder contains the JSON files for the different sources (mal, tmdb, tvdb, etc). This is to speed up the lookup process for an ID without having to iterate through the anime-lists array and check each element for that ID.
The Index file will contain Objects with the Key being the ID of the Anime for that source. The value of that Key will then contain two arrays, `anime-list` and `collection`, with the index where that ID can be found in those files.

An example, for the AniDB ID 1:
```json
  "1" : {
    "anime-list" : [ 0 ],
    "collection" : [ 0 ]
  },
```
So, the AniDB ID `1` can be found in the `anime-list-full.json` at position 0:
```json
{
  "type" : "TV",
  "anidb_id" : 1,
  "anilist_id" : 290,
  "animecountdown_id" : 36462,
  "animenewsnetwork_id" : 14,
  "anime-planet_id" : "crest-of-the-stars",
  "anisearch_id" : 3039,
  "imdb_id" : "tt0286390",
  "kitsu_id" : 265,
  "livechart_id" : 4157,
  "mal_id" : 290,
  "simkl_id" : 36462,
  "themoviedb_id" : 26209,
  "tvdb_id" : 72025,
  "season" : {
    "tvdb" : 1,
    "tmdb" : 1
  }
}
```
And the collection for that ID would be in the `anidb_collection.json` also at position 0:
```json
{
  "name" : "Seikai no Monshou",
  "ids" : [ 1, 4, 5, 6, 884, 1623, 2673 ]
}
```

### anime-list-mini

Same as `anime-list-full` but minified (no new-lines nor indenting)

To use the IDs for requests on the websites, the following "endpoints" can be used by replacing the {id} part in the URL:

* https://anidb.net/anime/{id}
* https://anilist.co/anime/{id}
* https://anime-planet.com/anime/{id}
* https://anisearch.com/anime/{id}
* https://kitsu.io/anime/{id}
* https://myanimelist.net/anime/{id}
* https://www.thetvdb.com/dereferrer/series/{id}
* https://www.thetvdb.com/dereferrer/movie/{id}
* https://www.themoviedb.org/movie/{id}
* https://www.themoviedb.org/tv/{id}
* https://www.animenewsnetwork.com/encyclopedia/anime.php?id={id}

---
**NOTE**

Both TheTVDB and TheMovieDB share IDs across Movies and TV-Shows.

Requesting ID 25 from TheMovieDB with endpoint TV will return "[Star Wars Droids](https://www.themoviedb.org/tv/25)"
while ID 25 from the movie endpoint will return "[Jarhead](https://www.themoviedb.org/movie/25-jarhead)"

You would have to make sure that you use the correct endpoint depending on what kind of media you have.

---

### Generation and Corrections

Lists above are generated through the [anime-lists-generator](https://github.com/Fribb/anime-lists-generator).
The generator will get the [anime-offline-database](https://github.com/manami-project/anime-offline-database/) and the [anime-lists](https://github.com/Anime-Lists/anime-lists/) projects and clean them up into a JSON format. Especially the anime-lists project has a lot of information that is not viable for a mapping project (like the TheTVDB ID set to "movie", etc.).

Those resulting JSON files (`*-reduced.json`) are then merged based on the AniDB ID, since that is the most common denominator of both lists.

Each of those elements is then checked to see if they have a TMDB, TVDB and IMDB ID. If any of them are missing, the IDs are looked up on TheMovieDB API search or external ID endpoints, depending on which ID is available.

Finally, the Index and Collection files are generated based on the resulting anime-list file.

What this means is that this project cannot provide a way for corrections anymore, and that corrections have to be brought to those source projects.
As for completely missing IDs, those should be added to TheMovieDB instead so that the generator is able to find those IDs and add them to the list the next time they are generated.

