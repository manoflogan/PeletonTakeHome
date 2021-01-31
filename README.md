__PELETON__

* Fetch the latest weather for Peloton HQ and display it to the user.
*   We will be using the MetaWeather API to retrieve forecast data: https://www.metaweather.com/api/
* Use the metaweather API to search for Peloton HQs location via the https://www.metaweather.com/api/location/search/?query=new+york endpoint
* Response is in JSON, an array of locations
* Each location will contain the woeid to be used in the next leg of requests
* Use the metaweather API to look up the weather for next week via https://www.metaweather.com/api/location/[woe_id]/ endpoint
* Response contains a consolidated_weather field, holding the weather for a location
consolidated_weather is an array of entries where each has:
    - applicable_date - the date for the forecast as an ISO date
    - min_temp - the low temp for the day in centigrade
    - max_temp - the high temp for the day in centigrade
* Days of forecast in the response may not be returned in order (may need sorting)
* Once data is fetched and sorted, display the next 7 days of forecasted data as a scrollable list
* Each day should show a title with the day formatted as: Monday, Feb 22nd. For the current day, the title should show Today
* For each day displayed, show the min/max temperatures side by side horizontally

Optional: show an indicator that the data is being fetched

Optional: provide a way to refresh data on demand