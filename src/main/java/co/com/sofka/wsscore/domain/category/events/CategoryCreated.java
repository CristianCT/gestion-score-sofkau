package co.com.sofka.wsscore.domain.category.events;

import co.com.sofka.wsscore.domain.generic.DomainEvent;

public class CategoryCreated extends DomainEvent {

    private final String title;
    private final String gender;
    private final String description;

    public CategoryCreated(String title, String gender, String description) {
        super("sofkau.category.categorycreated");
        this.title = title;
        this.gender = gender;
        this.description = description;
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
