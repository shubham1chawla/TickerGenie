# TickerGenie
This project aimed to have a view for different stock tickers registered on Bombay Stock Exchange. The project leaveraged Quandl APIs for getting data related to the tickers listed on the web UI.

## Video Presentation
<a href="http://www.youtube.com/watch?feature=player_embedded&v=lUSrEZ18VEk" target="_blank"><img src="http://img.youtube.com/vi/lUSrEZ18VEk/0.jpg" alt="IMAGE ALT TEXT HERE" width="320" height="240" border="10" /></a>

## Workkflow
![workflow](https://user-images.githubusercontent.com/31181262/55422669-cbdb1280-5599-11e9-9350-fd283dffd9d8.png)

## How to use
- Download zip or clone the repository in any desired folder.
- Download Consul Agent from [here](https://www.consul.io/downloads.html).
- Place the `consul.exe` file in the main project folder.
- Start the Consul agent by opening terminal/cmd in the main folder and running this script.
`consul agent -server -bootstrap-expect=1 -data-dir=consul-data -ui -bind=<ENTER YOUR IP>` (You could refer this [link](https://howtodoinjava.com/spring-cloud/consul-service-registration-discovery/).)
- Once you have started the consul service, import `TickerFetchService` and `QuandlFetchService` on your Eclipse or Intellij workspace and let them resolve `pom` for dependencies.
- **Important** Inside `QuandlFetchService` project go to `application.properties` and enter your Quandl API key.
- Once both the projects are up and running, make sure you go to your [localhost:8500](http://localhost:8500/ui/dc1/services) and check both services are registered on consul successfully.
- After registering both the services, set up the angular project `TickerGenieWeb` in the main project by first installing the node modules.
- Build `TickerGenieWeb` using `ng serve` and navigate to [localhost:4200](http://localhost:4200/login). You will be greeted with a login page. Use username as *shubham* and password as *1234*.
- If you are logged in succesfully, You have succesfully setup the project.
