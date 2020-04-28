# Stock Price Checker (Android App)

*Device Name: Nexus 7; by Guangda Zhu, Siteng Zhang*

*HELLO TO STOCK LOVERS, our app aims at providing a platform for users to find stocks they like and save information about stocks. Users can register their emails to get updates on the stocks based on their preferences. They will be able to save stock they want to continue to observe, and all the stocks they have saved up should be similar to the bucket list created for our android mini-app with each stock being an item in the list.*

![alt text](https://github.com/adazhu365/Stock-Price-Checker/blob/master/wireframe-1.png 'WireFrame')


**WHY Android:**

1. More precise sqlite control. We intended to use sqlite to store all the saved stocks from the user, therefore, a precise control is needed to avoid unintended consequences. IOS have an abstraction layer called CoreData which does not allow very precise controls. 
  
2. With our type of free app that doesn’t require the newest version of android or high-power processor, it makes more sense to open it to more potential users, and android have much more user compared to Ios. 

3. Only one person in the group has an ios device, and MacinCloud has proven to be very slow and somewhat costly to use. 


**Major Feature/Screen:**

**API call with Retrofit:** By consuming a presumed API services called retrofit, we are able to pull  multiple market statistics such as *highest price, lowest price* of the stocks users search.Web Services Consumption. Can load relevant statistics about Stock company from Intrinio.API. Please type in Stock name not company name!!

    * Go to the main page as normal
    * In the only input field, type in “AAPL”
    * Click on detailed info button
    * New page has information about the apple stock pulled from an api online. 

**Data Storage using SQLite:** For all stocks users search and favorite, we save them using SQLite data storage. Users can freely add and remove stocks from this list, and all the changes will be stored in the local database.

    * In the main screen, type FB in to the stock name input.
    * Click on detailed info
    * Click on “add to favorite”
    * The main list should have the FB stock added. 
    * Quit and reopen the app.
    * The main list should still have the newly added stock. 
    
**Data Storage using Sharedpreferences:** User can enter their email address, which will be saved in sharedpreferences using key-value pair, and used when user want a summary of the list of favorite stocks, as it will put the email entered as the receiver of such summary.

    * The above section also detailed a usage of sharedprefernece, automatically bringing the saved email address as the receiver of the email.
    * Also, go to the main page as normal
    * Click on setting button in the top right
    * Click on “general”
    * Click on “font size”
    * Choose a font size of small or Huge for clarity.
    * Go back to the main page, the list should have a different font size. This is achieved using sharedpreference. 

We have a settings page which allows user to adjust font size of the app using sharedpreferences. The main adjustment is for the list of favorite stocks and the detailed info page. This allows user to adjust the app according to their need and have accessibility benefits. 

**Data Storage using File read write & Email:**

    * Enter your email address in the first screen, and click “save email”.
    * Click continue to the main screen.
    * Click on “email summary” on the bottom.
    * If needed, Google may ask to setup an account.
    * Then a email draft screen should pop up, with receiver being the email entered. (EMAIL)
    * It should have an attachment, which is written about the summary of the list of favorite stocks. (FILE WRITE)
    
    

**Testing Methodology:**

We tested our app in the emulator and using nexus 7 to make sure that everything works right. We tried typing in wrong things on purpose to make sure bad input doesn’t break the app. We also tried deleting all stock from the list, to test if it can handle having nothing in the sqlite database. We have also switched orientation of the emulator/tablet itself to make sure the app still works in landscape. 

**Usage:**

We intended this app to be used by mostly casual users that does not care about fancy graphs or graphics. We wish to provide a simple experience for the user, making them only a few click away from looking at stock information for all the stock they want. It is really easy to use and doesn’t require any login information. However, if the email field in the first page is not saved in the sharedpreference, the email summary functionality won’t work as well, as the recipient of the email field is auto-filled with the email information from sharedpreference. 

**Lesson Learned:**

The biggest lesson we learned is probably that it is really hard to have a good looking UI. In ios, doing storyboard is simply drag and drop most of the time. Although android layout have similar functionalities, it is a lot more strict on position and constraints. Therefore, making a good looking UI is rather difficult. 
	

