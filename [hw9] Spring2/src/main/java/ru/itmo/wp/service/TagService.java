package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.repository.TagRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Set<Tag> getSetOfTags(String tagNames) {
        Set<Tag> setOfTags = new HashSet<>();

        if (tagNames == null || tagNames.equals("")) {
            return setOfTags;
        }

        Set<String> listOfTagNames = new HashSet<>(Arrays.asList(tagNames.split("\\s+")));

        for (String tagName : listOfTagNames) {
            Tag tag = new Tag();
            tag.setName(tagName);
            setOfTags.add(tag);
        }

        return setOfTags;
    }

    public boolean contains(Tag tag) {
        return tagRepository.existsByName(tag.getName());
    }

    public void save(Tag tag) {
        tagRepository.save(tag);
    }
}
