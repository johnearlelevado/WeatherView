# WeatherView app

### Download Here
https://drive.google.com/file/d/1pC6XIh6cl_PIoVDvnqleo5yY5PKaJIiV/view?usp=sharing

### Limitations
  - I used open `https://openweathermap.org/api` API to populate the app. Unfortunately, free forecasts is only for 5 days not 10 days
  - I used Volley networking library since we cannot use third party libraries like Retrofit
  - Note that the API gives forecasts of 3 hour intervals for 5 days only so the list is divided into hours and days. The hours data can't be condensed into days. Doing so would malrepresent the correct weather data for that day

### Tech stack
  - Dagger Hilt
  - View Binding
  - MVVM Pattern
  - ViewModel
  - LiveData
  - Android Volley
  - Android Room DB

### Instrumentation Unit Test
  - AppUtil Test

### Screenshots

![Screen Shot 2021-09-28 at 1 55 43 AM](https://user-images.githubusercontent.com/6505692/134960445-aee16646-4441-480f-84e2-b8c7ed33a24f.png)
![Screen Shot 2021-09-28 at 1 56 12 AM](https://user-images.githubusercontent.com/6505692/134960469-0597a75d-fe79-4833-ba2b-96bae1cb48df.png)

