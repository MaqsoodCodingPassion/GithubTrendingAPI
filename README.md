# Github TrendingAPI

-  This app fetch the trending repositories from the provided public API and display it to the
users.
- While the data is being fetched, the app shows a loading state in shimmer animation.
- If the app is not able to fetch the data, then it shows an error state to the user with an
option to retry again.
- All the items in the list be in their collapsed state by default and can be expanded on
being tapped.
- The app 100% offline support
- The cached data valid for a duration of 2 hour. After that the app attempt
to refresh the data from remote and purge the cache only after successful data fetch.
- The app is having a pull-to-refresh option to the user to force fetch data from remote.

This app developed in Kotlin with MVVM architecture by using components are :
- Dagger
- Live data
- Room
- View model
- Rx java
- Retrofit

Testing :
- Junit  
- Espresso

Stetho library - for debugging API request/response and DB


![Alt Text](https://i.imgur.com/M8RAdNN.png)
![Alt Text](https://i.imgur.com/Az03BmA.png)
![Alt Text](https://i.imgur.com/0XIrbHq.png)
![Alt Text](https://i.imgur.com/u07U3Br.png)

