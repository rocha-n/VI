# VI
HES-SO: Visualisation de l'information

This is a team project required by the VI module in the context of the master at HES-SO

**==> Link for the current deployment:** [https://rocha-n.github.io/VI/](https://rocha-n.github.io/VI/)

## Index
[Main idea and message](#main-idea-and-message)  
[Usable dimensions for visualisation](#usable-dimensions-for-visualisation)
[Available Data](#available-data)  
[Visualisation](#visualisation)  
[Choices made](#choices-made)  
[Presentation](#presentation)  

## Main idea and message
Nowadays, we have such a enourmous offer on alimentation products that it becomes quite easy to become submerged, and get tricked by  perfectly calibrated marketing appeal in choosing (mostly) the wrong ones.  

No wonder nutrition problems had arisen everywhere and the diet market is booming at the moment.  

The main idea behind this project is to be able to show the user information about __quality__ of food, in order to help to make an educated choice among the __quantity__ proposed.   

To do so, we use a __nutrition index__. It's a value between A and E (better to worst, respectively) that is calculated to take account of the nutritional properties as well as the components of the product. The better the index, the better the food you're eating!

Also, you'll be able to group by brand or by country, to see the proportions of the different nutrition indexes according to the chosen grouping. That way, you'll know which brand or which country tends to be better, from an alimentation quality standpoint.

## Available data
The data presented to the user is a subset of the [Open Food Facts](#https://world.openfoodfacts.org) database.  
We suppressed all records that were considered invalid or incomplete.  
Then we removed all the remaining records that had not a value for the country, the make or the ranking.  
As the data where initially really sparse, we reduced considerably the data, in order to be able to have 100% appliable records only.  
Finally, we transformed the database format, from CSV to JSON.  

After all the database alterations, the file went from 1.8Gb to 29.2Mb!  

## Usable dimensions for visualisation
There are three main dimensions that are left to user choice: Country, make, and nutrition index.

## Visualisation
The user will be presented with this interface:  

![Screen](mockups/sandwich%20closed.png)

The main part of the screen is occupied by a Treemap visualisation.  
It will display one of the three main dimensions. Each time the user selects one tile of the Treemap, the visualisation will change, as it will take the user's choice as a filter. It will then display another dimension to which the previously created filter has been applied. As there are three dimensions, the user will be able to go down three levels.  

On the left portion of the screen, a menu, obeing to the so-called _hamburger pattern_, will allow the user to set manually  the three dimensions with a drop-down menu. It will also be able to select if the values displayed are finite quantities or percent. The former will allow the option of the nutrition index visualized.
Also, to ease the representation of the minorities and add flexibilty to the user, we added the possibility to specify a max of products per group, and the maximum of groups taken in account for the visualisation.

![Screen](mockups/sandwich%20opened.png)

On the top of the screen, there is a banner indicating where the visualised data comes from, so the user can visit the source website and, if wanted, collaborate on populating the database.

The user is also able to change the visualisation to a bar chart. In certain situation, this kind of data representation will be more useful, as it ranks precisely the categories in descending order with their respective values. The treemap is more oriented towards a visualisation on the percentage of the sum of the values observed is represented by a given category.

## Choices made
In order to succeed to have this visualisation not only ready, but also usable by end-users, we hade to make some choices:

- Reducing the Open Food Facts database  
  The initial database was quite massive, which limited it's usability.  
  In fact, at each query, we had to browse the whole thing to retrieve our data, and that was causing slowdowns.  
  Not to mention the difficulties to host the file in a free web server. Remember it made originally 1.8Gb!
  
- Limiting the user choice to three pre-defined levels  
  The main reason is data quality. The original database was quite sparse.  
  The root cause of this is probably a combination of the fact that Open Food Facts is a community driven project (the data is inserted by end-users), and the fact that it is very exensive. The database designers offered a lot (too much?) fields to be filled, in order to enable very specific queries.  
  Unfortunately, that means that the end user is faced with several dozens of different informations for a single product.  
  Obviously, for the most part, they only fill the "major" fields (the brand, the country, and nutritional info).
  So we decided to pick a few criteria, and to only use the data for which those three criteria was present.  
  That saved us a lot of space in terms of file size, and also enable us to only present coherent data to the end-users.  
  
- Using GitHub pages as a web server  
Having a web-based project is always a chore when no specific infrastructure is given, as is implies having a reacheable web server, a domain name, and so on. So we decided to use a GitHub functionality named Pages, that we didn't know about.  
It enables us to have a website deployed online, with a non-modifiable URL (but in this case it's perfectly ok).  
That was a big discovery for us, as it was a real game changer for the usual constraints of web-based project.  
We are really glad of this discovery, and want to pass the word around.  
You can have a go at it just with a GitHub account. The documentation is [here](https://pages.github.com).

- Chosing the right visualisation
We wanted to present a visualisation that enables the observer to immediately distinguish categories based on a scalar.  
The scalar would be its representation ratio in the chosen category, that would be one amongst the three we picked-up.
For this specific requirement, the Treemap was, in our opinions, the most adapted way to go. 
Then, we thought it would be pertinent to have bar graphs, for when we want to have a finite value and not a magnitude.
So we let the user choose its graph type between treemap and bar graph.
As there is no real "right or wrong" choices but only "more or less adapted" choices, we are not taking any risks (coherence-wise) by letting the user chose by himself what seems to be the more informative view.

- Picking the right library
We were hunting the web for a library that would be flexible enough to give us the choice to change the visualisation dynamically, and that produced a beautiful enough output (according to our tastes, at least).  
Besides the requirements of generating both treemaps and bar charts, the keyword was "dynamic".  
We wanted to give the user immediate response with a great look and feel, in order for him or her to feel *in control*.   
That means that the visualisation must be at the same time easy to use and informative.  
We found the [D3.js](https://d3js.org) open-source library, that seemed to fit the bill. We have done some experiments with it, and it was visually nice, and offered the functionnality we needed to achieve our goal.  



## Presentation
This project included a presentation done to our colleagues.  
It is available directly in your browser, at [this address](https://docs.google.com/presentation/d/1AdQmDZrvV-eWrsDIJy3vZnkWH2gFoZ-tPa3fw1CODhg/edit?usp=sharing)
