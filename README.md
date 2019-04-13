# nike-coding-challenge
Coding challenge assigned to me as an interview task by Nike, Inc.
 
 ### Original Instructions
 ```
 Create a sample Android app that displays the top albums across all genres using Apple's RSS generator found here: https://rss.itunes.apple.com/en-us.

The app will need to target android Pie with the minimum Api of Nougat.

Limit the external dependencies to only AndroidX 1.+, Material Design Components, and Glide or Picasso for Image Processing.

Expected behavior:
On launch, the user should see a RecyclerView showing one album per item. Each item should display the name of the album, the artist, and the album art (thumbnail image).

Tapping on an item will launch a new activity where we see a larger image at the top of the screen and the same information that was shown in the item view, plus genre, release date,
and copyright info below the image. A button should also be included on this detailed activity that when tapped fast app switches to the album page in either the Apple Music app —
if installed on the phone — or a browser. The button should be centered horizontally and pinned 20 dp from the bottom of the view and 20dp from the leading and trailing edges of the
view. The detail activity is expected to be a single flat ViewGroup.

Bonus: An accompaning Espresso test mimicking a golden path (select 1st element, view its contents, back press, select 2nd element..)
```
