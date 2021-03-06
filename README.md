# Fetch Rewards Project #1 (Scrollable Text View)


### User Stories

- [x] Display all the items grouped by "listId"
- [x] Sort the results first by "listId" then by "name" when displaying.
- [x] Filter out any items where "name" is blank or null.

### App Walkthough GIF

<img src="https://i.imgur.com/6TlnttR.gif" width=250><br>


### Notes/Problems
- Tried using a RecyclerView for the Project.
    - I swapped to a scrollable text view instead
    - im going to upload the RecyclerView code into another respository here: (https://github.com/Kariizma/Fetch-Project--2--RecyclerView-)
- Put some of the Retrofit Logic in the Main activity, when it should be a seperate file
- Had some trouble modifying/removing nulls and blank strings from the list
    - I removed the nulls using an iterator
    - I did a simple check to not display empty strings.

## Open-source libraries used
- [Retrofit 2](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java
- [Retrofit Gson Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) - Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object. 
