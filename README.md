# anime-lists

Repository to store and provide the title and mapping lists of the website http://myanimelist.net and the corresponding mapping to https://www.themoviedb.org and https://www.thetvdb.com.

### anime-lists/anime-mapping/
contains the lists of mappings between Myanimelist, TheMovieDB and TheTVDB

Note: this list only contains available entries so for example the myanimelist with ID 2 is not available and therefore there is no mapping for it available.

The mapping can be validated manually by using the following links (replace the <XY_id> with the appropriate entry in the mapping file)

Myanimelist.net 
```https://myanimelist.net/anime/<mal_id>```

TheTVDB.com 
```http://thetvdb.com/?tab=series&id=<thetvdb_id>```

TheMovieDB.org
```https://www.themoviedb.org/movie/<themoviedb_id>```

The Mapping list can be merged into the full list with the following command (the full file shouldn't be created in the same folder to prevent merging the files twice):

```jq -s '[.[][]]|sort_by(.mal_id)' animeMapping_* > ../animeMapping_full.json```

### anime-lists/anime-titles/
contains the available titles (japanese, synonyms, english) all IDs on myanimelist.

Note: this list also contains unavailable entries!

### Report wrong mapping
Since I can't check all those entries by myself I require some help to do it. If you find a mapping that is wrong please report it [here](https://github.com/Fribb/anime-lists/issues)