public enum ApparelTags{
    TOPS("Tops"), BOTTOMS("Bottoms"), JEWELRY("Jewelry"), FOOTWEAR("Footwear"),
    ACCESSORIES("Accessories"), DRESSES("Dresses"), SWIMWEAR("Swimwear"),
    OUTERWEAR("Outerwear");

    private String name;

    public ApparelTags(String name){
        this.name = name;
    }

    public String toString(){
        return this.name;
    }



}