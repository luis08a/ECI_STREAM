package edu.eci.arsw.eci_stream.model.entities;

import java.util.List;

/**
 * RoomInfo
 */
public class RoomInfo {    
    private String category;
    private String title;
    private List<String> KeyWords;
    private String description;

    public RoomInfo (String category, String tittle, List<String> keyWords, String description) {
        this.category =category;
        this.title=tittle;
        this.KeyWords=keyWords;
        this.description=description;
        
    }
    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the keyWords
     */
    public List<String> getKeyWords() {
        return KeyWords;
    }

    /**
     * @param keyWords the keyWords to set
     */
    public void setKeyWords(List<String> keyWords) {
        this.KeyWords = keyWords;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
}