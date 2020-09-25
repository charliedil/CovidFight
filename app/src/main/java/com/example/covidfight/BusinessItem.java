package com.example.covidfight;

/**
 * Data model for each card in the RecyclerView
 */
class BusinessItem {

    // Member variables representing the title and information about the sport.
    private String title;
    private String info;
    private int imageResource;

    /**
     * Constructor for the Business data model
     * @param title name of the business
     * @param info  information about the business
     */
    public BusinessItem(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }

    String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void changeTitle(String text) {
        title = text;
    }
}