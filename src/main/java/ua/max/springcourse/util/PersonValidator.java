package ua.max.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.max.springcourse.dao.PersonDAO;
import ua.max.springcourse.models.Person;
import ua.max.springcourse.services.PeopleService;

import java.time.Year;
import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PersonDAO personDAO, PeopleService peopleService) {
        this.personDAO = personDAO;
        this.peopleService = peopleService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        final int currentYear = Year.now().getValue();
        if(person.getYearOfBirth() > currentYear) {
            errors.rejectValue("yearOfBirth", "",
                    "Year of birth cannot be greater than " + currentYear);
        }
        final int lowerYearOfBirthLimit = 1900;
        if(person.getYearOfBirth() < lowerYearOfBirthLimit) {
            errors.rejectValue("yearOfBirth", "",
                    "Year of birth cannot be smaller than " + lowerYearOfBirthLimit);
        }

        Optional<Person> homonymInDB = peopleService.findByFullName(person.getFullName());
        if (homonymInDB.isPresent() && !homonymInDB.get().getId().equals(person.getId())) {
            errors.rejectValue("fullName", "", "This name is already taken");
        }
    }
}
