package co.com.sofka.wsscore.domain.category.commands;

import co.com.sofka.wsscore.domain.generic.Command;

public class CreateCategoryCommand extends Command {
    private String categoryId;
    private String title;
    private String gender;
    private String description;

    public CreateCategoryCommand(String categoryId, String title, String gender, String description) {
        this.categoryId = categoryId;
        this.title = title;
        this.gender = gender;
        this.description = description;
    }

    public CreateCategoryCommand() {
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getTitle() {
        return title;
    }

    public String getGender() {
        return gender;
    }

    public String getDescription() {
        return description;
    }
}
