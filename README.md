# Password Manager

This Application stores and manages users usernames and password information of the
different accounts. This application will help the user remember different usernames and
passwords for accounts on the internet in the secure encrypted database.Furthermore,
users allow adding, edit, and delete accounts details using this tool.
The encrypted Password Manager tool saves all the details will be saved in the
database in encrypted format.

## Running the application

The project is a standard Maven project.AfterRunning the application then, open http://localhost:8080 in your browser.
To run this application, install Nod.js.


## Project structure

- `MainLayout.java` in `src/main/java` contains the navigation setup (i.e., the
  side/top bar and the main menu). This setup uses
  [App Layout](https://vaadin.com/components/vaadin-app-layout).
- `views` package in `src/main/java` contains the server-side Java views of your application.
- `views` folder in `frontend/` contains the client-side JavaScript views of your application.
- `themes` folder in `frontend/` contains the custom CSS styles.
##**How to run this project:**
>The main root of the project is src/pm/main. Once you run the Main class in side the src/pm/main ot will ask you the following:
>
> test cases availble inside tests folder.

### Login window
To run this application, open http://localhost:8080 in your browser and user will first see the login window; then, they can type their username and master password to log in or register as a new user. Example  of Log in window given below.

![](../../../../../var/folders/32/_8rs7y051w9b3b31pbvx_8dc0000gp/T/TemporaryItems/NSIRD_screencaptureui_eo9gFF/Screen Shot 2022-02-09 at 6.11.29 PM.png)

###Register window
 Register as a  new user with a username and password.
![](../../../../../var/folders/32/_8rs7y051w9b3b31pbvx_8dc0000gp/T/TemporaryItems/NSIRD_screencaptureui_mcLPx4/Screen Shot 2022-02-09 at 5.57.45 PM.png)

###Main window
After logging in, you will reach the main window where all the passwords are stored. 

![](../../../../../var/folders/32/_8rs7y051w9b3b31pbvx_8dc0000gp/T/TemporaryItems/NSIRD_screencaptureui_Om91W1/Screen Shot 2022-02-09 at 6.06.26 PM.png)

### Add Password 
When you want to add a new password, Click on the `Add Password` button, and the right side layout is shown. After adding a new password is finished, the list will be updated. Click on the `Delete` button to delete the password item, and to log out, click on the `Logout` Button.

![](../../../../../var/folders/32/_8rs7y051w9b3b31pbvx_8dc0000gp/T/TemporaryItems/NSIRD_screencaptureui_xQShrL/Screen Shot 2022-02-09 at 6.05.41 PM.png)