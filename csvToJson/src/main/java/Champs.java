public enum Champs {
    CODE(0),
    PRODUCT(7),
    MARQUE(12),
    CATEGORIE(14),
    PAYS(31),
    NUTRITION(54);

    private int index;

    Champs(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
