# VI
HES-SO: Visualisation de l'information

This is a team project required by the VI module in the context of the master at HES-SO

**==> Link for the current deployment:** [https://rocha-n.github.io/VI/](https://rocha-n.github.io/VI/)

## Index
[Main idea and message](#main-idea-and-message)  
[Visualisation](#visualisation)  
[Available Data](#available-data)  
[Repository Structure](#repository-structure)  

## Main idea and message
Nowadays, we have such a enourmous offer on alimentation products that it becomes quite easy to become submerged, and get tricked by the perfectly calibrated marketing appeal in choosing the (mostly) wrong ones.  

No wonder nutrition problems had arisen everywhere and the diet market is booming at the moment.  

The main idea behind this project is to be able to show the user information about __quality__ of food, in order to help to make an educated choice among the __quantity__ proposed.   
By allowing to quantify negative aspects (additives, palm oil, polyunsaturated fats, ...) but also positive (omega 3-6-9, proteins, labels, ...), we aim to contribute to help people correct their nutrition.

## Visualisation

The user will be presented with this interface:

![Main Screen](mockups/VI.png)

The left part of the screen will allow him to chose the values of the X and Y axes, as well as the type of graphic (scatterplot or treemap). A click on the `Refresh` button will calculate and display the view according the chosen parameters

## Available data

The comboboxes for the X and Y axis will contain the following options:
```
product name  
generic name  
creation datetime  
brand  
category  
main category  
origin  
manufacturing place  
labels  
country  
allergens  
traces  
nb_additives  
nb ingredients from palm oil  
nb ingredients that may be from palm oil  
nutrition grade  
energy (100g)  
energy from fat (100g)  
fat (100g)  
saturated fat (100g)  
monounsaturated fat (100g)  
polyunsaturated fat (100g)  
omega 3 fat (100g)  
omega 6 fat (100g)  
omega 9 fat (100g)  
carbohydrates (100g)  
sugars (100g)  
fiber (100g)  
proteins (100g)  
salt (100g)  
```

This will allow searches relevant to product quality, letting the user orient its queries towards ethic concerns (provenance, for example) or nutrition facts. This last category can also be used when checking for a specific component (like salt, for example) when needing to follow a strict diet, or more globally get a nutrition grade for a category of products, in order to restrict its initial choice. Those are just examples, of course. Any request can be made to fit user needs.  

## Repository structure
- data folder
  - contains the datasets used for the application.  
  We will start with a reduced one, and then use the full data file for release
- mockups folder
  - contains, well... mockups! Fair enough?
