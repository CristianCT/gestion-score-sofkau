package co.com.sofka.wsscore.domain.category.commands;

import co.com.sofka.wsscore.domain.generic.Command;

public class AssignProductCommand extends Command {
    private String categoryId;

    public AssignProductCommand(String categoryId) {
        this.categoryId = categoryId;
    }

    public AssignProductCommand() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

}
