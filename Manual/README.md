# Manual

This folder could contain information and screenshot on how to use the program.  If following this, we will get
an idea of what elements you have in the GUI, and to fully use it. Feel free to add additional files and link them. 

## Introduction

The Free Game Library Application allows users to look up free games information, list all stored records, and export the records in various formats. This manual will guide through main features, and how to use the application effectively.

## Running Program

After running MainApp.java, the window of `Free Game Library` will show up. 

## Main Features

This application contains four parts - input box, functionality buttons, display area and file (save/load) buttons. 
![Screenshot 2024-08-04 185025](https://github.com/user-attachments/assets/b3188be2-6ea1-4b8c-9f88-c79770944596)

### Search Button
On the top of the window, there is a box for user to type for searching and filtering. After typing the game name, click the `Search` button to search the information about the game and the result will be printed on the display area below buttons. All the game information are obtained through https://www.freetogame.com/api.

If the game is not available through the api, "No game found" will be printed on the display area.
![Screenshot 2024-08-04 185622](https://github.com/user-attachments/assets/d18c427e-1f03-4386-bc5e-e7cd96534070)

If the game exists in the api database, then the game information will be printed on the display area, and the default format is JSON.
![Screenshot 2024-08-04 185802](https://github.com/user-attachments/assets/6ed500b4-08f5-498d-a7f9-e13757f4cb64)

### Add and Add All Buttons

Add button locates on the first row of the buttons while Add All is on the second row. The difference between `Add` and `Add All` is that `Add` handles the situations when the user only needs to add a single game item (per time) to the game, while `Add All` will add all free games that are retrieved from the api database. 

![Screenshot 2024-08-04 190159](https://github.com/user-attachments/assets/eb8ae186-7352-4fe3-b57b-c74638053282)

For example, if the user obtains all PC-based free games, and only needs to add one game, such as `Age of Wushu`, then the user needs to click `Add` button instead of `Add All`. Then the user can type in the name of game, in this case, `Age of Wushu`, in the pop-up window and click `OK`.
![Screenshot 2024-08-04 190712](https://github.com/user-attachments/assets/ce9ec479-e622-49f4-b6f5-db8e01f4f095)
Then the information of `Age of Wushu` would be added to the game list.

![Screenshot 2024-08-04 190959](https://github.com/user-attachments/assets/e6e6aa77-97e3-4934-8f3c-10164370227e)

### Export List

`Export List` button exports the updated database file to a specified path. File name refers to the file that's exported. The path can be navigated in the window. When the path is chosen, click save to export the file.

![Screenshot 2024-07-13 100654](https://github.com/user-attachments/assets/56161f7b-acf6-4aa5-91df-0ecbe4196fb1)