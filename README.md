# Sample_Shop_App
Beta version of shop app made for owner "Kwesi James"

_Usernames and Passwords for all relevant accounts_

_Usernames |  Passwords_
admin|adminkj
yuh|yuh
bruh|bruh
test1|password
(You can create your own accounts)



_Methodology_


MainActivity.java and activity_main.xml
MainActivity.java serves as the main entry point into the app, handling user interactions for signing in and signing up. 
UI elements such as EditText for username and password, and Button for login and signup, are initialized on the OnCreate method, as well as an instance of the DBHelper class is also created for database operations.
SharedPreferences are used to store the current session of the username of the user that logged in, into MyPrefs to be used for table Basket later.
  
The method for btnLogin is a large if-statement with checkuserpass method from DBHelper, with comprehensive checks if the user’s credentials are not admin as well as comparing to the other accounts in the database for a valid login, passing through and the username in “editor.apply()” to MyPrefs and displaying successful login Toast messages after taking the user to HomeActivity, (or if admin credentials are valid, to AdminActivity instead).
 
Btnsignup takes the user to RegisterActivity for the user to make a new account.

 
RegisterActivity.java and activity_register.xml
The layout for activity_register.xml is like activity_main, except with more edittexts to allow more input of data.

For RegisterActivity.java, is in functionality very similar to MainActivity except gathering more info from the user, as well as passing the users details into the Users table in Database.db using DBHelper.

The signup button's event listener performs several checks and validations. It verifies if all the required fields are filled, displays appropriate toast messages if any fields are empty or if the entered email address is invalid. It then checks if the entered password and its confirmation match. It uses the DBHelper class to check if the username already exists in the database and inserts the user's information if all checks pass. If the registration is successful, it displays a success message and starts the HomeActivity. Otherwise, it shows messages for failed registration or an existing user.
The signin button, when clicked, starts the MainActivity too.

 
Methods for checking credentials stored in DBHelper.
DBHelper.java
DBHelper.java is the backbone of the app being useful for:

Database Creation and Upgrade: The onCreate method is responsible for creating the necessary database tables when the database is initially created. It defines the structure of tables for Users, Products, and Basket. 
 
Data Manipulation Methods: The insertData method inserts user registration information into the Users table, while the insertProduct method inserts product information into the Products table. These methods use the SQLiteDatabase class to perform database operations efficiently and securely. The updateProduct method updates the quantity of a specific product in the Products table. These methods are crucial for managing user data and product inventory within the application.
 
Data Retrieval Methods: The getData method retrieves all data from the Products table, while other methods such as getElectronicsData, getFashionData, and so on, retrieve specific subsets of data based on different categories, which are going to be used in the ShopAdapter class. These methods allow for efficient querying and retrieval of data from the database, enabling the application to present relevant information to the user.
Not only that, the getCurrentDateTime method returns the current date and time in a specific format, allowing me to add timestamps to database records, tracking registration dates, and keeping the database up to date.


AdminActivity.java, activity_admin.xml, ListAdmin.java, activity_list_admin.xml, and productcard.xml   
AdminActivity allows the admin to insert new products into the database by providing relevant information. Whilst the DBHelper class is used to facilitate database operations. The view button starts a new activity ListAdmin to display the list of products. 
The insert button performs the following actions when clicked:

•	Retrieves the entered values from the EditText fields.

•	Formats the current date and time using SimpleDateFormat.

•	Checks if the entered category is allowed by comparing it against a predefined array of valid categories.
 
•	If the category is allowed, it calls the insertProduct method of the DBHelper class to insert the product information into the database. It also displays a toast message indicating the success or failure of the data insertion.
 
DBHelper insertProduct() 1
•	If the category is not allowed, it provides an error message
 

ListAdmin.java is the class that is responsible for displaying the RecyclerView for all the products, that can be seen when clicking on the VIEW DATA button, by working with DBHelper and ShopAdapter
Activity_list_admin.xml contains the recyclerview, and it also uses Productcard.xml for styling and containing the textviews for each cardview in the recyclerview.
 
The displayData() method retrieves the product data from the database using the getData() method of the DBHelper class. If there are no entries in the database, a toast message is displayed to indicate that no products exist. Otherwise, the retrieved data is iterated through using a cursor, and the relevant information is added to the corresponding ArrayList objects.
 
HomeActivity.java, activity_home.xml
HomeActivity is responsible for being the activity that displays the three fragments in this project: BasketFragment, ShopFragment, and ProfileFragment.

The code first retrieves the username from the shared preferences using the SharedPreferences object, then the stored username is displayed in a toast message to provide a personalized welcome message to the user.
 
The onBackPressed method is overridden to prevent accidental logout. When the user presses the back button, a toast message is displayed indicating that they cannot exit at that time and should either logout or close the app.
 
The layout has been customized to have tabber-like buttons.

ShopAdapter.java, and dialog_add_to_basket.xml
ShopAdapte.java is responsible for populating the RecyclerViews in both ListAdmin and ShopFragment.java with product data. It also includes a dialog for adding items to the basket.

The ShopAdapter class extends RecyclerView.Adapter and is responsible for managing the data and views in the RecyclerView. It receives various lists of product information and initializes the necessary variables, such as the Context, product lists, and DBHelper.

The adapter also allows the recyclerview cards to be clicked; when an item is clicked, it triggers the showDialog method, which displays an AlertDialog with the option to add the item to the basket.

The showDialog method builds and displays the dialog using an AlertDialog.Builder. Allowing the user to add or subtract the item quantity using buttons. Upon clicking the "Add" button, it retrieves the selected product name, retail price, and the user's username from shared preferences. It then calls the insertBasketData method of the DBHelper class to insert the item into the basket table in the database.


ShopFragment.java, BasketFragment.java, ProfileFragment.java, and Layouts
The ShopFragment class utilizes separate RecyclerView instances and ShopAdapter objects to display product data for various categories. It retrieves data from a database using a DBHelper instance and populates category-specific lists with the help of Cursor objects. Each RecyclerView is associated with a specific category and its respective adapter. The fragment sets up and configures the RecyclerView and adapter instances for each category, allowing the products to be displayed appropriately in the UI.

BasketFragment has not been fully implemented; ideally I would have liked to populate a recyclerview of the Basket table in basketfragment and proceed to checking out, however my organization skills has been lacking.

ProfileFragment contains an intent button for the user to click and log out of the application. One thing to note is that every time a user logs in, the MyPrefs file always updates to the logged in user too.

The layouts for BasketFragment consisted of textviews of category names, and under each dedicated category name, is the respective recyclerview displaying the product cards of that specific category.


_Blackbox Testing_
Input	Expected Output	Actual Output	Screenshot
Duplicate Entries in Products	Add extra in quantity of that duplicate	Duplicated “Bat” and it incremented the quantity in the database, from 110, to 220 (110+110)	 
Basket quantity negative numbers	Prevention in going to negatives	Prevention in going to negatives	 
Test with an empty database.	Functional insertion	Functional insertion	-
Test the limit length of username	Should allow inf username length	Still functional after typing in 1234567891011121314151617181920	 
Test admin credentials in RegisterActivity	Allow admin login	allows a user to create admin which is problematic and crashes the login	 
Admin UI testing	Functional	ListAdmin can still add to basket since it is still connected by the adapters, so admin can bug and add to basket and it shows up as an empty field in the username of Basket in Database.db	  


_Known bugs in the App_
•	ListAdmin can still add to basket since it is still connected by the adapters, so admin can bug and add to basket and it shows up as an empty field in the username of Basket in Database.db
•	allows a user to create admin which is problematic and crashes the login
•	not implemented the code for RegisterActivity so that it doesn’t upload the username into SharedPreferences, unlike the login.
