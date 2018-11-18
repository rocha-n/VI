/**
 * Permet de regrouper la liste en fonction d'un nom de champs et calcule le nombre de produit pour la nutrition
 * @param prop Le champ que l'on vas utiliser pour le groupement.
 * @param list La liste des produits
 * @returns Une nouvelle liste qui contient le nom du champs que l'on à donné et des infos sur les nutritions.
 */
function groupByAndCountNutrition(props, list) {
    let newList = list.reduce((group, item) => {
        const val = props.reduce((key, prop) => {
            return key + item[prop]
        }, "").toUpperCase();

        if (!group.get(val)) {
            let objet = {total: 0};
            props.forEach(prop => {
                objet[prop] = item[prop];
            });
            group.set(val, objet);
        }
        let nutritionValues = ["a", "b", "c", "d", "e"];
        nutritionValues.forEach(value => {
            if (item.nutrition === value) {
                let nutrition = group.get(val)[value];
                if (!nutrition) {
                    nutrition = 0;
                }
                group.get(val).total++;
                group.get(val)[value] = nutrition + 1;
            }
        });
        return group;
    }, new Map());
    return Array.from(newList.values()).sort((a, b) => a[props[0]].localeCompare(b[props[0]]));
}

/**
 * Permet de filtrer dans la liste les objets qui on peut de produits.
 * @param list
 * @param nombreDeProduit
 * @returns La liste
 */
function filtreParNombreDeProduit(list, nombreDeProduit) {
    let newList = [];
    list.forEach((value) => {
        if (value.total > nombreDeProduit) {
            newList.push(value);
        }
    });
    return newList;
}

/**
 * Permet de filtrer la liste des nutrition et de calculer le pourcentage.
 *
 *  @param prop Pour définir le champs que l'on veut utiliser.
 * @param nutrition La nutrition que l'on veut filtrer
 * @param list La liste que l'on veut filtrer
 */
function filtrePourLesNutritiomAndComputPourcentage(props, nutrition, list) {
    let newList = [];
    list.forEach((obj) => {
        let nutritionValues = ["a", "b", "c", "d", "e"];
        nutritionValues.forEach(value => {
            if (value === nutrition && obj[nutrition]) {
                let newObjet = {total: ((obj[nutrition] / obj.total) * 100)};
                props.forEach(prop => {
                    if (obj[value]) {
                        newObjet[prop] = obj[prop];
                    }
                });
                newList.push(newObjet);
            }
        });
    });
    return newList;
}

/**
 * Permet de créer la liste qui sera utilisé par 3Dplus. Elle permet de pouvoir jouer avec les regroupements.
 *
 * @param prop Le nom du champ que l'on vas utiliser pour le groupement de la liste
 * @returns {Array} La liste pour 3dplus
 */
function createLigneToHaveTheNutrition(props, list) {
    let newList = [];
    list.forEach((obj) => {
        let nutritionValues = ["a", "b", "c", "d", "e"];
        nutritionValues.forEach(value => {
            let newObjet = {total: obj[value], nutrition: value};
            props.forEach(prop => {
                if (obj[value]) {
                    newObjet[prop] = obj[prop];
                }
            });
            newList.push(newObjet);
        });
    });
    return newList;
}
