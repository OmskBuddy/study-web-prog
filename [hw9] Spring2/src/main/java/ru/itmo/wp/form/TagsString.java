package ru.itmo.wp.form;

import javax.validation.constraints.Pattern;

public class TagsString {
    @Pattern(regexp = "[\\sa-zA-Z]*", message = "Only latin letters expected")
    private String tagNames;

    public String getTagNames() {
        return tagNames;
    }

    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
    }
}
