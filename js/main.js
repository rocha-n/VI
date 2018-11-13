Array.prototype.groupBy = function (prop) {
  return this.reduce(function (groups, item) {
    const val = item[prop];
    groups[val] = groups[val] || [];
    groups[val].push(item);
    return groups
  }, {})
};

//let t = data.filter(item => item["nutrition_grade_fr"] != undefined && (item.nutrition_grade_fr.length == 1 || item.nutrition_grade_uk.length == 1));
let g = data.reduce((group, item) => {
  const val = (item["marque"] + "").toUpperCase();
  if (!group.get(val)) {
    group.set(val, {marque: val, total: 0})
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
let list = [];
g.forEach((value) => {
  if (value.total > 50) {
    list.push(value);
  }
});
list = list.sort((a, b) => a.marque.localeCompare(b.marque));

let list22 = [];
list.forEach((obj) => {
  let nutritionValues = ["a", "b", "c", "d", "e"];
  nutritionValues.forEach(value => {
    if (obj[value]) {
      list22.push({marque: obj.marque, total: obj[value], nutrition:value})
    }
  });
});
console.log(list22);

let list2 = [];
list.forEach((obj) => {
  let nutritionValues = ["a", "b", "c", "d", "e"];
  nutritionValues.forEach(value => {
    if (value === "a") {
      list2.push({marque: obj.marque, total: ((obj.a/obj.total)*100)})
    }
  });
});
console.log(list2);
