package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.form.TagsString;
import ru.itmo.wp.service.TagService;

import java.util.Objects;
import java.util.Set;

@Component
public class TagsStringValidator implements Validator {
    private final TagService tagService;

    public TagsStringValidator(TagService tagService) {
        this.tagService = tagService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return TagsString.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // No operations
    }
}
