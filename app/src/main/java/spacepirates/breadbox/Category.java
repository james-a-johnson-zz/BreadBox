public enum Category{
    APPAREL("Apparel"), FURNITURE("Furniture"), KITCHENWARE("Kitchenware"),
     TOYS("Toys"), BOOKS("Books"), ELECTRONICS("Electronics");

    private String name;

    public Category(String name){
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}