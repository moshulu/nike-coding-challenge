# nike-coding-challenge
Coding challenge assigned to me as an interview task by Nike, Inc.
 
 ### Original Instructions
 ```
 Create a sample Android app that displays the top albums across all genres using Apple's RSS generator found here: https://rss.itunes.apple.com/en-us.
  The app will need to target android Pie with the minimum Api of Nougat.
  Limit the external dependencies to only AndroidX 1.+, Material Design Components, and Glide or Picasso for Image Processing.

Expected behavior:
  On launch, the user should see a RecyclerView showing one album per item. Each item should display the name of the album, the artist, and the album art (thumbnail image).
  Tapping on an item will launch a new activity where we see a larger image at the top of the screen and the same information that was shown in the item view, plus genre, release date, and copyright info below the image. A button should also be included on this detailed activity that when tapped fast app switches to the album page in either the Apple Music app — if installed on the phone — or a browser. The button should be centered horizontally and pinned20 dp from the bottom of the view and 20dp from the leading and trailing edges of the view. The detail activity is expected to be a single flat ViewGroup.

Bonus: An accompaning Espresso test mimicking a golden path (select 1st element, view its contents, back press, select 2nd element..)
```

## Intrepretation

##### Getting the data

The original instructions only noted that the Android application must show the top albums across all genres using Apple's RSS generator. I chose to limit the query to include only the top 10 albums across all genres, and return it in JSON format. The url I used:

```

https://rss.itunes.apple.com/api/v1/us/apple-music/top-albums/all/10/explicit.json

```

##### API

The original instructions noted that I must "target android Pie with the minimum Api of Nougat," meaning I must target between API 24 and API 28. 

##### Expected Behavior

The initial view must show a RecyclerView with album artwork, artist and album title. Tapping on a RecyclerView item will present the user with a seperate activity that shows the respective data, now including:

- Artwork (larger than initial thumbnail)
- Album Title
- Artist
- Genre(s)
- Release date
- Copyright information
- Button that leads the user to Apple Music album page, if Apple Music is installed. Opens Apple Music webpage in default browser if Apple music is not installed. The button needs to be centered horizontally and pinned 20dp from the bottom, left, and right of the view.

The details of the album must be in a single ViewGroup. I chose to put it in a ConstraintLayout, for ease of creating a responsive design on different phones.

##### Espresso Testing

I created an Espresso Test, called 'GoldenRuleTest' that clicks on each of the RecyclerView items in AlbumActivity (which shows AlbumDetailActivity), and backs out of it. GoldenRuleTest is designed so that it can apply to any number of RecyclerView items in AlbumActivity, if the URL were to change from 10 items to, say, 25.

##### Picasso

The application uses Picasso's external libraries to load images.

## Installation and Execution

(Note: these instructions are for Android Studio. If you're installing on Eclipse, check out [this](https://stackoverflow.com/questions/24462452/how-to-import-eclipse-project-from-git-to-android-studio).

To install, close all existing projects in Android Studio.

On the launcher, click 'Check out project from Version Control' > 'Git'

In the URL, paste https://github.com/moshulu/nike-coding-challenge/. Choose the directory you want to save this project in. Click 'Clone'

The project should automatically clone into your IDE, and start syncing.

### Things I want to improve on in upcoming iterations
- Easier passing data between activies. I used ArrayList<Map<String, Any>> for my ease, but I think I can do better.
- Asking questions about the requirements in order to give the customer a better product. I did this project on the weekend, and was unable to contact the requirement-givers over the weekend. I strongly believe that if I were working closely with a customer, I wouldn't run into this problem at all.
- Enabling of KDoc for documentation. Look it up (here)[https://kotlinlang.org/docs/reference/kotlin-doc.html]
- The UI design
- Better testing across a larger variety of phones to test responsiveness of UI
- Figuring out edge cases across a larger variety of phones

## Expected output
![alt text](https://imgur.com/VksEyUo "AlbumActivity Pixel 2 API 28")
![alt text](https://imgur.com/xcP8Tbi "AlbumDetailActivity Pixel 2 API 28")
![alt text](https://imgur.com/0RbsLNz "AlbumActivity Nexus 4 API 24")
![alt text](https://imgur.com/x6z97YJ "AlbumDetailActivity Nexus 4 API 24")
![alt text](https://imgur.com/jfQAf59 "AlbumActivity Pixel 2 API 26")
![alt text](https://imgur.com/Kufpynd "AlbumDetailActivity Pixel 2 API 26")




