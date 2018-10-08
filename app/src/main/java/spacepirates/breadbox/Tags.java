public enum Tags{
    TOPS("Tops", Category.APPAREL), BOTTOMS("Bottoms", Category.APPAREL),
    JEWELRY("Jewelry", Category.APPAREL), FOOTWEAR("Footwear", Category.APPAREL),
    ACCESSORIES("Accessories", Category.APPAREL), DRESSES("Dresses", Category.APPAREL),
    SWIMWEAR("Swimwear", Category.APPAREL), OUTERWEAR("Outerwear", Category.APPAREL),

    CUTLERY("Cutlery", Category.KITCHENWARE),

    NOVEL("Novel", Category.BOOK), BIOGRAPHY("Biography", Category.BOOK),
    COOKBOOK("CookBook" Category.BOOK);

    private String name;
    private Category category;

    public Tags(String name, Category category){
        this.name = name;
        this.category = category;
    }

    public String toString(){
        return this.name;
    }



}