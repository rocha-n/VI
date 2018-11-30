# VI
HES-SO: Visualisation de l'information

This is a team project required by the VI module in the context of the master at HES-SO

**==> Link for the current deployment:** [https://rocha-n.github.io/VI/](https://rocha-n.github.io/VI/)

## Index
[Main idea and message](#main-idea-and-message)  
[Usable dimensions for visualisation](#usable-dimensions-for-visualisation)
[Available Data](#available-data)  
[Visualisation](#visualisation)  
[Repository Structure](#repository-structure)  

## Main idea and message
Nowadays, we have such a enourmous offer on alimentation products that it becomes quite easy to become submerged, and get tricked by  perfectly calibrated marketing appeal in choosing (mostly) the wrong ones.  

No wonder nutrition problems had arisen everywhere and the diet market is booming at the moment.  

The main idea behind this project is to be able to show the user information about __quality__ of food, in order to help to make an educated choice among the __quantity__ proposed.   

To do so, we use a __nutrition index__. It's a value between A and E (better to worst, respectively) that is calculated to take account of the nutritional properties as well as the components of the product. The better the index, the better the food you're eating!

## Available data
The data presented to the user is a subset of the [Open Food Facts](#https://world.openfoodfacts.org) database.  
We suppressed all records that were considered invalid or incomplete.  
Then we removed all the remaining records that had not a value for the country, the make or the ranking.  
As the data where initially really sparse, we reduced considerably the data, in order to be able to have 100% appliable records only.  
Finally, we transformed the database format, from CSV to JSON.  

After all the database alterations, the file went from 1.8Gb to 29.2Mb!  

## Usable dimensions for visualisation
There are three main dimensions that are left to user choice: Country, make, and ranking.

## Visualisation

The user will be presented with this interface:  
<!--le menu à gauche répond au pattern sandwich-hamburger -->

![Main Screen](mockups/VI.png)

The left part of the screen will allow him to chose the values of the X and Y axes, as well as the type of graphic (scatterplot or treemap). A click on the `Refresh` button will calculate and display the view according the chosen parameters



## Repository structure
- data folder
  - contains the datasets used for the application.  
  We will start with a reduced one, and then use the full data file for release
- mockups folder
  - contains, well... mockups! Fair enough?
