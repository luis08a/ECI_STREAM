

# ECI_STREAM
## ARSW 2019-1 Project

  

### Team:
| Name | Role |
|--|--|
| Luis Moreno | Developer Backend |
| Nicolas Cardenas | Developer Frondend |
| Andres Villamil | Developer Backend |

### ECI Stream:
In this project we want to create a new educational tool which allows its users to share information in real time via streaming, where there is a teacher that has a role as a leader which can share its screen as the main screen for the members of the stream (students), and everybody in the stream can choose from a pool the screen of another member as a secondary, this would be divided in private or public streams where the private would have password, or a free entry.

  

### Purpose:

-   Have a real time educational tool which that allows multiple student-teacher communication without necessarily being in person.
    

### Problem:

-   Because of the transport infrastructure of Bogota, or personal events, the time to get to the classroom may be lost or it’s impossible to attend to class, with this tool we want to give the opportunity of a virtual class where there is a reliable student-teacher interaction, facilitating the information access and education to more people.
    

### Method:

-   The method that will allow us to achieve the goal is implementing sound and video streaming service where each person in a particular party can share its screen in order to show to others members what he or she is doing in that moment. Of course is necessary the presence of a teacher or master member that controls the meeting.
    

### Functionality

First the user will register in the system to create an account, where he will provide data as username password and an email.
When the user tries to enter some page, if he isn’t logged in, he will be automatically redirected to a login page with the user and (hiden)password fields to be fill, then the data will be compared with if the data provided by the user is wrong he can try as many times as the user wants.
When the given data (username and password) matches, the user will be redirected to the main page of the application, there he will be able to see some of the current on going streams and a button to go to the user profile.
All users can create a room and initiate an stream, the user that create the stream assumes the role of teacher or educator for that specific room and every one that start to watch the stream plays student role. For confidence purposes just the users that have certified that they are teachers, in real life, will appear a distinct sign in their profile that identifies them as a teachers.
Before starting the stream the user must write a specific information for the stream. A title and a category for the stream, keywords about the content of the stream, a brief resume of it and if he wants to put a password to access it.
When starting the stream there will be buttons to turn on/off the microphone and other for the pc sounds there will be also shown how many people are seeing the stream and a button to terminate it, that when pushed will end the stream and return the teacher to the main page.
Any user will be to search for any stream with a search bar, the search parameters will be the key words, after clicking on the search button it will redirect the user to another page where the results of the search will be shown and by clicking on the image of the stream the user will be able to enter it, if it’s a private stream the user will be required to write the password to join it.
Inside the stream the user will be able to mute the stream or hide the chat, the user will be able to chat with the viewers of the stream with the chat the user will be able to left the stream by closing the tab or going back in the browser.
In the profile tab will be shown the main information of the user and a photo if the user uploads one.
In all the tabs there will be the logout button which will close this instance of the account and redirect to the login page.
In order to make a great user experience the user also will be able to follow other users and rate the job done for other user.

### Demo 
- View of index page <br>
![index](/images/index.png)
- View of login page <br>
![login](/images/login.png)
- View of register page <br>
![register](/images/register.png)
- View of main page <br>
![main](/images/main.png)
- View of creat room page <br>
![createRoom](/images/createRoom.png)
- View of room page <br>
![room](/images/room.png)

### User stories:

[https://tree.taiga.io/project/luis08a-arsw-2019-1-eci-stream/backlog](https://tree.taiga.io/project/luis08a-arsw-2019-1-eci-stream/backlog)

### Installation:
This application needs installed:
* Java
* Tomcat
* Spring 